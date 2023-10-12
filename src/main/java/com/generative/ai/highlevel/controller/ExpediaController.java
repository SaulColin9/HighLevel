package com.generative.ai.highlevel.controller;

import com.generative.ai.highlevel.dto.ExpediaApiResponse;
import com.generative.ai.highlevel.service.ExpediaApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/expedia")
public class ExpediaController {
    private final ExpediaApiService expediaApiService;

    public ExpediaController(ExpediaApiService expediaApiService) {
        this.expediaApiService = expediaApiService;
    }

    @GetMapping("/data")
    public Mono<ExpediaApiResponse> fetchDataFromExpedia() {
        return expediaApiService.fetchExpediaData();
    }
}
