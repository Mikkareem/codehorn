package com.techullurgy.codehorn.problems.data.mappers

import com.techullurgy.codehorn.common.dto.TestcaseDTO
import com.techullurgy.codehorn.common.dto.TestcaseInputDTO
import com.techullurgy.codehorn.common.dto.TestcaseInputDetailsDTO
import com.techullurgy.codehorn.common.mappers.toTypeMask
import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseInputCollectionType
import com.techullurgy.codehorn.common.model.TestcaseInputType
import com.techullurgy.codehorn.common.model.TestcaseType
import com.techullurgy.codehorn.problems.data.entities.Testcase
import com.techullurgy.codehorn.problems.data.entities.TestcaseFormat
import com.techullurgy.codehorn.problems.data.entities.TestcaseInput

fun TestcaseInputDetailsDTO.toTestcaseFormat() = TestcaseFormat(
    name = name,
    displayOrder = displayOrder,
    typeMask = type.toTypeMask() or collectionType.toTypeMask()
)

fun TestcaseFormat.toTestcaseInputDetailsDTO() = TestcaseInputDetailsDTO(
    name = name,
    displayOrder = displayOrder,
    type = when {
        (typeMask and TestcaseType.STRING_TYPE) != 0L -> TestcaseInputType.STRING
        else -> TestcaseInputType.NON_STRING
    },
    collectionType = when {
        (typeMask and TestcaseType.LIST_TYPE) != 0L -> TestcaseInputCollectionType.LIST
        (typeMask and TestcaseType.LIST_LIST_TYPE) != 0L -> TestcaseInputCollectionType.LIST_LIST
        else -> TestcaseInputCollectionType.SINGLE
    }
)

fun Testcase.toProblemTestcase() = ProblemTestcase(
    id = testcaseId,
    isHidden = isHidden,
    input = inputs.map { it.value },
    masks = inputs.map { it.format.typeMask }
)

fun Testcase.toTestcaseDTO() = TestcaseDTO(
    id = testcaseId,
    isHidden = isHidden,
    inputs = inputs.map {
        TestcaseInputDTO(
            value = it.value,
            details = it.format.toTestcaseInputDetailsDTO()
        )
    }
)

fun TestcaseDTO.toTestcase() = Testcase(
    testcaseId = id,
    isHidden = isHidden,
).apply {
    val newDetails = this@toTestcase.inputs.map {
        TestcaseInput(
            value = it.value,
            format = it.details.toTestcaseFormat()
        )
    }
    inputs.clear()
    inputs.addAll(newDetails)
}