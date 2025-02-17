package com.techullurgy.codehorn.common.mappers

import com.techullurgy.codehorn.common.dto.TestcaseDTO
import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseInputCollectionType
import com.techullurgy.codehorn.common.model.TestcaseInputType
import com.techullurgy.codehorn.common.model.TestcaseType

fun TestcaseInputType.toTypeMask() = when(this) {
    TestcaseInputType.STRING -> TestcaseType.STRING_TYPE
    TestcaseInputType.NON_STRING -> TestcaseType.NON_STRING_TYPE
}

fun TestcaseInputCollectionType.toTypeMask() = when(this) {
    TestcaseInputCollectionType.LIST -> TestcaseType.LIST_TYPE
    TestcaseInputCollectionType.LIST_LIST -> TestcaseType.LIST_LIST_TYPE
    TestcaseInputCollectionType.SINGLE -> TestcaseType.SINGLE_TYPE
}

fun TestcaseDTO.toProblemTestcase() = ProblemTestcase(
    id = id,
    isHidden = isHidden,
    input = inputs.map { it.value },
    masks = inputs.map { it.details.type.toTypeMask() or it.details.collectionType.toTypeMask()  }
)