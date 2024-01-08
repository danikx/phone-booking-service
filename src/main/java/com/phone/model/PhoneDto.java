package com.phone.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phone.handler.BooleanToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PhoneDto {
  private String phoneName;
  @JsonSerialize(using = BooleanToStringSerializer.class)
  private boolean available;
  private PhoneDetails phoneDetails;
}
