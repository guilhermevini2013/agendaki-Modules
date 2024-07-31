package com.agendaki.financially.configurations.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagBankClintConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecode();
    }
}
