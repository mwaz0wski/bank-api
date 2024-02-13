package es.nextdigital.bank;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		var app = new SpringApplication(BankApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
