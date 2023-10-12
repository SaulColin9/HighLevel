package com.generative.ai.highlevel.service;
import com.generative.ai.highlevel.ExpediaApiConfig;
import com.generative.ai.highlevel.dto.ExpediaApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ExpediaApiService {
    private final WebClient webClient;
    private final ExpediaApiConfig apiConfig;

    public ExpediaApiService(WebClient.Builder webClientBuilder, ExpediaApiConfig apiConfig) {
        this.webClient = webClientBuilder.baseUrl(apiConfig.getBaseUrl()).build();
        this.apiConfig = apiConfig;
    }

    public Mono<ExpediaApiResponse> fetchExpediaData() {
        return webClient
                .get()
                .uri("/your-api-endpoint")  // Replace with the actual Expedia API endpoint
                .headers(httpHeaders -> httpHeaders.set("Authorization", "Bearer " + apiConfig.getApiKey()))
                .retrieve()
                .bodyToMono(ExpediaApiResponse.class);
    }

    public Mono<ExpediaApiResponse> searchFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/search")
                        .queryParam("origin", origin)
                        .queryParam("destination", destination)
                        .queryParam("departureDate", departureDate)
                        .queryParam("returnDate", returnDate)
                        .build())
                .headers(httpHeaders -> httpHeaders.set("Authorization", "Bearer " + apiConfig.getApiKey()))
                .retrieve()
                .bodyToMono(ExpediaApiResponse.class);
    }

    public Mono<ExpediaApiResponse> searchHotels(String destination, LocalDate checkInDate, LocalDate checkOutDate) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/hotels/search")
                        .queryParam("destination", destination)
                        .queryParam("checkInDate", checkInDate)
                        .queryParam("checkOutDate", checkOutDate)
                        .build())
                .headers(httpHeaders -> httpHeaders.set("Authorization", "Bearer " + apiConfig.getApiKey()))
                .retrieve()
                .bodyToMono(ExpediaApiResponse.class);
    }

    public Mono<ExpediaApiResponse> searchRentalCars(String location, LocalDate pickupDate, LocalDate returnDate) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rentalcars/search")
                        .queryParam("location", location)
                        .queryParam("pickupDate", pickupDate)
                        .queryParam("returnDate", returnDate)
                        .build())
                .headers(httpHeaders -> httpHeaders.set("Authorization", "Bearer " + apiConfig.getApiKey()))
                .retrieve()
                .bodyToMono(ExpediaApiResponse.class);
    }
}
