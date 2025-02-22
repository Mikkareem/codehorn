package com.techullurgy.codehorn.gateway.configuration.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.Date
import javax.crypto.SecretKey

class JwtGenerator {
    private val secretKeyStr = "SECRET_KEY"

    fun generateToken(authentication: Authentication): String? {
        val username = authentication.name
        return generateToken(username)
    }

    fun generateToken(username: String?): String? {
        val currentDate = Date(Instant.now().toEpochMilli())
        val expirationDate = Date(currentDate.time + (3 * 60 * 60 * 1000))

        return Jwts.builder()
            .subject(username)
            .issuer("self")
            .issuedAt(Date())
            .expiration(expirationDate)
            .signWith<SecretKey?>(getSigningKey(), Jwts.SIG.HS512)
            .compact()
    }

    fun getUsernameFromToken(token: String?): String? {
        val claims = Jwts.parser()
            .sig().add(Jwts.SIG.HS512).and()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
        return claims.subject
    }

    private fun getSigningKey(): SecretKey {
        val keyBytes = secretKeyStr.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}