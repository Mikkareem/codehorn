package com.techullurgy.codehorn.problems.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

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

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "snippet_problem_id")
    val problem: Problem? = null
) {
    override fun toString(): String {
        return "Snippet(javascript='$javascript', python='$python', java='$java', cpp='$cpp', c='$c', id=$id)"
    }
}