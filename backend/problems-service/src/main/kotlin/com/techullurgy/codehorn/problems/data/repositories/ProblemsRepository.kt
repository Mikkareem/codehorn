package com.techullurgy.codehorn.problems.data.repositories

import com.techullurgy.codehorn.problems.data.entities.Problem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProblemsRepository: JpaRepository<Problem, String>