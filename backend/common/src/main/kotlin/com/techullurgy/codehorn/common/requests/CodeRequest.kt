package com.techullurgy.codehorn.common.requests

import com.techullurgy.codehorn.common.dto.TestcaseDTO

data class CodeRequest(
    val language: String,
    val userCode: String,
    val userTestcases: List<TestcaseDTO> = emptyList()
)