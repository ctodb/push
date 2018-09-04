package cn.ctodb.push.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class PushServerApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PushServerApp.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}