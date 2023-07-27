package com.example.minor_project1.utils;

import com.example.minor_project1.models.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Utils {

    public static Map<String, String> getAuthorities(){
        HashMap<String,String> authoritiesMap = new HashMap<>();

        List<String> studentAuthorities = Arrays.asList(Constants.STUDENT_SELF_DETAILS,
                Constants.ACCESS_BOOK_AUTHORITY,
                Constants.PAYMENT_TRANSACTION,
                Constants.ACCESS_TRANSACTION_AUTHORITY);

        List<String> adminAuthorities = Arrays.asList(Constants.STUDENT_BY_ID_DETAILS,
                Constants.CREATE_ADMIN_AUTHORITY,
                Constants.CREATE_BOOK_AUTHORITY,
                Constants.DELETE_BOOK_AUTHORITY,
                Constants.UPDATE_BOOK_AUTHORITY,
                Constants.ACCESS_BOOK_AUTHORITY,
                Constants.ACCESS_TRANSACTION_AUTHORITY,
                Constants.CREATE_TRANSACTION_AUTHORITY);

        String studentAuthoritiesAsString = String.join(Constants.DELIMETER, studentAuthorities);
        String adminAuthoritiesAsString = String.join(Constants.DELIMETER, adminAuthorities);

        authoritiesMap.put(Constants.STUDENT_USER, studentAuthoritiesAsString);
        authoritiesMap.put(Constants.ADMIN_USER, adminAuthoritiesAsString);

        return authoritiesMap;
    }

    public static Student removeBookAndTransaction(Student student){
        student.setBookList(null);
        student.setTransList(null);
        return student;
    }
}
