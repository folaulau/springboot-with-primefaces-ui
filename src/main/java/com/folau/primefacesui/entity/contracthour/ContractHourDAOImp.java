package com.folau.primefacesui.entity.contracthour;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ContractHourDAOImp implements ContractHourDAO {

    @Autowired
    private ContractHourRepository contractHourRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ContractHour save(ContractHour contractHour) {
        return contractHourRepository.saveAndFlush(contractHour);
    }

    @Override
    public List<ContractHour> findAll() {
        return contractHourRepository.findAll();
    }
}