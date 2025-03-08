package com.techullurgy.codehorn.common.model

enum class CodeSubmissionResult {
    CompilationError, TimeLimitExceeded, WrongAnswer, Accepted, RuntimeError, NotExecuted;

    fun isResultExists() = this == Accepted || this == WrongAnswer

    fun isStandardOutExists() = this != CompilationError && this != NotExecuted
}