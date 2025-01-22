package com.kievmaia.rosaencantada;

import org.springframework.boot.SpringApplication;

public class TestRosaencantadaApplication {

	public static void main(String[] args) {
		SpringApplication.from(RosaencantadaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
