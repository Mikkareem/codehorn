package com.techullurgy.codehorn.gateway.utils

import org.springframework.security.core.context.SecurityContextHolder

fun getCurrentAuthenticatedUser(): String {
    return SecurityContextHolder.getContext().authentication?.name ?: ""
}