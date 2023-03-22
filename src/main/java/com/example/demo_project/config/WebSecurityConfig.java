package com.example.demo_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo_project.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	//定義哪些url需要被保護
		.antMatchers("/resources/**").permitAll()	//resources資料夾允許匿名存取
//		.antMatchers("/hello").hasRole("user")	//匹配到 "/hello" 底下需要有 user 的角色才能進入
		.anyRequest().authenticated()	//對任何未匹配的url都要身分認證
		.and()
		.formLogin()	//自訂使用者login頁面
		.and()
		.httpBasic();	//配置為http的基本認證
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//in memory
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("Alice").roles("admin").password(passwordEncoder().encode("123"))
//		.and()	//加入第二個使用者，使用.and()
//		.withUser("Bob").roles("user").password(passwordEncoder().encode("123"));
//	}
	
	//配置密碼加密元件
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//====================================================	
	// 忽略特定網址，不進行帳密登錄
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/hello.html").permitAll();	//permitAll()允許
//	}

	// 忽略攔截，此方法與上面2擇1(HttpSecurity http 或 WebSecurity web)
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/hello.html");
//	}
}
