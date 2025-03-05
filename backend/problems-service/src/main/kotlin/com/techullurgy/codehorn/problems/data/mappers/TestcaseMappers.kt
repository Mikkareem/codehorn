package com.techullurgy.codehorn.problems.data.mappers

import com.techullurgy.codehorn.common.dto.TestcaseDTO
import com.techullurgy.codehorn.common.dto.TestcaseInputDTO
import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.problems.data.entities.Testcase

fun Testcase.toTestcaseDTO() = TestcaseDTO(
    id = testcaseId,
    isHidden = isHidden,
    inputs = inputs.map {
        TestcaseInputDTO(
            value = it.value,
            name = it.format!!.name,
            format = it.format!!.id!!
        )
    }
)

fun Testcase.toProblemTestcase() = ProblemTestcase(
    id = testcaseId,
    isHidden = isHidden,
    inputs = inputs.map { it.value },
    masks = inputs.map { it.format!!.testcaseType },
    parserCodes = inputs.map { it.format!!.parserCode }
)