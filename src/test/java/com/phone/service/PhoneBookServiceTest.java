package com.phone.service;

import com.phone.Application;
import com.phone.model.Phone;
import com.phone.model.PhoneBookRequestDto;
import com.phone.model.PhoneBookResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("/phones.sql")
public class PhoneBookServiceTest {
  @Autowired
  protected TestRestTemplate restTemplate;

  @Test
  public void shouldBookPhone() {
    ParameterizedTypeReference<PhoneBookResponseDto> responseType = new ParameterizedTypeReference<>() {
    };

    PhoneBookRequestDto request = createPhoneBookRequest("me", "iPhone X", 1);
    HttpEntity<PhoneBookRequestDto> requestHttpEntity = new HttpEntity<>(request);
    ResponseEntity<PhoneBookResponseDto> bookPhoneResp = restTemplate.exchange(
        "/api/v1/phone/book",
        HttpMethod.POST,
        requestHttpEntity,
        responseType);

    assertNotNull(bookPhoneResp);
    assertNotNull(bookPhoneResp.getBody());
    assertEquals(200, bookPhoneResp.getStatusCode().value());
    assertEquals("Phone successfully booked", bookPhoneResp.getBody().getMessage());
  }

  @Test
  public void shouldFailBookPhoneNotEnoughPhoneCount() {
    ParameterizedTypeReference<PhoneBookResponseDto> responseType = new ParameterizedTypeReference<>() {
    };

    PhoneBookRequestDto request = createPhoneBookRequest("me", "iPhone X", 100);
    HttpEntity<PhoneBookRequestDto> requestHttpEntity = new HttpEntity<>(request);
    ResponseEntity<PhoneBookResponseDto> bookPhoneResp = restTemplate.exchange(
        "/api/v1/phone/book",
        HttpMethod.POST,
        requestHttpEntity,
        responseType);

    assertNotNull(bookPhoneResp);
    assertNotNull(bookPhoneResp.getBody());
    assertEquals(400, bookPhoneResp.getStatusCode().value());
    assertEquals("Not enough count of phones", bookPhoneResp.getBody().getMessage());
  }

  @Test
  public void shouldFailReturnPhoneBookingNotFound() {
    ParameterizedTypeReference<PhoneBookResponseDto> responseType = new ParameterizedTypeReference<>() {};

    ResponseEntity<PhoneBookResponseDto> response = restTemplate.exchange("/api/v1/phone/1000/return", HttpMethod.PUT, null, responseType);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(404, response.getStatusCode().value());
    assertEquals("Phone booking not found", response.getBody().getMessage());
  }

  @Test
  public void shouldReturnPhoneBooking() {
    ParameterizedTypeReference<PhoneBookResponseDto> responseType = new ParameterizedTypeReference<PhoneBookResponseDto>() {
    };

    // book the phone
    PhoneBookRequestDto request = createPhoneBookRequest("me", "iPhone X", 1);
    HttpEntity<PhoneBookRequestDto> requestHttpEntity = new HttpEntity<>(request);
    ResponseEntity<PhoneBookResponseDto> bookPhoneResp = restTemplate.exchange(
        "/api/v1/phone/book",
        HttpMethod.POST,
        requestHttpEntity,
        responseType);

    assertNotNull(bookPhoneResp);
    assertNotNull(bookPhoneResp.getBody());
    assertEquals(200, bookPhoneResp.getStatusCode().value());

    // return the phone
    ResponseEntity<PhoneBookResponseDto> response = restTemplate.exchange(
        "/api/v1/phone/" + bookPhoneResp.getBody().getBookId() + "/return",
        HttpMethod.PUT, null, responseType);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(200, response.getStatusCode().value());
    assertEquals("Phone successfully returned", response.getBody().getMessage());
  }

  private PhoneBookRequestDto createPhoneBookRequest(String whoBooked, String phoneName, int count) {
    PhoneBookRequestDto request = new PhoneBookRequestDto();
    request.setBookedBy(whoBooked);
    request.setPhoneName(phoneName);
    request.setCount(count);

    return request;
  }
}