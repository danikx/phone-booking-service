package com.phone.service;

import com.phone.model.PhoneTechDetailsDto;

public interface PhoneDetailsService {

  PhoneTechDetailsDto getPhoneDetailsByName(String phoneName);
}