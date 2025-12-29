package com.techullurgy.codehorn.common.web

import com.techullurgy.codehorn.common.web.exceptions.CodehornRestClientException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient
import tools.jackson.databind.json.JsonMapper

@Configuration
class RestClientConfiguration {

    @Bean
//    @LoadBalanced
    fun provideRestClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean
    fun provideRestClient(builder: RestClient.Builder): RestClient {
        return builder
            .defaultStatusHandler(HttpStatusCode::isError) { req, res ->
                val body = res.body.bufferedReader().use { it.readText() }
                throw CodehornRestClientException(exceptionBody = body, status = res.statusCode)
            }
            .build()
    }

    @Bean
    fun objectMapper(): JsonMapper {
        return JsonMapper().apply {
//            registerKotlinModule()
//            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // Ignore unknown fields
        }
    }
}