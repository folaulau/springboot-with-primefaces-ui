package com.folau.primefacesui.entity.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractServiceImp implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Override
    public List<Contract> getAllContracts() {
        return contractDAO.findAll();
    }
}