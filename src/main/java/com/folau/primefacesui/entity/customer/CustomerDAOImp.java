package com.folau.primefacesui.entity.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@Slf4j
@Repository
public class CustomerDAOImp implements CustomerDAO {

    @Autowired
    private CustomerRepository CustomerRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
}