package com.techullurgy.codehorn.java.execution.controllers

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseType
import com.techullurgy.codehorn.common.requests.CodeExecutionRequest
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionService
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionType
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/code/execution/java")
class JavaExecutionController {

    @Autowired
    private lateinit var codeExecutionServiceProvider: ObjectProvider<CodeExecutionService>

    // TODO: Can we make it GET request with body
    @PostMapping()
    fun executeJavaCode(@RequestBody executionRequest: CodeExecutionRequest): ResponseEntity<String> {

        val codeExecutionService = codeExecutionServiceProvider.getObject(executionRequest.submissionId)

        codeExecutionService.executeFor(
            fileContent = executionRequest.fileContent,
            executionType = CodeExecutionType.STOP_IF_FAILS,
            testcases = listOf(
                ProblemTestcase(
                    id = 1,
                    isHidden = true,
                    input = executionRequest.sampleTestcases.map { it.input } + executionRequest.hiddenTestcases.map { it.input },
                    masks = listOf(
                        TestcaseType.SINGLE_TYPE or TestcaseType.NON_STRING_TYPE,
                        TestcaseType.SINGLE_TYPE or TestcaseType.NON_STRING_TYPE
                    )
                )
            )
        )

        return ResponseEntity.ok("Success")
    }
}