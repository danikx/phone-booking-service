package com.phone.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PhoneBooking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int bookId;
  private int count = 1;

  @ManyToOne
  @JoinColumn(name = "phone_id")
  private Phone phone;
  private LocalDateTime bookedAt;
  private String bookedBy;
  @Enumerated(EnumType.STRING)
  private BookingStatusEnum status;
}
