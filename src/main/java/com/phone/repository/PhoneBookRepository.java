package com.phone.repository;

import java.util.List;
import java.util.Optional;

import com.phone.model.Phone;
import com.phone.model.PhoneBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBooking, Long> {

  Optional<PhoneBooking> findPhoneBookByBookId(int bookId);

  Optional<List<PhoneBooking>> findPhoneBookByPhone(Phone phone);
}
