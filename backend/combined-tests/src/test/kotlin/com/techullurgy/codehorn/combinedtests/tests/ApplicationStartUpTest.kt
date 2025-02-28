package com.techullurgy.codehorn.combinedtests.tests

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient
import kotlin.test.assertEquals

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ApplicationStartUpTest {

    @Autowired
    private lateinit var client: RestClient

    @Test
    @Order(1)
    fun testServerAvailability() {
        data class HealthCheckResponse(val status: String)

        val response = client
            .get()
            .uri("/health-check")
            .retrieve()
            .toEntity(HealthCheckResponse::class.java)

        println(response.body)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals(HealthCheckResponse("UP"), response.body)
    }
}