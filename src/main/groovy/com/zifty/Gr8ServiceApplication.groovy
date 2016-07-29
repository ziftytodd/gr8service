package com.zifty

import com.netflix.appinfo.AmazonInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.commons.util.InetUtils
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
class Gr8ServiceApplication {

	static void main(String[] args) {
		SpringApplication.run Gr8ServiceApplication, args
	}

	@Bean
	@Autowired
	@Profile("aws")
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setDataCenterInfo(info);
		return config;
	}
}

