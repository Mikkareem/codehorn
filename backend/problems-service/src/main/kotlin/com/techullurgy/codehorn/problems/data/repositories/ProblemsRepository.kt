package com.techullurgy.codehorn.problems.data.repositories

import com.techullurgy.codehorn.problems.data.entities.Problem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProblemsRepository: JpaRepository<Problem, String> {
    fun findByProblemNo(number: Int): Optional<Problem>
}