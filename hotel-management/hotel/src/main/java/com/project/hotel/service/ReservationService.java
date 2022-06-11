package com.project.hotel.service;

import com.project.hotel.domain.customer.CustomerRepository;
import com.project.hotel.web.dto.ReservationListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;

    public ArrayList<ReservationListDto> findReservationList(String seq) {
        return customerRepository.findReservationList(seq);
    }
}
