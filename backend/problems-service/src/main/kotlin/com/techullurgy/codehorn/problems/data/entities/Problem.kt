package com.techullurgy.codehorn.problems.data.entities

import com.techullurgy.codehorn.common.model.Difficulty
import jakarta.persistence.*

@Entity
data class Problem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val title: String,
    val description: String,
    val difficulty: Difficulty,

    @Column(unique = true, nullable = false)
    var problemNo: Int? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "snippet_id")
    val snippet: Snippet,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "file_content_id")
    val fileContent: FileContent,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "problem_id")
    val testcases: MutableList<Testcase> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "problem_id")
    val testcaseFormats: MutableList<TestcaseFormat> = mutableListOf()
)
