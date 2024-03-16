package com.zettamine.boot.mi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.User;
import com.zettamine.boot.mi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public User validateUser(User user) {
		
		Optional<User> optUser = userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}

}
