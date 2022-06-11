package com.project.hotel.service;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.domain.customer.CustomerRepository;
import com.project.hotel.web.dto.JoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final CustomerRepository customerRepository;

    public boolean isDuplicate(JoinForm joinForm) {
        return customerRepository.findById(joinForm.getId()) != null; // 중복된 아이디가 있으면 true, 없으면 false 반환
    }

    public void join(JoinForm joinForm) {
        Customer joinCustomer = Customer.builder().name(joinForm.getName())
                                                    .id(joinForm.getId())
                                                    .pw(joinForm.getPw())
                                                    .tel(joinForm.getTel())
                                                    .email(joinForm.getEmail())
                                                    .build();

        customerRepository.save(joinCustomer);
    }
}
