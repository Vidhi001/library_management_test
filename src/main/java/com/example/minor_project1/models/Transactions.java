package com.example.minor_project1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String txnId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transStatus;

    @Enumerated(value = EnumType.STRING)
    private TransactionsType transType;

    @CreationTimestamp
    private Date CreatedOn;

    @UpdateTimestamp
    private Date UpdatedOn;

    private Integer fine;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transList"})
    private Student myStudent;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transactionsList"})
    private Book myBook;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transactionsList"})
    private Admin myAdmin;


}
