package com.techullurgy.codehorn.problems.services

import com.techullurgy.codehorn.common.exceptions.ProblemNotFoundException
import com.techullurgy.codehorn.common.exceptions.TestcaseFormatNotFoundException
import com.techullurgy.codehorn.problems.data.entities.Problem
import com.techullurgy.codehorn.problems.data.entities.Testcase
import com.techullurgy.codehorn.problems.data.repositories.FileContentRepository
import com.techullurgy.codehorn.problems.data.repositories.ProblemsRepository
import com.techullurgy.codehorn.problems.data.repositories.SnippetsRepository
import com.techullurgy.codehorn.problems.data.repositories.TestcaseFormatRepository
import com.techullurgy.codehorn.problems.data.repositories.TestcaseInputRepository
import com.techullurgy.codehorn.problems.data.repositories.TestcaseRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProblemsService(
    private val problemsRepository: ProblemsRepository,
    private val snippetsRepository: SnippetsRepository,
    private val fileContentRepository: FileContentRepository,
    private val testcaseFormatRepository: TestcaseFormatRepository,
    private val testcaseRepository: TestcaseRepository,
    private val testcaseInputRepository: TestcaseInputRepository
) {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    fun getAllProblems(): List<Problem> {
        return problemsRepository.findAll()
    }

    @Transactional
    fun getProblemById(problemId: String): Problem {
        return problemsRepository.findById(problemId).orElseThrow { ProblemNotFoundException() }
    }

    @Transactional
    fun saveProblem(problem: Problem): String? {
        if(problem.testcaseFormats.isEmpty()) {
            throw TestcaseFormatNotFoundException()
        }

        if (problem.problemNo == null) {
            val query = entityManager.createNativeQuery("SELECT nextval('problem_no_seq')")
            problem.problemNo = (query.singleResult as Number).toInt()
        }

        val snippets = problem.snippet!!
        val fileContents = problem.fileContent!!
        val testcaseFormats = problem.testcaseFormats

        val sp = problemsRepository.save(problem.copy(testcaseFormats = mutableListOf(), snippet = null, fileContent = null))

        val savedProblem = problemsRepository.findById(sp.id!!).get()

        val savedSnippet = snippetsRepository.save(snippets.copy(problem = savedProblem))
        val savedFileContent = fileContentRepository.save(fileContents.copy(problem = savedProblem))
        testcaseFormatRepository.saveAll(testcaseFormats.map { it.copy(problem = savedProblem) })

        savedProblem.snippet = savedSnippet
        savedProblem.fileContent = savedFileContent
        problemsRepository.save(savedProblem)

        return savedProblem.id
    }

    @Transactional
    fun saveTestcaseForProblem(testcase: Testcase, problemId: String): Long? {
        val testcaseInputs = testcase.inputs

        val problem = problemsRepository.findByIdOrNull(problemId) ?: return null

        val savedTestcase = testcaseRepository.save(testcase.copy(inputs = mutableListOf(), problem = problem))

        val formats = problem.testcaseFormats

        val savableInputs = testcaseInputs.mapIndexed { index, input ->
            input.copy(
                testcase = savedTestcase,
                format = formats[index]
            )
        }

        testcaseInputRepository.saveAll(savableInputs)

        return savedTestcase.testcaseId
    }

    @Transactional
    fun deleteAll() {
        problemsRepository.deleteAll()
    }
}
