package com.techullurgy.codehorn.common.web.mappers

import com.techullurgy.codehorn.common.model.CodeSubmissionResult
import com.techullurgy.codehorn.common.model.TestcaseResult
import com.techullurgy.codehorn.common.web.dto.TestcaseInputDTO
import com.techullurgy.codehorn.common.web.dto.TestcaseResultDTO
import com.techullurgy.codehorn.common.web.model.ResultDetail

fun TestcaseResult.toTestcaseResultDTO() = TestcaseResultDTO(
    testcaseId = testcase.id,
    inputs = testcase.inputNames.mapIndexed { index, it ->
        TestcaseInputDTO(
            name = it,
            value = testcase.inputs[index]
        )
    },
    result = result,
    details = when(result) {
        CodeSubmissionResult.CompilationError -> ResultDetail.CompilationErrorResult(compilationError)
        CodeSubmissionResult.TimeLimitExceeded -> ResultDetail.TimeLimitExceededResult
        CodeSubmissionResult.WrongAnswer -> ResultDetail.WrongAnswerResult(expectedResult, yourResult, stdout)
        CodeSubmissionResult.Accepted -> ResultDetail.AcceptedResult(expectedResult, yourResult, stdout)
        CodeSubmissionResult.RuntimeError -> ResultDetail.RuntimeErrorResult(stdout, stderr)
        CodeSubmissionResult.NotExecuted -> ResultDetail.NotExecutedResult
    }
)