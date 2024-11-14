package com.folau.primefacesui.entity.contracthour;

import java.util.List;

public interface ContractHourDAO {
    ContractHour save(ContractHour contractHour);

    List<ContractHour> findAll();
}