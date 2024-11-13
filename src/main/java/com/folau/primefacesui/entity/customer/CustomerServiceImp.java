package com.folau.primefacesui.entity.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerDAO CustomerDAO;
}