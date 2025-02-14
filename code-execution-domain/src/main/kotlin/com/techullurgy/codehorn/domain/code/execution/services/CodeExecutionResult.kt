package com.techullurgy.codehorn.domain.code.execution.services

sealed interface CodeExecutionResult {
    data object NotExecuted : CodeExecutionResult
    data class CompilationError(val error: String): CodeExecutionResult
    data object TimeLimitExceeded : CodeExecutionResult { val name = "Time Limit Exceeded" }
    data object Accepted : CodeExecutionResult
    data object WrongAnswer: CodeExecutionResult
    data class RuntimeError(val error: String) : CodeExecutionResult
}