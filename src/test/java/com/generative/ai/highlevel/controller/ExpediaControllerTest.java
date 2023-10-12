package com.generative.ai.highlevel.controller;
import com.generative.ai.highlevel.dto.ExpediaApiResponse;
import com.generative.ai.highlevel.service.ExpediaApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class ExpediaControllerTest {

    @InjectMocks
    private ExpediaController expediaController;

    @Mock
    private ExpediaApiService expediaApiService;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        webTestClient = WebTestClient
                .bindToController(expediaController)
                .configureClient()
                .baseUrl("/expedia")
                .build();
    }

    @Test
    public void testFetchDataFromExpedia() {
        // Define a sample response
        ExpediaApiResponse sampleResponse = new ExpediaApiResponse(/* Set response data */);

        // Mock the service method to return the sample response
        Mockito.when(expediaApiService.fetchExpediaData()).thenReturn(Mono.just(sampleResponse));

        // Perform the GET request to the controller endpoint
        webTestClient.get()
                .uri("/data")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ExpediaApiResponse.class)
                .isEqualTo(sampleResponse);
    }
}
