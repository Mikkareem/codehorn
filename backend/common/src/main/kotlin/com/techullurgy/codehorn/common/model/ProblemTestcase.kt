package com.techullurgy.codehorn.common.model

data class ProblemTestcase(
    val id: Long,
    val isHidden: Boolean = true,
    val input: List<String>,
    val masks: List<Long>
)