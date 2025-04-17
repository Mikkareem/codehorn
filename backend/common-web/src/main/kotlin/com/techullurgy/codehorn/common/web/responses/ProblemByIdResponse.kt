package com.techullurgy.codehorn.common.web.responses

import com.techullurgy.codehorn.common.annotations.InternalNetworkModel
import com.techullurgy.codehorn.common.web.dto.FileContentDTO
import com.techullurgy.codehorn.common.web.dto.SnippetDTO
import com.techullurgy.codehorn.common.model.Difficulty
import com.techullurgy.codehorn.common.model.ProblemTestcase

@InternalNetworkModel
data class ProblemByIdResponse(
    val id: String? = null,
    val title: String,
    val description: String,
    val difficulty: Difficulty,
    val problemNo: Int,
    val snippet: SnippetDTO,
    val fileContent: FileContentDTO,
    val testcases: List<ProblemTestcase>
)