package com.example.minor_project1.repository;

import com.example.minor_project1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
//    @Query("select b from Book b join author a where ")
    List<Book> findByBookName(String value);

    @Query("select b from Book b where Genre =?1")
    List<Book> findByGenre(String value);

    @Query("select b from Book b join Author a on b.my_author = a.id where a.authorName = ?1")
    List<Book> findByAuthorName(String value);


}
