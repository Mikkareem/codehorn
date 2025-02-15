package com.techullurgy.codehorn.common.dto

import com.techullurgy.codehorn.common.model.CodeSubmissionResult

data class TestcaseResultDTO(
    val testcase: TestcaseDTO,
    val expectedResult: String,
    val yourResult: String,
    val stdout: String,
    val result: CodeSubmissionResult
)