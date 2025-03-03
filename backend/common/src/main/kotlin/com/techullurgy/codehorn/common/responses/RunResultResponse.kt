package com.techullurgy.codehorn.common.responses

import com.techullurgy.codehorn.common.model.TestcaseResult

data class RunResultResponse(
    val problemId: String,
    val results: List<TestcaseResult>
)