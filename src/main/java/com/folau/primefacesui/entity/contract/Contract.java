package com.folau.primefacesui.entity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.folau.primefacesui.entity.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@DynamicUpdate
@Entity
@Table(name = "primefaces_ui_contracts")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "contract_number", unique = true, nullable = false, updatable = false)
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @JsonIgnoreProperties(value = {"contracts"})
    @ManyToMany(mappedBy = "contracts", cascade = CascadeType.DETACH)
    private Set<Customer> customers;


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ToStringBuilder.reflectionToString(this);
    }

    @PrePersist
    private void preCreate() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = "contract-" + UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

    @PreUpdate
    private void preUpdate() {
    }

}