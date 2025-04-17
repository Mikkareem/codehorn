package com.techullurgy.codehorn.common.web.dto

data class FileContentDTO(
    val id: Int = 0,
    val c: String,
    val creplaceStr: String,
    val cpp: String,
    val cppReplaceStr: String,
    val java: String,
    val javaReplaceStr: String,
    val python: String,
    val pythonReplaceStr: String,
    val javascript: String,
    val javascriptReplaceStr: String
)
