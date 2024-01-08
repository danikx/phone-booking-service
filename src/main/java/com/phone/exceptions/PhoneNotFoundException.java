package com.phone.exceptions;

public class PhoneNotFoundException extends BaseException {
  public PhoneNotFoundException() {
    super("Phone not found");
  }
}
