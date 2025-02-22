package com.techullurgy.codehorn.gateway.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    @Bean
    fun securityFilterChain(configurer: DefaultSecurityFilterChainConfigurer): SecurityFilterChain {
        return configurer.configure()
    }
}