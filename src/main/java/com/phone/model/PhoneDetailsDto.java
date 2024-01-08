package com.phone.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phone.handler.BooleanToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PhoneDetailsDto {
  private String phoneName;
  @JsonSerialize(using = BooleanToStringSerializer.class)
  private boolean available;
  private final List<BookDetails> bookDetails = new ArrayList<>();

}
