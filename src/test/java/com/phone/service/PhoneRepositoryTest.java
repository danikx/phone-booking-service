package com.phone.service;

import java.util.Optional;

import com.phone.model.Phone;
import com.phone.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/phones.sql")
public class PhoneRepositoryTest {

  @Autowired
  private PhoneRepository phoneRepository;

  @Test
  public void shouldFindOnePhones() {
    Optional<Phone> phone = phoneRepository.findPhoneByPhoneName("Nokia 3310");
    assertTrue(phone.isPresent());
  }
}
