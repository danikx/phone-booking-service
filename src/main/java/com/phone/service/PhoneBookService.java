package com.phone.service;

import java.util.List;

import com.phone.model.PhoneBookRequestDto;
import com.phone.model.PhoneBookResponseDto;
import com.phone.model.PhoneDetailsDto;
import com.phone.model.PhoneDto;

public interface PhoneBookService {

  List<PhoneDto> getListOfPhones();

  PhoneBookResponseDto bookPhone(PhoneBookRequestDto phoneName);

  PhoneBookResponseDto returnPhone(int bookId);

  PhoneDetailsDto getPhoneDetails(String phoneName);
}
