package com.management.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>(); 
		
		@GetMapping("/login.vw")
		public String loginVw(User user) {
			users.add(user);
			System.out.println("user : " + user);
			
			return "/html/user/login";
		}

		@RequestMapping(value="login.do", method = RequestMethod.POST)
		public String loginDo(User user) {
			System.out.println("user id : " + user.getUserId());
			System.out.println("user pw : " + user.getUserPw());
			
			if(user.getUserId() == null || user.getUserId() == "") {
				System.out.println("아이디를 입력해주세요.");
			}else if(user.getUserPw() == null || user.getUserPw() == "") {
				System.out.println("패스워드를 입력해주세요.");
			}else {
				System.out.println("로그인 성공 !!");
			}
			
			return "html/user/login";
		}
}
