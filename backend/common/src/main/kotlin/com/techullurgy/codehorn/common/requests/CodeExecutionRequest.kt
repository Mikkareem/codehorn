package com.techullurgy.codehorn.common.requests

import com.techullurgy.codehorn.common.annotations.InternalNetworkModel
import com.techullurgy.codehorn.common.dto.TestcaseDTO
import com.techullurgy.codehorn.common.model.ProblemTestcase

@InternalNetworkModel
data class CodeExecutionRequest(
    val submissionId: String,
    val fileContent: String,
    val sampleTestcases: List<ProblemTestcase>,
    val hiddenTestcases: List<ProblemTestcase>
)