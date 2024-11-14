package com.folau.primefacesui.entity.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Customer", description = "Customer Operations")
@RequestMapping("/customers")
@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get All Customers")
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), OK);
    }

}