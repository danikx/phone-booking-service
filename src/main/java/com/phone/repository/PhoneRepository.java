package com.phone.repository;

import java.util.Optional;

import com.phone.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
  Optional<Phone> findPhoneByPhoneName(String phoneName);
}