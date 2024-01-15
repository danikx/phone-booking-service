package com.phone.exceptions;

import org.springframework.http.HttpStatus;

public class PhoneNotFoundException extends BaseException {
  public PhoneNotFoundException() {
    super("Phone not found");
  }

  @Override
  public HttpStatus getCode() {
    return HttpStatus.NOT_FOUND;
  }
}
