package com.techullurgy.codehorn.common.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FileContentDTO(
    val id: Int = 0,
    val c: String,
    val cpp: String,
    val java: String,
    val python: String,
    val javascript: String,
    @JsonProperty(value = "cmain") val cMain: String,
    val cppMain: String,
    val javaMain: String,
    val pythonMain: String,
    val javascriptMain: String,
    @JsonProperty(value = "cutils") val cUtils: String,
    val cppUtils: String,
    val javaUtils: String,
    val pythonUtils: String,
    val javascriptUtils: String,
    @JsonProperty(value = "cimports") val cImports: String,
    val cppImports: String,
    val javaImports: String,
    val pythonImports: String,
    val javascriptImports: String,
)
