package com.folau.primefacesui.entity.contracthour;

import com.folau.primefacesui.entity.customer.Customer;
import com.folau.primefacesui.entity.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "ContractHour", description = "Contract Hour Operations")
@RequestMapping("/contract-hours")
@RestController
@Slf4j
public class ContractHourController {

    @Autowired
    private ContractHourService contractHourService;

    @Operation(summary = "Get All Customers")
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getCustomerContractHours() {
        return new ResponseEntity<>(contractHourService.getCustomerContractHours(), OK);
    }

}