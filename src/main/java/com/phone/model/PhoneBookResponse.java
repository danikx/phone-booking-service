package com.phone.model;

import lombok.Data;

@Data
public class PhoneBookResponse {
  private static final String RETURNED = "Phone successfully returned";
  private static final String BOOKED = "Phone successfully booked";
  private int bookId;
  private String message;

  public static PhoneBookResponse successfullyReturned(int bookId) {
    return createResponse(RETURNED, bookId);
  }

  public static PhoneBookResponse successfullyBooked(int bookId) {
    return createResponse(BOOKED, bookId);
  }

  public static PhoneBookResponse createResponse(String message, int bookId) {
    PhoneBookResponse bookResponse = new PhoneBookResponse();
    bookResponse.setMessage(message);
    bookResponse.setBookId(bookId);

    return bookResponse;
  }
}