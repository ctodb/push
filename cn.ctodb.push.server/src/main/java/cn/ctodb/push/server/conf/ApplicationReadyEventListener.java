package cn.ctodb.push.server.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import cn.ctodb.push.server.service.MgsServer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    @Autowired
    private MgsServer mgsServer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread(mgsServer).start();
        new Thread(() -> {
            while (true) {
                try {
                    MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
                    requestEntity.add("id", mgsServer.getId());
                    requestEntity.add("port", mgsServer.getPort() + "");
                    restTemplate.postForObject(applicationProperties.getServer().getCenter() + "/keep", requestEntity, Object.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000 * 60l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}