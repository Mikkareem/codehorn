package com.techullurgy.codehorn.common.model

data class ProblemTestcase(
    val id: Long,
    val isHidden: Boolean = true,
    val inputs: List<String>,
    val masks: List<Long>,
    val parserCodes: List<String>,
)