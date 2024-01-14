package com.phone.model;

import lombok.Data;

@Data
public class PhoneBookResponseDto {
  private static final String RETURNED = "Phone successfully returned";
  private static final String BOOKED = "Phone successfully booked";
  private int bookId;
  private String message;

  public static PhoneBookResponseDto successfullyReturned(int bookId) {
    return createResponse(RETURNED, bookId);
  }

  public static PhoneBookResponseDto successfullyBooked(int bookId) {
    return createResponse(BOOKED, bookId);
  }

  public static PhoneBookResponseDto createResponse(String message, int bookId) {
    PhoneBookResponseDto bookResponse = new PhoneBookResponseDto();
    bookResponse.setMessage(message);
    bookResponse.setBookId(bookId);

    return bookResponse;
  }
}