package com.techullurgy.codehorn.java.execution

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseCollectionType
import com.techullurgy.codehorn.common.model.TestcaseDataType
import com.techullurgy.codehorn.common.model.TestcaseType
import com.techullurgy.codehorn.common.web.requests.CodeExecutionRequest
import com.techullurgy.codehorn.common.web.model.ResultDetail
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.random.Random

@WebMvcTest
class JavaExecutionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

//    @Test
    fun basicTest() {
        mockMvc.post(EndpointConstants.Public.LanguageExecution.POST_CODE_EXECUTION_JAVA) {
            contentType = MediaType.APPLICATION_JSON

            content = mapper.writeValueAsString(
                CodeExecutionRequest(
                    submissionId = "irsath8192",
                    fileContent = "Filecontent of java",
                    testcases = listOf(
                        ProblemTestcase(
                            id = "1",
                            isHidden = false,
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                        ProblemTestcase(
                            id = "2",
                            isHidden = false,
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                        ProblemTestcase(
                            id = "3",
                            isHidden = false,
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                        ProblemTestcase(
                            id = "4",
                            isHidden = false,
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                        ProblemTestcase(
                            id = "5",
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                        ProblemTestcase(
                            id = "6",
                            inputNames = listOf("x", "y"),
                            inputs = listOf("${Random.Default.nextInt()}", "${Random.Default.nextInt()}"),
                            masks = listOf(
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask
                            ),
                        ),
                    )
                )
            )
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun testcaseResultResponseParsingTest() {
        val mapper = ObjectMapper().registerKotlinModule()


        data class OuterResponse(
            val id: String,
            val resp: ResultDetail
        )

        val response1: OuterResponse = OuterResponse(
            "2718",
            ResultDetail.NotExecutedResult
        )

        val response2: OuterResponse = OuterResponse(
            "2718738",
            ResultDetail.RuntimeErrorResult("stdout", "stderr")
        )

        val string1 = mapper.writeValueAsString(response1)
        println(string1)
        val r1 = mapper.readValue<OuterResponse>(string1, OuterResponse::class.java)
        println(r1)

        val string2 = mapper.writeValueAsString(response2)
        println(string2)
        val r2 = mapper.readValue<OuterResponse>(string2, OuterResponse::class.java)
        println(r2)
    }
}