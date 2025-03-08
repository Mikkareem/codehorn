package com.techullurgy.codehorn.domain.code.execution.services.utils

import java.io.BufferedReader
import java.io.InputStreamReader

internal fun Process.getOutputs(): List<String> {
    val outputReader = BufferedReader(InputStreamReader(inputStream))

    var line: String? = ""

    val outputs = mutableListOf<String>()

    do {
        line = outputReader.readLine()
        if(line == null) {
            break
        }
        if(line.isNotBlank()) {
            outputs.add(line)
        }
    } while(true)

    return outputs
}

internal fun Process.getErrors(): String {
    val outputReader = BufferedReader(InputStreamReader(errorStream))

    var line: String? = ""

    val builder = StringBuilder()

    do {
        line = outputReader.readLine()
        if(line == null) {
            break
        }
        if(line.isNotBlank()) {
            builder.append(line)
            builder.append("\n")
        }
    } while(true)

    return builder.toString()
}