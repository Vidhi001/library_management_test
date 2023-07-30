package com.example.minor_project1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String authorName;

    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date CreatedOn;

    @OneToMany(mappedBy = "my_author")
    @JsonIgnoreProperties({"my_author"})
    private Set<Book> bookList;

}

// ABC  email@gmail.com 50
// ABC  email@gmail.com 51