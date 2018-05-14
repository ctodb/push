package cn.ctodb.push.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class RegApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RegApp.class);
		app.run(args);
	}
}