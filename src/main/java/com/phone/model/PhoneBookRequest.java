package com.phone.model;

import lombok.Data;

@Data
public class PhoneBookRequest {
  private String phoneName;
  private String bookedBy;
  private int count;
}