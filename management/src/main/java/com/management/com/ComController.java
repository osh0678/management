package com.management.com;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.management.user.User;

@Controller
@RequestMapping("/com")
public class ComController {
	private List<User> users = new ArrayList<User>();

	@GetMapping("/main.vw")
	public String mainVw(User user,HttpServletRequest request, HttpServletResponse response) {
		AppServletUtil app = new AppServletUtil();
		ModelAndView mv = new ModelAndView();
		
		users.add(user);
		
		System.out.println("user ID : " + user.getUserId());
		if(!app.isLogin(user.getUserId())){
			System.out.println("로그인 화면으로 돌아갑니다.");
			System.out.println(app.isLogin(user.getUserId()));
			return "redirect:/usr/login.vw";
		}
		
		System.out.println("user : " + user);
//		mv.setViewName("");
		return "html/com/main";
	}

}
