package com.techullurgy.codehorn.gateway.configuration.security

import com.techullurgy.codehorn.gateway.configuration.security.jwt.JwtFilter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class DefaultSecurityFilterChainConfigurer(
    private val authEntryPoint: AuthenticationEntryPoint,
    private val jwtFilter: JwtFilter,
    private val http: HttpSecurity
) {
    fun configure(): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(authEntryPoint) }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}