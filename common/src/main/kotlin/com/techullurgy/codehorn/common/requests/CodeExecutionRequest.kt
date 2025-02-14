package com.techullurgy.codehorn.common.requests

import com.techullurgy.codehorn.common.dto.TestcaseDTO

data class CodeExecutionRequest(
    val submissionId: String,
    val fileContent: String,
    val sampleTestcases: List<TestcaseDTO>,
    val hiddenTestcases: List<TestcaseDTO>
)
