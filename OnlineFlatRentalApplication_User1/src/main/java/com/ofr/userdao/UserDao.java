package com.ofr.userdao;

/*
 * Dao Class which extends to JPA Repository to use the predefined methods
 * 
 * @Author : Sathya Sivam R
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofr.usermodel.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
