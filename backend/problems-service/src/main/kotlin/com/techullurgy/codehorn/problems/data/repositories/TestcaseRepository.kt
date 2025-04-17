package com.techullurgy.codehorn.problems.data.repositories

import com.techullurgy.codehorn.problems.data.entities.Testcase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestcaseRepository: JpaRepository<Testcase, Long>