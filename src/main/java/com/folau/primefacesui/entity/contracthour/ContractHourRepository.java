package com.folau.primefacesui.entity.contracthour;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractHourRepository extends JpaRepository<ContractHour, Long> {
}