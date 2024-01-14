package com.phone.service;

import com.phone.model.PhoneTechDetailsDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PhoneDetailsServiceImpl implements PhoneDetailsService {

  private final RestClient restClient;

  @Value("${phone-booking.service.fonoapi}")
  private String fonapiUrl;

  @CircuitBreaker(name = "fonoapi_service", fallbackMethod = "phoneDetailsFallback")
  public PhoneTechDetailsDto getPhoneDetailsByName(String phoneName) {
    ResponseEntity<String> result = restClient.get()
        .uri(fonapiUrl)
        .retrieve()
        .toEntity(String.class);

    if (result.getStatusCode().is2xxSuccessful()) {
      String body = result.getBody();
      throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    return PhoneTechDetailsDto.builder().band2G(true).band3G(true).band4G(true).technology("empty").build();
  }

  public PhoneTechDetailsDto phoneDetailsFallback(String phoneName, Throwable ex) {
    return null;
  }
}