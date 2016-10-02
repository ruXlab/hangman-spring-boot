package vc.rux.codingtest.hangman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HangmanApplication {

	public static void main(String[] args) {


		SpringApplication.run(HangmanApplication.class, args);
	}

	public static final int MAX_ATTEMPTS = 7;
}
