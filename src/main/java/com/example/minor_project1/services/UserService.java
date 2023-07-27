package com.example.minor_project1.services;

import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.repository.UserRepository;
import com.example.minor_project1.config.PasswordEncoderConfig;
import com.example.minor_project1.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public SecuredUser save(SecuredUser securedUser, String studentUser) {
        // Encrupt the Password
        String encryptedPassword =  passwordEncoderConfig.getEncodedPassword().encode(securedUser.getPassword());

        //Add Authorities to the user
        String authorities = Utils.getAuthorities().get(studentUser);

        securedUser.setAuthorities(authorities);
        securedUser.setPassword(encryptedPassword);

        return userRepository.save(securedUser);
    }

}
