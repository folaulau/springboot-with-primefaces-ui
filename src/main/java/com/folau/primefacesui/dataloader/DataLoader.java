package com.folau.primefacesui.dataloader;

import com.folau.primefacesui.entity.contract.Contract;
import com.folau.primefacesui.entity.contract.ContractRepository;
import com.folau.primefacesui.entity.contracthour.ContractHour;
import com.folau.primefacesui.entity.contracthour.ContractHourDAO;
import com.folau.primefacesui.entity.contracthour.ContractHourRepository;
import com.folau.primefacesui.entity.customer.Customer;
import com.folau.primefacesui.entity.customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class DataLoader {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractHourRepository contractHourRepository;

    private Faker faker = new Faker();
    private Random random = new Random();

    private int numberOfContracts = 4;
    private int numberOfCustomers = 30;

    private List<Contract> contracts = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Load contract data
        loadContracts();
        loadCustomers();
    }

    private void loadContracts(){
        for (int i = 1; i <= numberOfContracts; i++) {
            Optional<Contract> optionalContract = contractRepository.findById((long) i);

            if(optionalContract.isPresent()) {
                continue;
            }

            Contract contract = new Contract();
            contract.setId((long) i);
            contract.setNumber(random.nextInt(100000, 999999) + "");
            contract.setName(faker.name().fullName());
            contract.setStartDate(LocalDate.now().minusDays(random.nextInt(1, 30)));
            contract.setStartDate(contract.getStartDate().plusDays(random.nextInt(1, 30)));
            contracts.add(contractRepository.saveAndFlush(contract));

        }
    }

    private void loadCustomers(){
        int maxContractsPerCustomer = numberOfContracts;
        for (int i = 1; i <= numberOfCustomers; i++) {

            Optional<Customer> optionalCustomer = customerRepository.findById((long) i);

            if(optionalCustomer.isPresent()) {
                continue;
            }

            Customer customer = new Customer();
            customer.setId((long) i);
            Name name = faker.name();
            customer.setFirstName(name.firstName());
            customer.setLastName(name.lastName());
            customer.setEmail((customer.getFirstName()+customer.getLastName()).trim().toLowerCase() + "@gmail.com");
            customer.setDateOfBirth(LocalDate.now().minusYears(random.nextInt(18, 60)));
            customer.setPhoneNumber(faker.phoneNumber().cellPhone());

            int customerMaxNumberOfContracts = random.nextInt(1, maxContractsPerCustomer);

            System.out.println("customer id: "  + i + ", number of contracts: "+ customerMaxNumberOfContracts);

            customer = customerRepository.saveAndFlush(customer);

            if(customerMaxNumberOfContracts == 1){
                Contract contract = contracts.get(random.nextInt(0, numberOfContracts));

                ContractHour contractHour = new ContractHour();
                contractHour.setCustomer(customer);
                contractHour.setContract(contract);
                contractHour.setHours(random.nextInt(20, 100));
                contractHour.setHoursUsed(random.nextInt(2, 18));

                contractHourRepository.saveAndFlush(contractHour);

            }else {
                for (int con = 1; con <= customerMaxNumberOfContracts; con++){
                    Contract contract = contracts.get(random.nextInt(0, numberOfContracts));

                    ContractHour contractHour = new ContractHour();
                    contractHour.setCustomer(customer);
                    contractHour.setContract(contract);
                    contractHour.setHours(random.nextInt(20, 100));
                    contractHour.setHoursUsed(random.nextInt(2, 18));

                    contractHourRepository.saveAndFlush(contractHour);
                }
            }
        }
    }
}
