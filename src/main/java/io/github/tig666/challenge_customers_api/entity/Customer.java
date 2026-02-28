package io.github.tig666.challenge_customers_api.entity;

import io.github.tig666.challenge_customers_api.entity.generic.GeneralAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends GeneralAudit {
    @Id
    @Column(name = "document_number")
    private Long documentNumber;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "is_active")
    private boolean isActive = true;
}
