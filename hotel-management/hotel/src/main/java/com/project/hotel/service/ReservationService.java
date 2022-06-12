package com.project.hotel.service;

import com.project.hotel.domain.customer.CustomerRepository;
import com.project.hotel.domain.customer.ReservationRepository;
import com.project.hotel.web.dto.ReservationDto;
import com.project.hotel.web.dto.ReservationListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public ArrayList<ReservationListDto> findReservationList(String seq) {
        return customerRepository.findReservationList(seq);
    }

    public ReservationDto findReservation(String customerSeq, String seq) {
        ReservationDto reservationDto = reservationRepository.findBySeq(seq);
        if (!reservationDto.getCustomerSeq().equals(customerSeq)) { // 예약의 고객번호와 세션의 고객번호가 일치하지 않으면 null 반환
            return null;
        }
        return reservationDto;
    }
}
