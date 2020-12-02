package com.ofr.userdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofr.usermodel.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
