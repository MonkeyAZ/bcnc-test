package com.bcncgroup.test.app.businesslogic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bcncgroup.test.app.businesslogic.repositories.PriceRepository;
import com.bcncgroup.test.app.businesslogic.services.PriceService;

@Configuration
public class Config {

    @Bean
    PriceService priceServer(PriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
}
