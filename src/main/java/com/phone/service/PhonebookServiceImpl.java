package com.phone.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.phone.exceptions.PhoneBookingNotFoundException;
import com.phone.exceptions.PhoneCountNotEnoughException;
import com.phone.exceptions.PhoneNotFoundException;
import com.phone.model.BookDetails;
import com.phone.model.Phone;
import com.phone.model.PhoneBookRequest;
import com.phone.model.PhoneBookResponse;
import com.phone.model.PhoneBooking;
import com.phone.model.PhoneDetailsDto;
import com.phone.model.PhoneDto;
import com.phone.repository.PhoneBookRepository;
import com.phone.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.phone.model.BookingStatusEnum.BOOKED;
import static com.phone.model.BookingStatusEnum.RETURNED;

@Service
@RequiredArgsConstructor
public class PhonebookServiceImpl implements PhoneBookService {

  private final PhoneRepository phoneRepository;
  private final PhoneBookRepository bookRepository;
  private final PhoneDetailsService detailsService;

  @Override
  @Transactional
  public PhoneBookResponse bookPhone(PhoneBookRequest phoneBookRequest) {
    Phone phone = phoneRepository
        .findPhoneByPhoneName(phoneBookRequest.getPhoneName())
        .orElseThrow(PhoneNotFoundException::new);

    if (!phone.isAvailable()) {
      throw new PhoneCountNotEnoughException();
    }

    if (!phone.isEnoughPhoneCount(phoneBookRequest.getCount())) {
      throw new PhoneCountNotEnoughException();
    }

    phone.decreasePhoneCount(phoneBookRequest.getCount());

    PhoneBooking booking = new PhoneBooking();
    booking.setPhone(phone);
    booking.setCount(phoneBookRequest.getCount());
    booking.setBookedBy(phoneBookRequest.getBookedBy());
    booking.setBookedAt(LocalDateTime.now());
    booking.setStatus(BOOKED);

    phoneRepository.save(phone);
    bookRepository.save(booking);

    return PhoneBookResponse.successfullyBooked(booking.getBookId());
  }

  @Override
  @Transactional
  public PhoneBookResponse returnPhone(int bookId) {
    PhoneBooking booking = bookRepository.findPhoneBookByBookId(bookId)
        .orElseThrow(PhoneBookingNotFoundException::new);

    if (RETURNED.equals(booking.getStatus())) {
      return PhoneBookResponse.successfullyReturned(bookId);
    }

    booking.getPhone().increaseCount();
    booking.setStatus(RETURNED);

    bookRepository.save(booking);

    return PhoneBookResponse.successfullyReturned(bookId);
  }

  @Override
  public PhoneDetailsDto getPhoneDetails(String phoneName) {
    Phone phone = phoneRepository.findPhoneByPhoneName(phoneName)
        .orElseThrow(PhoneNotFoundException::new);

    List<PhoneBooking> phoneBookList = bookRepository.findPhoneBookByPhone(phone)
        .orElseThrow(PhoneBookingNotFoundException::new);

    PhoneDetailsDto phoneDetailsDto = PhoneDetailsDto.builder()
        .phoneName(phoneName)
        .available(phone.getCount() >= 1)
        .build();

    for (PhoneBooking phoneBooking : phoneBookList) {
      phoneDetailsDto.getBookDetails().add(
          BookDetails.builder()
              .phoneBookedAt(phoneBooking.getBookedAt())
              .phoneBookedBy(phoneBooking.getBookedBy())
              .status(phoneBooking.getStatus().name())
              .build()
      );
    }

    return phoneDetailsDto;
  }

  @Override
  public List<PhoneDto> getListOfPhones() {
    return phoneRepository.findAll().stream().map(p ->
        new PhoneDto(p.getPhoneName(), p.getCount() >= 1, detailsService.getPhoneDetailsByName(p.getPhoneName()))
    ).collect(Collectors.toList());
  }
}
