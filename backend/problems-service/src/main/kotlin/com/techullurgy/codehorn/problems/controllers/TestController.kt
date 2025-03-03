package com.techullurgy.codehorn.problems.controllers

import com.techullurgy.codehorn.common.constants.ConsulConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
@RequestMapping("/test")
class TestController {
    @Autowired
    private lateinit var discoveryClient: DiscoveryClient

    @Autowired
    private lateinit var restClient: RestClient

    @GetMapping("/services")
    fun getServices(): MutableList<String?>? {
        return discoveryClient.services
    }

    @GetMapping
    fun debugRestClient(): ResponseEntity<String> {
        return restClient.get()
            .uri("http://${ConsulConstants.ServiceNames.SUBMISSIONS_SERVICE}/test")
            .retrieve()
            .toEntity(String::class.java)
    }
}