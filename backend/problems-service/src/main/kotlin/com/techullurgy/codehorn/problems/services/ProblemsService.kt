package com.techullurgy.codehorn.problems.services

import com.techullurgy.codehorn.problems.data.entities.Problem
import com.techullurgy.codehorn.problems.data.repositories.ProblemsRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProblemsService(
    private val problemsRepository: ProblemsRepository
) {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun getAllProblems(): List<Problem> {
        return problemsRepository.findAll()
    }

    @Transactional
    fun getProblemById(problemId: String): Problem {
        return problemsRepository.findById(problemId).orElseThrow { ProblemNotFound() }
    }

    @Transactional
    fun saveProblem(problem: Problem) {
        if (problem.problemNo == null) {
            val query = entityManager.createNativeQuery("SELECT nextval('problem_no_seq')")
            problem.problemNo = (query.singleResult as Number).toInt()
        }
        problemsRepository.save(problem)
    }
}

class ProblemNotFound: RuntimeException()