package com.techullurgy.codehorn.combinedtests

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient

@SpringBootApplication
open class CombinedTestApplication {
    @Value("\${app.host}")
    private lateinit var host: String

    @Bean
    open fun restClient() : RestClient {
        return RestClient.builder()
            .baseUrl("http://$host:8080")
            .defaultStatusHandler(HttpStatusCode::is4xxClientError) { req, res -> }
            .build()
    }
}