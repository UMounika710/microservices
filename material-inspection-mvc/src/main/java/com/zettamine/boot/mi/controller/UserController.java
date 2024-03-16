package com.zettamine.boot.mi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.entity.User;
import com.zettamine.boot.mi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/*
	 * 
	 * @GetMapping("/") public String showUserForm(Model model) {
	 * model.addAttribute("user", new User());
	 * 
	 * return "login"; }
	 */
	@PostMapping("/login")
	public ResponseEntity<String> processUser(@RequestBody(required = true) User user, HttpServletRequest request) {

		User loggedUser = userService.validateUser(user);
		if (loggedUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", loggedUser);
			return new ResponseEntity<String>("Logged in successful", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Invalid Login", HttpStatus.BAD_REQUEST);

	}

}
