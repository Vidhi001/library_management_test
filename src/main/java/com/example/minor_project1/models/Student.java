package com.example.minor_project1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String studentName;

    @Column(unique = true,nullable = false)
    private String studentEmail;

    private Integer age;

    @CreationTimestamp
    private Date CreatedOn;

    @UpdateTimestamp
    private Date UpdatedOn;

    @OneToMany(mappedBy = "my_student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"my_student"})
    private Set<Book> bookList;

    @OneToMany(mappedBy = "myStudent", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"myStudent"})
    private Set<Transactions> transList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student"})
    private SecuredUser securedUser;



}
