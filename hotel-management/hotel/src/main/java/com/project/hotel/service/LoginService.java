package com.project.hotel.service;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;

    public Customer login(String id, String pw) {

        Customer customer = customerRepository.findById(id);

        // 회원이 없을 경우 NULL 반환
        if (customer == null) {
            return null;
        }

        // 비밀번호가 일치할 경우 고객 반환
        if (customer.getPw().equals(pw)) {
            return customer;
        } else {
            return null;
        }
    }
}
