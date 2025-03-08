package com.techullurgy.codehorn.domain.code.execution.services

interface ExecutionParametersProvider {
    fun getCodeFileName(): String

    fun getLanguage(): String

    fun getCompiler(): String
}