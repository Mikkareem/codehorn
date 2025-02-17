package com.techullurgy.codehorn.submissions.data.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne


@Entity
data class WrongAnswerProps(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val totalTestcases: Int,
    val executedTestcases: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "failed_testcase_id")
    val failedTestcase: FailedTestcase
)