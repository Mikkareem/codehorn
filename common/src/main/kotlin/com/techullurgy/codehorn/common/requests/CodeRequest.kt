package com.techullurgy.codehorn.common.requests

import com.techullurgy.codehorn.common.dto.TestcaseDTO

data class CodeRequest(
    val userId: String,
    val problemId: String,
    val language: String,
    val userCode: String,
    val sampleTestcases: List<TestcaseDTO>
)