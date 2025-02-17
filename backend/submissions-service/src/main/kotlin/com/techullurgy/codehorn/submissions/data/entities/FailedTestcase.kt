package com.techullurgy.codehorn.submissions.data.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
data class FailedTestcase(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val expectedOutput: String,
    val yourOutput: String,
    val stdout: String,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "failed_testcase_id")
    val inputs: List<FailedTestcaseInput>
)