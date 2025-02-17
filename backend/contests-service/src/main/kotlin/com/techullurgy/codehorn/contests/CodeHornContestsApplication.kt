package com.techullurgy.codehorn.contests

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CodeHornContestsApplication

fun main(args: Array<String>) {
    runApplication<CodeHornContestsApplication>(*args)
}