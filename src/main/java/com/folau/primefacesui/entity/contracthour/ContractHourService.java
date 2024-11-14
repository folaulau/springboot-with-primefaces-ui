package com.folau.primefacesui.entity.contracthour;

import java.util.List;
import java.util.Map;

public interface ContractHourService {
    List<Map<String, Object>> getCustomerContractHours();
}