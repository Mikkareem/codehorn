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
    val creplaceStr: String,
    @Column(columnDefinition = "TEXT")
    val cpp: String,
    val cppReplaceStr: String,
    @Column(columnDefinition = "TEXT")
    val java: String,
    val javaReplaceStr: String,
    @Column(columnDefinition = "TEXT")
    val python: String,
    val pythonReplaceStr: String,
    @Column(columnDefinition = "TEXT")
    val javascript: String,
    val javascriptReplaceStr: String,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "file_content_problem_id")
    val problem: Problem? = null
) {
    override fun toString(): String {
        return "FileContent(javascriptReplaceStr='$javascriptReplaceStr', javascript='$javascript', pythonReplaceStr='$pythonReplaceStr', python='$python', javaReplaceStr='$javaReplaceStr', java='$java', cppReplaceStr='$cppReplaceStr', cpp='$cpp', creplaceStr='$creplaceStr', c='$c', id=$id)"
    }
}