package com.techullurgy.codehorn.domain.code.execution.services

import java.io.Closeable
import java.io.File

class UserFileCreator(
    submissionId: String,
    language: String
): Closeable {
    val file = File("temp/$language/$submissionId").apply { mkdirs() }

    override fun close() {
        file.deleteRecursively()
    }
}