package com.folau.primefacesui.entity.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ContractDAOImp implements ContractDAO {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
}