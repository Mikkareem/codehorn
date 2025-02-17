package com.techullurgy.codehorn.submissions.data.repository

import com.techullurgy.codehorn.submissions.data.entities.Submission
import org.springframework.data.jpa.repository.JpaRepository

interface SubmissionsRepository: JpaRepository<Submission, Long>