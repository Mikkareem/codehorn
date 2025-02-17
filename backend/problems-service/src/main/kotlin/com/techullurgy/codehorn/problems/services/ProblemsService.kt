package com.techullurgy.codehorn.problems.services

import com.techullurgy.codehorn.problems.data.entities.Problem
import com.techullurgy.codehorn.problems.data.repositories.ProblemsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProblemsService(
    private val problemsRepository: ProblemsRepository
) {

    @Transactional
    fun getProblemById(problemId: String): Problem {
        return problemsRepository.findById(problemId).orElseThrow { ProblemNotFound() }
    }
}

class ProblemNotFound: RuntimeException()