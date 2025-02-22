package com.techullurgy.codehorn.gateway.configuration.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val path = request.requestURI

            if(path.startsWith("/auth/")) {
                filterChain.doFilter(request, response)
                return
            }

            val authHeader = request.getHeader("Authorization") ?: throw AuthenticationCredentialsNotFoundException("No Authorization Header")

            val token = if(authHeader.startsWith("Bearer ")) {
                authHeader.removePrefix("Bearer ")
            } else {
                throw AuthenticationCredentialsNotFoundException("We accept only Bearer Authentication")
            }

            if(token == "12345678") {
                val user = User.withUsername("Irsath")
                    .password("")
                    .roles("ADMIN").build()
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken.authenticated(user, null, user.authorities)
            }

            filterChain.doFilter(request, response)
        } catch (e: AuthenticationException) {
            response.contentType = "application/json"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer?.write("""{ "status": "Unauthorized", "error": "${e.message}" }""")
        } catch(e: Exception) {
            throw e
        }
    }
}