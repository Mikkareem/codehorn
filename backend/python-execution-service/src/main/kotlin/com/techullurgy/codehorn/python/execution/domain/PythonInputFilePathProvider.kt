package com.techullurgy.codehorn.python.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class PythonInputFilePathProvider(private val submissionId: String): InputFilePathProvider {
    override fun provide() = "temp/python/$submissionId"
}