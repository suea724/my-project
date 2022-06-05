package com.project.hotel;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class TestData {

    private final CustomerRepository customerRepository;

    // 애플리케이션 시작 시 입력할 테스트 데이터
    @PostConstruct
    public void init() {

        Customer customer = Customer.builder()
                                    .seq("1")
                                    .id("test1234")
                                    .pw("test1234")
                                    .name("테스트")
                                    .tel("010-0000-0000")
                                    .email("aaaaa@test.com")
                                    .build();

        customerRepository.save(customer);
    }

    // 애플리케이션 종료시 삭제
    @PreDestroy
    public void close() {
        customerRepository.deleteCustomer("test1234");
    }

}
