package com.rbleek.wordgenclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class WordgenClientApplication {

	Logger log = LoggerFactory.getLogger(WordgenClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WordgenClientApplication.class, args);
	}

	@Bean
	CommandLineRunner run(WebClient webClient){
		return args -> {
		    webClient.get().uri("/generate")
					.retrieve().bodyToFlux(Word.class)
					.map(Word::getWord)
					.subscribe(s -> log.info("received a word: {}", s));
		};
	}

	@Bean
	WebClient webClient() {
		return WebClient.create("http://localhost:8080/words").mutate().build();
	}
}
