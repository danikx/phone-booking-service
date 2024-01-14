package com.phone.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phone.handler.BooleanToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneDto {
  private String phoneName;
  @JsonSerialize(using = BooleanToStringSerializer.class)
  private boolean available;
  private PhoneTechDetailsDto phoneTechDetailsDto;
}
