package com.frankstar.code.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author :  frankstar
 * @AddTime :  2020/6/29
 * @EMail :  yehongxing@meituan.com
 * @Project :  code-lab
 * @Desc :
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.
			//使用内存中的InMemoryUserDetailManager
			inMemoryAuthentication()
			//不用密码编码器
			.passwordEncoder(encoder)
			//配置admin用户
			.withUser("frankstar")
			.password(encoder.encode("frankstar")).roles("ADMIN", "NORMAL")
			.and()
			.withUser("normal").password(encoder.encode("normal")).roles("NORMAL");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/star/echo").permitAll() // 所有用户可访问
			.antMatchers("/star/admin").hasRole("ADMIN") // 需要 ADMIN 角色
			.antMatchers("/star/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
			// 任何请求，访问的用户都需要经过认证
			.anyRequest().authenticated()
			.and()
			// <Y> 设置 Form 表单登录
			.formLogin()
//                    .loginPage("/login") // 登录 URL 地址
			.permitAll() // 所有用户可访问
			.and()
			// 配置退出相关
			.logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
			.permitAll(); // 所有用户可访问
	}
}
