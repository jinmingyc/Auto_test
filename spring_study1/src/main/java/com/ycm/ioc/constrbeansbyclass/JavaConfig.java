package com.ycm.ioc.constrbeansbyclass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

	@Bean
	FunctionService functionService() {
		return new FunctionService();
	}

	@Bean
	UseFuctionService useFuctionService() {
		UseFuctionService useFuctionService = new UseFuctionService();
		useFuctionService.setFunctionService(functionService());

		return useFuctionService;

	}
}
