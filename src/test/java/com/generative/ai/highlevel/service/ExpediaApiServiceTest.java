package com.generative.ai.highlevel.service;

import com.generative.ai.highlevel.ExpediaApiConfig;
import com.generative.ai.highlevel.dto.ExpediaApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.time.LocalDate;

public class ExpediaApiServiceTest {

    @InjectMocks
    private ExpediaApiService expediaApiService;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private ExpediaApiConfig apiConfig;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock the behavior of WebClient
        WebClient webClient = Mockito.mock(WebClient.class);
        Mockito.when(webClientBuilder.baseUrl(Mockito.anyString())).thenReturn(webClientBuilder);
        Mockito.when(webClientBuilder.build()).thenReturn(webClient);
        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri((URI) Mockito.any())).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.headers(Mockito.any())).thenReturn((WebClient.RequestHeadersSpec) responseSpec);
    }

    @Test
    public void testSearchFlights() {
        // Define a sample response
        ExpediaApiResponse sampleResponse = new ExpediaApiResponse(/* Set response data */);

        // Mock the API configuration
        Mockito.when(apiConfig.getBaseUrl()).thenReturn("https://api.expedia.com");
        Mockito.when(apiConfig.getApiKey()).thenReturn("your-api-key");

        // Mock the response from the Expedia API
        Mockito.when(responseSpec.bodyToMono(ExpediaApiResponse.class)).thenReturn(Mono.just(sampleResponse));

        // Call the method under test
        Mono<ExpediaApiResponse> result = expediaApiService.searchFlights("origin", "destination", LocalDate.now(), LocalDate.now());

        // Verify the result
        StepVerifier.create(result)
                .expectNext(sampleResponse)
                .expectComplete()
                .verify();
    }
}
