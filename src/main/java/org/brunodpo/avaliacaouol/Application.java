package org.brunodpo.avaliacaouol;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ponto de entrada deste micro serviço
 * @author brunodpo
 * @since 2019-04-20
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Properties props = System.getProperties();
		props.setProperty("java.net.preferIPv4Stack", "true");
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Referência dinâmica para a chamada de APIs REST externas
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
