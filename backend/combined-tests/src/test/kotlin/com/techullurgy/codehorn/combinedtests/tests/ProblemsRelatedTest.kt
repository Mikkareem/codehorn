package com.techullurgy.codehorn.combinedtests.tests

import com.techullurgy.codehorn.common.web.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.common.web.responses.SnippetForProblemForLanguageResponse
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProblemsRelatedTest {

    @Autowired
    private lateinit var client: RestClient

    @Test
    @Order(1)
    fun getProblemByIdForUser() {
        val response1 = client.get()
            .uri("/problems/19")
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(ProblemByIdForUserResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response1.statusCode)
        assertEquals(19, response1.body!!.problem.problemNo)

        val response2 = client.get()
            .uri("/problems/19")
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(ProblemByIdForUserResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response2.statusCode)
        assertEquals(19, response2.body!!.problem.problemNo)
    }

    @Test
    @Order(2)
    fun getSnippetForCForProblem() {
        val response = client.get()
            .uri {
                it.path("/problems/{id}/snippet")
                    .queryParam("language", "c")
                    .build("19")
            }
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals("c", response.body!!.language)
        assertEquals(true, response.body!!.snippet.isNotEmpty())
    }

    @Test
    @Order(3)
    fun getSnippetForCppForProblem() {
        val response = client.get()
            .uri {
                it.path("/problems/{id}/snippet")
                    .queryParam("language", "cpp")
                    .build("19")
            }
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals("cpp", response.body!!.language)
        assertEquals(true, response.body!!.snippet.isNotEmpty())
    }

    @Test
    @Order(4)
    fun getSnippetForJavaForProblem() {
        val response = client.get()
            .uri {
                it.path("/problems/{id}/snippet")
                    .queryParam("language", "java")
                    .build("19")
            }
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals("java", response.body!!.language)
        assertEquals(true, response.body!!.snippet.isNotEmpty())
    }

    @Test
    @Order(5)
    fun getSnippetForPythonForProblem() {
        val response = client.get()
            .uri {
                it.path("/problems/{id}/snippet")
                    .queryParam("language", "python")
                    .build("19")
            }
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals("python", response.body!!.language)
        assertEquals(true, response.body!!.snippet.isNotEmpty())
    }


    @Test
    @Order(6)
    fun getSnippetForJavascriptForProblem() {
        val response = client.get()
            .uri {
                it.path("/problems/{id}/snippet")
                    .queryParam("language", "javascript")
                    .build("19")
            }
            .header("Authorization", "Bearer 12345678")
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)

        assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
        assertEquals("javascript", response.body!!.language)
        assertEquals(true, response.body!!.snippet.isNotEmpty())
    }
}