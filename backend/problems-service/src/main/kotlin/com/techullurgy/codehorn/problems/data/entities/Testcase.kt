package com.techullurgy.codehorn.problems.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
data class Testcase(
    @Id
    @GeneratedValue
    val testcaseId: Long = 0,

    val isHidden: Boolean = true,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "problem_id")
    @JsonIgnore
    val problem: Problem? = null,

    @OneToMany(mappedBy = "testcase", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val inputs: MutableList<TestcaseInput> = mutableListOf()
) {
    override fun toString(): String {
        return "Testcase(id=$testcaseId, isHidden=$isHidden, problem=${problem?.id}, inputs=${this@Testcase.inputs})"
    }
}


@Entity
data class TestcaseInput(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @Column(name = "testcase_value")
    val value: String,

    @ManyToOne
    @JoinColumn(name = "format_id")
    val format: TestcaseFormat? = null,

    @ManyToOne
    @JoinColumn(name = "testcase_id")
    @JsonIgnore
    val testcase: Testcase? = null
) {
    override fun toString(): String {
        return "TestcaseInput(id=$id, value=$value, format=${format?.id}, testcase=${testcase?.testcaseId})"
    }
}

@Entity
data class TestcaseFormat(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    val name: String,

    val displayOrder: Int,

    val testcaseType: Long,

    @Column(columnDefinition = "TEXT")
    val parserCode: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "problem_id", nullable = false)
    @JsonIgnore
    val problem: Problem? = null
) {
    override fun toString(): String {
        return "TestcaseFormat(id=$id, name=$name, displayOrder=$displayOrder, parserCode=$parserCode, problem=${problem?.id})"
    }
}