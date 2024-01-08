package com.phone.service;

import java.util.List;

import com.phone.model.PhoneDetails;

public interface PhoneDetailsService {

  PhoneDetails getPhoneDetailsByName(String phoneName);
}