package com.utku.exchange;

import com.utku.exchange.controller.ExchangeController;
import com.utku.exchange.service.ExchangeService;
import com.utku.exchange.service.impl.ApiLayerIntegrationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeApplicationTests {

	@Autowired
	private ExchangeController exchangeController;
	@Autowired
	private ExchangeService exchangeService;
	@Autowired
	private ApiLayerIntegrationImpl apiLayerIntegration;

	@Test
	void contextLoads() {
		assertThat(exchangeController).isNotNull();
		assertThat(exchangeService).isNotNull();
		assertThat(apiLayerIntegration).isNotNull();
	}

}
