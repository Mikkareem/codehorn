package com.techullurgy.codehorn.common.web.dto

import com.techullurgy.codehorn.common.model.CodeSubmissionResult
import com.techullurgy.codehorn.common.web.model.ResultDetail

data class TestcaseResultDTO(
    val testcaseId: String,
    val inputs: List<TestcaseInputDTO>,
    val result: CodeSubmissionResult,
    val details: ResultDetail
)