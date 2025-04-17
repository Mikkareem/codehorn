package com.techullurgy.codehorn.common.web.responses

import com.techullurgy.codehorn.common.web.dto.TestcaseResultDTO

data class RunResultResponse(
    val problemId: String,
    val results: List<TestcaseResultDTO>
)