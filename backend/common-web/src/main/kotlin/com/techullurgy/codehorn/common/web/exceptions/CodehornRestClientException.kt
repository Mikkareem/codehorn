package com.techullurgy.codehorn.common.web.exceptions

import org.springframework.http.HttpStatusCode

class CodehornRestClientException(
    internal val exceptionBody: String,
    internal val status: HttpStatusCode
): RuntimeException()