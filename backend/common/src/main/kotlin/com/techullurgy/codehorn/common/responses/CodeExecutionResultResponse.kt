package com.techullurgy.codehorn.common.responses

import com.techullurgy.codehorn.common.model.TestcaseResult

data class CodeExecutionResultResponse(
    val submissionId: String,
    val testcaseResults: List<TestcaseResult>
)
