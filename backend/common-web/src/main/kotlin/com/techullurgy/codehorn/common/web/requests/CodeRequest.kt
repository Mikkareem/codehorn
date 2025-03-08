package com.techullurgy.codehorn.common.web.requests

import com.techullurgy.codehorn.common.web.dto.TestcaseDTO

data class CodeRequest(
    val language: String,
    val userCode: String,
    val userTestcases: List<TestcaseDTO> = emptyList()
)