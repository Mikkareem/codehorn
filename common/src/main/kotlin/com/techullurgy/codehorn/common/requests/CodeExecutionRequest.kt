package com.techullurgy.codehorn.common.requests

import com.techullurgy.codehorn.common.annotations.InternalNetworkModel
import com.techullurgy.codehorn.common.dto.TestcaseDTO

@InternalNetworkModel
data class CodeExecutionRequest(
    val submissionId: String,
    val fileContent: String,
    val sampleTestcases: List<TestcaseDTO>,
    val hiddenTestcases: List<TestcaseDTO>
)