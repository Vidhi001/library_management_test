package com.example.minor_project1.config;

import com.example.minor_project1.services.UserService;
import com.example.minor_project1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                // Student
                .antMatchers(HttpMethod.GET, "/student/**").hasAuthority(Constants.STUDENT_SELF_DETAILS)
                .antMatchers(HttpMethod.GET, "/student-by-id/**").hasAuthority(Constants.STUDENT_BY_ID_DETAILS)
                .antMatchers(HttpMethod.POST,"student/**").permitAll()

                // Admin
                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY)

                // Book
                .antMatchers(HttpMethod.GET,"/book/**").hasAuthority(Constants.ACCESS_BOOK_AUTHORITY)
                .antMatchers(HttpMethod.POST,"/book/**").hasAuthority(Constants.CREATE_BOOK_AUTHORITY)

                // Transaction
                .antMatchers(HttpMethod.POST,"/transaction/payment/**").hasAuthority(Constants.PAYMENT_TRANSACTION)
                .antMatchers(HttpMethod.POST,"/transaction/**").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/transaction/**").hasAuthority(Constants.ACCESS_TRANSACTION_AUTHORITY)

                .and()
                .formLogin();
    }
}
