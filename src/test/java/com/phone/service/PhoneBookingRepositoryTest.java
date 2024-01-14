package com.phone.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.phone.model.Phone;
import com.phone.model.PhoneBooking;
import com.phone.repository.PhoneBookRepository;
import com.phone.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/phones.sql")
public class PhoneBookingRepositoryTest {

  @Autowired
  private PhoneBookRepository phoneBookRepository;

  @Autowired
  private PhoneRepository phoneRepository;

  @Test
  public void shouldCreatePhoneBooking() {
    Optional<Phone> phoneOpt = phoneRepository.findPhoneByPhoneName("Nokia 3310");
    assertTrue(phoneOpt.isPresent());

    PhoneBooking booking = new PhoneBooking();

    phoneBookRepository.save(booking);

    Optional<List<PhoneBooking>> BookOpt = phoneBookRepository.findPhoneBookByPhone(phoneOpt.get());
    assertTrue(BookOpt.isPresent());
  }

  @Test
  public void shouldFindPhoneBooking() {
    Optional<Phone> phoneOpt = phoneRepository.findPhoneByPhoneName("Nokia 3310");
    assertTrue(phoneOpt.isPresent());

    PhoneBooking booking = new PhoneBooking();
    booking.setBookedBy("me");
    booking.setBookedAt(LocalDateTime.now());
    booking.setCount(1);

    phoneBookRepository.save(booking);

    final Optional<PhoneBooking> bookOpt = phoneBookRepository.findPhoneBookByBookId(booking.getBookId());
    assertTrue(bookOpt.isPresent());
  }
}
