package com.techullurgy.codehorn.submissions.controllers

import com.techullurgy.codehorn.common.web.requests.CodeRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/problems/{problemId}")
class SubmissionsController {

    @PostMapping("/run")
    fun runCode(
        @PathVariable("problemId") problemId: String,
        @RequestBody request: CodeRequest
    ) {

    }

    @PostMapping("/submit")
    fun submitCode(
        @PathVariable("problemId") problemId: String,
        @RequestBody request: CodeRequest
    ) {

    }
}