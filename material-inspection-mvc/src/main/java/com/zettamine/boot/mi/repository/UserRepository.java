package com.zettamine.boot.mi.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zettamine.boot.mi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	Optional<User> findByUserNameAndPassword(String userName, String password);

}
