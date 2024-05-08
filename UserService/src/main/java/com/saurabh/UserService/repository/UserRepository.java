package com.saurabh.UserService.repository;

import com.saurabh.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByPhoneNo(String phoneNo);
}
