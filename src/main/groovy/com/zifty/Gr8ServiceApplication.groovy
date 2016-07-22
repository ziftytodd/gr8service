package com.zifty

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class Gr8ServiceApplication {

	static void main(String[] args) {
		SpringApplication.run Gr8ServiceApplication, args
	}
}
