package com.techullurgy.codehorn.problems.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Snippet(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val c: String,
    val cpp: String,
    val java: String,
    val python: String,
    val javascript: String,
)