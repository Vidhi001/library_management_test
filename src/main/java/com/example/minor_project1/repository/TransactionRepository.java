package com.example.minor_project1.repository;

import com.example.minor_project1.models.Book;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.models.Transactions;
import com.example.minor_project1.models.TransactionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
  //  @Query("select t from Transactions t where t.my_student = ?1 and t.my_book=?2 and t.transType=?3 order by Id desc limit 1")
    Transactions findTopByMyStudentAndMyBookAndTransTypeOrderByIdDesc(Student my_student, Book my_book, TransactionsType transactionsType);

    Transactions findTopByTxnIdOrderByIdDesc(String txnId);


//  SELECT * FROM your_table_name WHERE STR_TO_DATE(datetime_column, '%Y-%m-%d %H:%i:%s') >= CURDATE() - INTERVAL 15 DAY;
//  SELECT e FROM YourEntity e WHERE FUNCTION('STR_TO_DATE', e.datetimeColumn, '%Y-%m-%d %H:%i:%s.%f') >= :fifteenDaysAgo";

    @Query(value = "Select * from transactions t where t.my_student_id = ?1 and STR_TO_DATE(t.created_on, '%Y-%m-%d %H:%i:%s.%f') >= CURDATE() - INTERVAL ?2 DAY",nativeQuery = true )
    List<Transactions> getlastNDaysData(Integer studentId, Integer daysData);
}
