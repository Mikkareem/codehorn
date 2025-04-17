package com.techullurgy.codehorn.common.web.dto

import com.techullurgy.codehorn.common.model.Difficulty

data class ProblemDTO(
    val problemId: String,
    val problemNo: Int,
    val title: String,
    val difficulty: Difficulty,
    val description: String,
    val preferredSnippet: String,
    val preferredLanguage: String,
    val sampleTestcases: List<TestcaseDTO>
)