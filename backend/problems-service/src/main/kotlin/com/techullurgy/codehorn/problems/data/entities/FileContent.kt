package com.techullurgy.codehorn.problems.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
data class FileContent(
    @Id
    @GeneratedValue
    val id: Int = 0,
    @Column(columnDefinition = "TEXT")
    val c: String,
    @Column(columnDefinition = "TEXT")
    val cpp: String,
    @Column(columnDefinition = "TEXT")
    val java: String,
    @Column(columnDefinition = "TEXT")
    val python: String,
    @Column(columnDefinition = "TEXT")
    val javascript: String,

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

    @Column(columnDefinition = "TEXT", nullable = true)
    val cUtils: String? = null,
    @Column(columnDefinition = "TEXT", nullable = true)
    val cppUtils: String? = null,
    @Column(columnDefinition = "TEXT", nullable = true)
    val javaUtils: String? = null,
    @Column(columnDefinition = "TEXT", nullable = true)
    val pythonUtils: String? = null,
    @Column(columnDefinition = "TEXT", nullable = true)
    val javascriptUtils: String? = null,

    @Column(nullable = true)
    val cImports: String? = null,
    @Column(nullable = true)
    val cppImports: String? = null,
    @Column(nullable = true)
    val javaImports: String? = null,
    @Column(nullable = true)
    val pythonImports: String? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "file_content_problem_id")
    val problem: Problem? = null
) {
    override fun toString(): String {
        return "FileContent(pythonImports=$pythonImports, javaImports=$javaImports, cppImports=$cppImports, cImports=$cImports, javascriptUtils=$javascriptUtils, pythonUtils=$pythonUtils, javaUtils=$javaUtils, cppUtils=$cppUtils, cUtils=$cUtils, javascriptMain='$javascriptMain', pythonMain='$pythonMain', javaMain='$javaMain', cppMain='$cppMain', cMain='$cMain', javascript='$javascript', python='$python', java='$java', cpp='$cpp', c='$c', id=$id)"
    }
}