package com.phone.controller;

import java.util.List;

import com.phone.model.Phone;
import com.phone.model.PhoneBookRequest;
import com.phone.model.PhoneBookResponse;
import com.phone.model.PhoneDetailsDto;
import com.phone.model.PhoneDto;
import com.phone.service.PhoneBookService;
import com.phone.service.PhoneDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PhoneBookController {

  private final PhoneBookService phoneBookService;

  @GetMapping("/phone")
  public List<PhoneDto> getAllPhones() {
    return phoneBookService.getListOfPhones();
  }

  @GetMapping("/phone/details")
  public PhoneDetailsDto getPhoneDetails(@RequestParam("name") String phoneName) {
    return phoneBookService.getPhoneDetails(phoneName);
  }

  @PostMapping("/phone/book")
  public PhoneBookResponse bookPhone(@RequestBody PhoneBookRequest details) {
    return phoneBookService.bookPhone(details);
  }

  @PutMapping("/phone/{book_id}/return")
  public PhoneBookResponse returnPhone(@PathVariable("book_id") int bookId) {
    return phoneBookService.returnPhone(bookId);
  }
}
