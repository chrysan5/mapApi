package com.mapApi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class KakaoConfig {

    @Value("${spring.profiles.include}")
    private String kakao_apikey;

    @Bean
    public WebClient kakaoWebClient(){
        return WebClient.builder().baseUrl("http://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakao_apikey).build();
    }
}
