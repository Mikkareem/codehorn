package com.techullurgy.codehorn.submissions.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class FailedTestcaseInput(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    val input: String
)