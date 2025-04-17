package com.techullurgy.codehorn.common.web.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ResultDetail.AcceptedResult::class, name = "accepted"),
    JsonSubTypes.Type(value = ResultDetail.WrongAnswerResult::class, name = "wrong_answer"),
    JsonSubTypes.Type(value = ResultDetail.RuntimeErrorResult::class, name = "runtime_error"),
    JsonSubTypes.Type(value = ResultDetail.CompilationErrorResult::class, name = "compilation_error"),
    JsonSubTypes.Type(value = ResultDetail.NotExecutedResult::class, name = "not_executed"),
    JsonSubTypes.Type(value = ResultDetail.TimeLimitExceededResult::class, name = "time_limit_exceeded")
)
sealed interface ResultDetail {
    data class AcceptedResult(
        val expectedResult: String,
        val yourResult: String,
        val stdout: String
    ): ResultDetail

    data class WrongAnswerResult(
        val expectedResult: String,
        val yourResult: String,
        val stdout: String
    ): ResultDetail

    data class RuntimeErrorResult(
        val stdout: String,
        val stderr: String
    ): ResultDetail

    data class CompilationErrorResult(
        val compilationError: String
    ): ResultDetail

    data object NotExecutedResult: ResultDetail
    data object TimeLimitExceededResult: ResultDetail
}