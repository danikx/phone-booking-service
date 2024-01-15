package com.phone.exceptions;

import org.springframework.http.HttpStatus;

public class PhoneBookingNotFoundException extends BaseException {

  public PhoneBookingNotFoundException() {
    super("Phone booking not found");
  }

  @Override
  public HttpStatus getCode() {
    return HttpStatus.NOT_FOUND;
  }
}
