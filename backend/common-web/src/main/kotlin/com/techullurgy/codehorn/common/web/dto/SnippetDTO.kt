package com.techullurgy.codehorn.common.web.dto

data class SnippetDTO(
    val id: Long,
    val c: String,
    val cpp: String,
    val java: String,
    val python: String,
    val javascript: String
)
