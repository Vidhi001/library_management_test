package com.example.minor_project1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private Date CreatedOn;

    @UpdateTimestamp
    private Date UpdatedOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList"})
    private Author my_author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList"})
    private Student my_student;

    @OneToMany(mappedBy = "myBook", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"myAdmin"})
    private Set<Transactions> transactionsList;

}

// select * from author where id = ?
// select * from book where author_id = ?
