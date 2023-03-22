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
		http.authorizeRequests()	//�w�q����url�ݭn�Q�O�@
		.antMatchers("/resources/**").permitAll()	//resources��Ƨ����\�ΦW�s��
//		.antMatchers("/hello").hasRole("user")	//�ǰt�� "/hello" ���U�ݭn�� user ������~��i�J
		.anyRequest().authenticated()	//����󥼤ǰt��url���n�����{��
		.and()
		.formLogin()	//�ۭq�ϥΪ�login����
		.and()
		.httpBasic();	//�t�m��http���򥻻{��
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
//		.and()	//�[�J�ĤG�ӨϥΪ̡A�ϥ�.and()
//		.withUser("Bob").roles("user").password(passwordEncoder().encode("123"));
//	}
	
	//�t�m�K�X�[�K����
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//====================================================	
	// �����S�w���}�A���i��b�K�n��
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/hello.html").permitAll();	//permitAll()���\
//	}

	// �����d�I�A����k�P�W��2��1(HttpSecurity http �� WebSecurity web)
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/hello.html");
//	}
}
