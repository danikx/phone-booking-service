package com.phone.handler;

import com.phone.exceptions.BaseException;
import com.phone.model.PhoneBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<PhoneBookResponse> handleCustomException(BaseException ex) {
    PhoneBookResponse response = new PhoneBookResponse();
    response.setMessage(ex.getMessage());
    response.setBookId(-1);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
