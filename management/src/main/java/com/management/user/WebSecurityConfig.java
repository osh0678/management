package com.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired UserDetailsService userDetailsService;
	
	@Bean
	  public BCryptPasswordEncoder bCryptPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		//허용경로
		web.ignoring().antMatchers("/resources/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
	      .authorizeRequests()
	        // 해당 url을 허용한다. 
	          .antMatchers("/resources/**").permitAll()
	        // admin 폴더에 경우 admin 권한이 있는 사용자에게만 허용 
	          .antMatchers("/admin/**").hasAuthority("ADMIN")
	          // user 폴더에 경우 user 권한이 있는 사용자에게만 허용
	        .antMatchers("/user/**").hasAuthority("USER")
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()
	        .loginPage("/login")
	        .successHandler(new CustomLoginSuccessHandler()) // 로그인 성공 핸들러 
	        .failureHandler(new CustomLoginFailureHandler()) // 로그인 실패 핸들러 
	        .permitAll()
	        .and()
	      .logout()
	        .permitAll()
	        .and()
	       .exceptionHandling().accessDeniedPage("/403"); // 권한이 없을경우 해당 url로 이동
	}

	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	  }  
}