package com.techullurgy.codehorn.submissions.data.entities

import com.techullurgy.codehorn.common.model.CodeSubmissionResult
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Submission(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val language: String,
    val code: String,
    val verdict: CodeSubmissionResult,
    val time: LocalDateTime,

    val problem: String,
    val userId: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "accepted_props_id", nullable = true)
    val acceptedProps: AcceptedProps? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "wrong_props_id", nullable = true)
    val wrongAnswerProps: WrongAnswerProps? = null
)