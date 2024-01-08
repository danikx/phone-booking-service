package com.phone.exceptions;

public class PhoneCountNotEnoughException extends BaseException {
  public PhoneCountNotEnoughException() {
    super("Not enough count of phones");
  }
}
