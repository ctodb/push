package cn.ctodb.push.server.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import cn.ctodb.push.server.service.MgsServer;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

	@Autowired
	private MgsServer mgsServer;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		new Thread(mgsServer).start();

	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}