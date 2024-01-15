package com.phone.exceptions;

import org.springframework.http.HttpStatus;

public class PhoneCountNotEnoughException extends BaseException {
  public PhoneCountNotEnoughException() {
    super("Not enough count of phones");
  }

  @Override
  public HttpStatus getCode() {
    return HttpStatus.BAD_REQUEST;
  }
}
