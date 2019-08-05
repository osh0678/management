package com.management.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>(); 
		
		@GetMapping("/login")
		public String login(User user) {
			System.out.println("user : " + user);
			users.add(user);
			return "/html/user/login";
		}
}
