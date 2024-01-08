package com.phone.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDetails {
  private LocalDateTime phoneBookedAt;
  private String phoneBookedBy;
  private String status;
}
