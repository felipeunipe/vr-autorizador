package br.com.vr.autorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class VrAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrAutorizadorApplication.class, args);
	}

}
