package com.techullurgy.codehorn.combinedtests.tests

import com.techullurgy.codehorn.common.model.CodeSubmissionResult
import com.techullurgy.codehorn.common.web.requests.CodeRequest
import com.techullurgy.codehorn.common.web.responses.RunResultResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class BasicRunCodeTest {

    @Autowired
    private lateinit var client: RestClient

    @Test
    fun runJavaCodeTestAccepted() {
        val code = """
            class Solution {
                public int addTwo(int a, int b) {
                    return a+b;
                }
            }
        """.trimIndent()

        val request = CodeRequest(
            language = "java",
            userCode = code,
            userTestcases = listOf()
        )

        val response = client.post()
            .uri("http://localhost:8080/problems/3/run")
            .header("Authorization", "Bearer 12345678")
            .body(request)
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(RunResultResponse::class.java)

        val body = response.body!!

        assertEquals(4, body.results.size)
        assertEquals(4, body.results.count { it.result == CodeSubmissionResult.Accepted })
    }

    @Test
    fun runJavaCodeTestWrongAnswer() {
        val code = """
            class Solution {
                public int addTwo(int a, int b) {
                    return a*b;
                }
            }
        """.trimIndent()

        val request = CodeRequest(
            language = "java",
            userCode = code,
            userTestcases = listOf()
        )

        val response = client.post()
            .uri("http://localhost:8080/problems/3/run")
            .header("Authorization", "Bearer 12345678")
            .body(request)
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(RunResultResponse::class.java)

        val body = response.body!!

        assertEquals(4, body.results.size)
        assertEquals(3, body.results.count { it.result == CodeSubmissionResult.WrongAnswer })
        assertEquals(1, body.results.count { it.result == CodeSubmissionResult.NotExecuted })
    }
}