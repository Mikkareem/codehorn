package com.techullurgy.codehorn.common.dto

import com.techullurgy.codehorn.common.model.TestcaseInputCollectionType
import com.techullurgy.codehorn.common.model.TestcaseInputType

data class TestcaseDTO(
    val id: Long = 0,
    val inputs: List<TestcaseInputDTO>,
    val isHidden: Boolean
)

data class TestcaseInputDTO(
    val value: String,
    val details: TestcaseInputDetailsDTO
)

data class TestcaseInputDetailsDTO(
    val name: String,
    val type: TestcaseInputType,
    val collectionType: TestcaseInputCollectionType,
    val displayOrder: Int
)