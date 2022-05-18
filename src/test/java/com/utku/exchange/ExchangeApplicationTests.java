package com.utku.exchange;

import com.utku.exchange.controller.ExchangeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeApplicationTests {

	@Autowired
	private ExchangeController exchangeController;

	@Test
	void contextLoads() {
		assertThat(exchangeController).isNotNull();
	}

}
