package com.techullurgy.codehorn.common.web.requests

import com.techullurgy.codehorn.common.annotations.InternalNetworkModel
import com.techullurgy.codehorn.common.model.ProblemTestcase

@InternalNetworkModel
data class CodeExecutionRequest(
    val submissionId: String,
    val fileContent: String,
    val testcases: List<ProblemTestcase>
)