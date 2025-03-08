package com.techullurgy.codehorn.problems.data.mappers

import com.techullurgy.codehorn.common.web.dto.TestcaseDTO
import com.techullurgy.codehorn.common.web.dto.TestcaseInputDTO
import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.problems.data.entities.Testcase

fun Testcase.toTestcaseDTO() = TestcaseDTO(
    id = testcaseId,
    isHidden = isHidden,
    inputs = inputs.map {
        TestcaseInputDTO(
            value = it.value,
            name = it.format!!.name,
        )
    }
)

fun Testcase.toProblemTestcase() = ProblemTestcase(
    id = "$testcaseId",
    isHidden = isHidden,
    inputs = inputs.map { it.value },
    inputNames = inputs.map { it.format!!.name },
    masks = inputs.map { it.format!!.testcaseType },
)