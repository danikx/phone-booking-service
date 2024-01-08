package com.phone.service;

import java.util.List;

import com.phone.model.Phone;
import com.phone.model.PhoneBookRequest;
import com.phone.model.PhoneBookResponse;
import com.phone.model.PhoneDetailsDto;
import com.phone.model.PhoneDto;

public interface PhoneBookService {

  List<PhoneDto> getListOfPhones();

  PhoneBookResponse bookPhone(PhoneBookRequest phoneName);

  PhoneBookResponse returnPhone(int bookId);

  PhoneDetailsDto getPhoneDetails(String phoneName);
}
