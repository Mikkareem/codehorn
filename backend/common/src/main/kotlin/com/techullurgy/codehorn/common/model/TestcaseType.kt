package com.techullurgy.codehorn.common.model

object TestcaseType {
    const val STRING_TYPE = 1L shl 10
    const val NON_STRING_TYPE = 1L shl 12
    const val SINGLE_TYPE = 1L shl 14
    const val LIST_TYPE = 1L shl 18
    const val LIST_LIST_TYPE = 1L shl 20
}

enum class TestcaseInputType {
    STRING, NON_STRING
}

enum class TestcaseInputCollectionType {
    LIST, LIST_LIST, SINGLE
}