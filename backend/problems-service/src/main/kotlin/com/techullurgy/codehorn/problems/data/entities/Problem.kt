package com.techullurgy.codehorn.problems.data.entities

import com.techullurgy.codehorn.common.model.Difficulty
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

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

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "snippet_id", foreignKey = ForeignKey(name = "fk_problem_snippet"))
    var snippet: Snippet? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "file_content_id", foreignKey = ForeignKey(name = "fk_problem_file_content"))
    var fileContent: FileContent? = null,

    @OneToMany(mappedBy="problem", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val testcases: MutableList<Testcase> = mutableListOf(),

    @OneToMany(mappedBy="problem", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val testcaseFormats: MutableList<TestcaseFormat> = mutableListOf()
)
