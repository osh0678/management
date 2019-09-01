package com.management.com;

public class AppServletUtil {
	
	public boolean isLogin(String userId) {
		if( userId != null) {
			return true;
		}else {
			return false;
		}
	}
	
}
