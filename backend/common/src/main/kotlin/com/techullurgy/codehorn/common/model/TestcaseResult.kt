package com.techullurgy.codehorn.common.model

data class TestcaseResult(
    val testcase: ProblemTestcase,
    val expectedResult: String,
    val yourResult: String,
    val stdout: String,
    val stderr: String,
    val compilationError: String,
    val result: CodeSubmissionResult
)