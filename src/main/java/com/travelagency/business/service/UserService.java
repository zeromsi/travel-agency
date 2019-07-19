package com.travelagency.business.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.travelagency.business.web.dto.UserRegistrationDto;
import com.travelagency.data.User;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    void save(UserRegistrationDto registration) throws Exception;
}