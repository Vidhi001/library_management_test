package com.example.minor_project1.utils;

public class Constants {

    public static final String DELIMETER = "::";
    public static final String STUDENT_USER = "student";

    public static final String ADMIN_USER = "admin";

    // Cache constants
    public static final String STUDENT_CACHE_KEY_PREFIX = "std::";
    public static final Integer STUDENT_CACHE_KEY_EXPIRY = 600;

    // Authorities for Students

    public static final String STUDENT_SELF_DETAILS = "STUDENT_SELF_DETAILS";

    public static final String PAYMENT_TRANSACTION = "PAYMENT_TRANSACTION";



    // Authorities for Admin

    public static final String STUDENT_BY_ID_DETAILS = "STUDENT_BY_ID_DETAILS";
    public static final String CREATE_ADMIN_AUTHORITY = "CREATE_ADMIN";

    public static final String CREATE_BOOK_AUTHORITY = "CREATE_BOOK";
    public static final String DELETE_BOOK_AUTHORITY = "DELETE_BOOK";
    public static final String UPDATE_BOOK_AUTHORITY = "UPDATE_BOOK";

    public static final String CREATE_TRANSACTION_AUTHORITY = "CREATE_TRANSACTION";


    // For Both
    public static final String ACCESS_BOOK_AUTHORITY = "ACCESS_BOOK";
    public static final String ACCESS_TRANSACTION_AUTHORITY = "ACCESS_TRANSACTION";


}
