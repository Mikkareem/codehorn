package com.techullurgy.codehorn.combinedtests.tests

import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient
import kotlin.test.assertEquals

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProblemsRelatedTest {

    @Autowired
    private lateinit var client: RestClient

//    @Test
    @Order(1)
    fun getProblemById() {
        val response = client.get()
            .uri("/problems/19")
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(ProblemByIdForUserResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
    }
}