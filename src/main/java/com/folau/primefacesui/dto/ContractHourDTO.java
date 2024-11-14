package com.folau.primefacesui.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.folau.primefacesui.entity.contract.Contract;
import com.folau.primefacesui.entity.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ContractHourDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String customerName;

    private String contractUuid;

    private int hours;

    private int hoursUsed;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ToStringBuilder.reflectionToString(this);
    }
}
