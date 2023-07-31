package com.example.minor_project1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@JsonSerialize(using = ASerializer.class)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Name;

    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date CreatedOn;

    @OneToMany(mappedBy = "myAdmin", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"transactionsList"})
    private Set<Transactions> transactionsList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"admin"})
    private SecuredUser securedUser;


}
