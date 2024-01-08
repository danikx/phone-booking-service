package com.phone.exceptions;

public class PhoneBookingNotFoundException extends BaseException {
  public PhoneBookingNotFoundException() {
    super("Phone booking not found");
  }
}
