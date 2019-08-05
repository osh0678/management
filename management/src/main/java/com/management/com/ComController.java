package com.management.com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.user.User;

@Controller
public class ComController {
	private List<User> users = new ArrayList<User>();

	@GetMapping("/main.vw")
	public String mainVw(User user) {
		users.add(user);
		
		if(user.getUserId() == null) {
			System.out.println("로그인 화면으로 돌아갑니다.");
			return "/html/user/login";
		}
		
		System.out.println("user : " + user);

		return "/html/user/login";
	}
}
