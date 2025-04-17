package com.techullurgy.codehorn.java.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class JavaInputFilePathProvider(private val submissionId: String): InputFilePathProvider {
    override fun provide() = "temp/java/$submissionId"
}