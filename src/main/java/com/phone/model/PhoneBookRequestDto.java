package com.phone.model;

import lombok.Data;

@Data
public class PhoneBookRequestDto {
  private String phoneName;
  private String bookedBy;
  private int count;
}