package com.techullurgy.codehorn.problems.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class CodeTemplates(
    @Id
    @GeneratedValue
    val id: Int = 0,

    @Column(columnDefinition = "TEXT")
    val cUtils: String,

    @Column(columnDefinition = "TEXT")
    val cppUtils: String,

    @Column(columnDefinition = "TEXT")
    val javaUtils: String,

    @Column(columnDefinition = "TEXT")
    val pythonUtils: String,

    @Column(columnDefinition = "TEXT")
    val javascriptUtils: String,

    @Column(columnDefinition = "TEXT")
    val cMain: String,

    @Column(columnDefinition = "TEXT")
    val cppMain: String,

    @Column(columnDefinition = "TEXT")
    val javaMain: String,

    @Column(columnDefinition = "TEXT")
    val pythonMain: String,

    @Column(columnDefinition = "TEXT")
    val javascriptMain: String,

    val cImports: String,

    val cppImports: String,

    val javaImports: String,

    val pythonImports: String,

    val javascriptImports: String,
)