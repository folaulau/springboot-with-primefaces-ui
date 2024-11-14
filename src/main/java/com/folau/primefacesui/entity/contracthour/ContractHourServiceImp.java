package com.folau.primefacesui.entity.contracthour;

import com.folau.primefacesui.entity.contract.Contract;
import com.folau.primefacesui.entity.contract.ContractDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ContractHourServiceImp implements ContractHourService {

    @Autowired
    private ContractHourDAO contractHourDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Override
    public List<Map<String, Object>> getCustomerContractHours() {

        List<Contract> contracts = contractDAO.findAll();

        List<ContractHour> contractHours = contractHourDAO.findAll();

        // sort by customer name
        contractHours.sort((c1, c2) -> c1.getCustomer().getId().compareTo(c2.getCustomer().getId()));

        long customerId = 0;
        List<Map<String, Object>> customerContractHoursList = new ArrayList<>();
        Map<String, Object> customerContractHours = null;
        for(ContractHour contractHour : contractHours) {
            log.info("contractHour: {}", contractHour);

            if(customerId != contractHour.getCustomer().getId()) {

                if(customerId != 0) {
                    customerContractHoursList.add(customerContractHours);
                }

                customerId = contractHour.getCustomer().getId();
                customerContractHours = new HashMap<>();
            }

            customerContractHours.put("customerName", contractHour.getCustomer().getName());
            customerContractHours.put(contractHour.getContract().getUuid(), contractHour.getHours());

        }

        return customerContractHoursList;
    }
}