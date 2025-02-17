package com.techullurgy.codehorn.submissions.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class AcceptedProps(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val executionTime: Long,
    val memoryConsumption: Long
)
