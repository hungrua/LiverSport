package com.liversportweb.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.liversportweb.javainuse.UserAuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
	@Autowired
	UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/","/login","/booking","/user/bookings","/user/add","/register","/user/favorite/*","/user/information/editPage","/user/information/**").permitAll();

		http.authorizeRequests().antMatchers("/admin/**","admin/field","/admin/information/editPage","/admin/information","admin/information/save/*").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/user/**","/booking","/sport_field","/user/field/**","/yourMatch").hasAuthority("USER");
		http.authorizeRequests().and().formLogin()
		.loginProcessingUrl("/j_spring_security_check")
		.loginPage("/login")
		.successHandler(userAuthenticationSuccessHandler)
		.failureUrl("/login?message=notFound")
		.usernameParameter("username")
		.passwordParameter("password");
	}
	@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
    }
 @Autowired
    private UserDetailsService userDetailsService;
 @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}