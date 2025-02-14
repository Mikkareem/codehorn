package com.techullurgy.codehorn.domain.code.execution.parsers

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseType

abstract class TestcaseParserStrategy {
    abstract fun parse(testcase: ProblemTestcase): String

    protected fun isStringType(mask: Long): Boolean = (mask and TestcaseType.STRING_TYPE) != 0L
    protected fun isNonStringType(mask: Long): Boolean = (mask and TestcaseType.NON_STRING_TYPE) != 0L

    protected fun isSingleType(mask: Long): Boolean = (mask and TestcaseType.SINGLE_TYPE) != 0L
    protected fun is1DList(mask: Long): Boolean = (mask and TestcaseType.LIST_TYPE) != 0L
    protected fun is2DList(mask: Long): Boolean = (mask and TestcaseType.LIST_LIST_TYPE) != 0L
}