package com.phone.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDetails {
  private String technology;
  private boolean band2G;
  private boolean band3G;
  private boolean band4G;
}
