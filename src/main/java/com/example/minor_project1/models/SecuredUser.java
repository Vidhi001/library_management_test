package com.example.minor_project1.models;

import com.example.minor_project1.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SecuredUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String authorities;

    @OneToOne(mappedBy = "securedUser")
    @JsonIgnoreProperties({"securedUser"})
    private Student student;

    @OneToOne(mappedBy = "securedUser")
    @JsonIgnoreProperties({"securedUser"})
    private Admin admin;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        String[] authoritiesArray = this.authorities.split(Constants.DELIMETER);
        return Arrays.stream(authoritiesArray)
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername(){

        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
