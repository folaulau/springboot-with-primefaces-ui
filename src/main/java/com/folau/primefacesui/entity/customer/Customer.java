package com.folau.primefacesui.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.folau.primefacesui.entity.address.Address;
import com.folau.primefacesui.entity.contract.Contract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Where(clause = "deleted = false")
@DynamicUpdate
@Entity
@Table(name = "primefaces_ui_customers", indexes = {@Index(columnList = "email"),
        @Index(columnList = "deleted")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

//    @JsonIgnoreProperties(value = {"customers"})
//    @ManyToMany(cascade = CascadeType.DETACH)
//    @JoinTable(
//            name = "customer_contracts",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "contract_id"))
//    private Set<Contract> contracts;

    // This is the main or default address
    @JsonIgnoreProperties(value = {"customer"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

//    public void addContract(Contract contract) {
//        if (this.contracts == null) {
//            this.contracts = new HashSet<>();
//        }
//        // add only if it does not exist
//         if (this.contracts.stream().noneMatch(c -> c.getId().equals(contract.getId()))) {
//             this.contracts.add(contract);
//         }
//    }


    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ToStringBuilder.reflectionToString(this);
    }

    @PrePersist
    private void preCreate() {

        if (this.deleted == null) {
            this.deleted = false;
        }

        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = "customer-" + UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

    @PreUpdate
    private void preUpdate() {
    }
}