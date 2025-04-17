package com.techullurgy.codehorn.common.web.dto

data class TestcaseDTO(
    val id: Long = 0,
    val inputs: List<TestcaseInputDTO>,
    val isHidden: Boolean
)

data class TestcaseInputDTO(
    val value: String,
    val name: String,
)