package com.phone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long phoneId;
  private String phoneName;
  private int count;
  @Column(name = "total")
  private int totalPhoneCount;

  public void increaseCount() {
    if (count + 1 > totalPhoneCount) {
      throw new IllegalStateException("current count of phones can't be more than total count");
    }
    count = count + 1;
  }

  public void decreasePhoneCount(int c) {
    if (count - c < 0) {
      throw new IllegalStateException("current amount of phones can't be less than zero");
    }
    count = count - c;
  }

  public boolean isAvailable() {
    return count >= 1;
  }

  public boolean isEnoughPhoneCount(int phoneCount) {
    return count >= phoneCount;
  }
}
