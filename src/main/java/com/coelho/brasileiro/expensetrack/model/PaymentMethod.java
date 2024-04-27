package com.coelho.brasileiro.expensetrack.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_method")
@Entity(name = "PaymentMethod")
public class PaymentMethod  implements EntityDeletable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonCreator
    public PaymentMethod(@JsonProperty("id") String id) {
        this.id = UUID.fromString(id);
    }


}
