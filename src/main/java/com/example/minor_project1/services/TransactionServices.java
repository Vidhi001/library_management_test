package com.example.minor_project1.services;

import com.example.minor_project1.dtos.CreatePaymentRequest;
import com.example.minor_project1.dtos.CreateTransactionRequest;
import com.example.minor_project1.models.*;
import com.example.minor_project1.repository.TransactionRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionServices {
    Logger logger = LoggerFactory.getLogger(TransactionServices.class);
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AdminServices adminServices;

    @Autowired
    BookServices bookServices;

    @Autowired
    StudentServices studentServices;

    @Value("${student.book.issue.limit}")
    Integer maxBookLimit;

    @Value("${student.book.issue.days}")
    Integer maxDays;


    public String initCreate(CreateTransactionRequest transactionRequest, Integer adminId) throws Exception {

        Student student = studentServices.get(transactionRequest.getStudentId());
        Admin admin = adminServices.get(adminId);
        List<Book> bookLst = bookServices.getByRequest("Id", String.valueOf(transactionRequest.getBookId()));

        Book book = null;
        if(bookLst!=null && bookLst.size()>0){
            book = bookLst.get(0);
        }


        return transactionRequest.getTransType() == TransactionsType.ISSUE
                ? issueRequest(transactionRequest, student, admin, book)
                : returnRequest(transactionRequest, student, admin, book);
    }


    private String issueRequest(CreateTransactionRequest transactionRequest, Student student, Admin admin, Book book) throws Exception {
//        logger.debug("Student : {} , Admin : {} , Book : {} ",student,admin,book);
//        logger.debug("Student : {} , Book : {} ",student.getBookList().size()>=maxBookLimit,book.getMy_student());
        if(student==null
                || book==null
                || admin==null
                || student.getBookList().size()>=maxBookLimit
                || book.getMy_student()!=null) {
            throw new Exception("Invalid Argument Issue");
        }

        Transactions transactions = null;
        try {
            transactions = Transactions.builder()
                    .txnId(String.valueOf(UUID.randomUUID()))
                    .transStatus(TransactionStatus.PENDING)
                    .transType(transactionRequest.getTransType())
                    .myStudent(student)
                    .myAdmin(admin)
                    .myBook(book)
                    .build();

            transactionRepository.save(transactions);

            book.setMy_student(student);

            bookServices.createOrUpdate(book);

            transactions.setTransStatus(TransactionStatus.SUCCESS);
        } catch (Exception e) {
            transactions.setTransStatus(TransactionStatus.FAILED);
        } finally {
            transactionRepository.save(transactions);

        }
        return transactions.getTxnId();

    }

    private String returnRequest(CreateTransactionRequest transactionRequest, Student student, Admin admin, Book book) throws Exception {
        if(student==null
                || book==null
                || admin==null
                || book.getMy_student()==null
                || book.getMy_student().getId() != student.getId()) {
            throw new Exception("Invalid Argument Return");
        }
        Transactions transactions =  null;
        Transactions issueRequest = transactionRepository.findTopByMyStudentAndMyBookAndTransTypeOrderByIdDesc(student,book,TransactionsType.ISSUE);

        if (issueRequest == null)
            throw new Exception("No Valid Transactions");


        try{
            Integer fine = calculateFine(issueRequest.getCreatedOn());

            transactions = Transactions.builder()
                    .txnId(String.valueOf(UUID.randomUUID()))
                    .transStatus(TransactionStatus.PENDING)
                    .transType(transactionRequest.getTransType())
                    .myStudent(student)
                    .myAdmin(admin)
                    .myBook(book)
                    .fine(fine)
                    .build();

            transactionRepository.save(transactions);

            if(fine==0) {
                book.setMy_student(null);

                bookServices.createOrUpdate(book);

                transactions.setTransStatus(TransactionStatus.SUCCESS);


            }

        }
        catch (Exception e){
            transactions.setTransStatus(TransactionStatus.FAILED);
        }
        finally {
            transactionRepository.save(transactions);
        }

        return transactions.getTxnId();
    }

    private Integer calculateFine(Date issueRequest) {
        long issueDateMillis = issueRequest.getTime();
        long systemDateMillis = System.currentTimeMillis();
        logger.debug("Issue created : {} , current Time : {}", issueDateMillis,systemDateMillis);
        long diff = systemDateMillis - issueDateMillis;

        long daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        logger.debug("Days : {} ", daysPassed);
        if(daysPassed > maxDays){
            return (int)(daysPassed-maxDays);
        }
        else{
            return 0;
        }

    }

    public void payFine(CreatePaymentRequest paymentRequest, Integer studentId) throws Exception {
         Transactions txnDetail = transactionRepository.findTopByTxnIdOrderByIdDesc(paymentRequest.getTxnId());
         Book book = txnDetail.getMyBook();
         try {
             if (book.getMy_student() !=null
                     && book.getMy_student().getId() == studentId
                     && txnDetail.getFine() == paymentRequest.getAmount()) {
                 txnDetail.setTransStatus(TransactionStatus.SUCCESS);
                 book.setMy_student(null);
                 bookServices.createOrUpdate(book);
                 transactionRepository.save(txnDetail);
             }
         }
         catch (Exception e){
             throw new Exception("Payment Failed");
         }
    }

    public List<Transactions> getLastNDays(Integer studentId, Integer daysData) {
        return transactionRepository.getlastNDaysData(studentId,daysData);

    }
}
