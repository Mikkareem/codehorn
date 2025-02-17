package com.techullurgy.codehorn.contests.configuration

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class ContestsConfiguration {

    @Bean
    @LoadBalanced
    fun provideRestClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean
    fun provideRestClient(builder: RestClient.Builder): RestClient {
        return builder.build()
    }
}