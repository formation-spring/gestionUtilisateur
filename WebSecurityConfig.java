package com.formation.formationspring.config;

import com.formation.formationspring.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
private String key="pass";

@Autowired
 private JwtUserDetailsService userDetailsService;
@Override
 protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
 auth.authenticationProvider(authProvider());

}
@Bean
 public DaoAuthenticationProvider  authProvider() {
 DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
 authProvider.setUserDetailsService(userDetailsService);
 authProvider.setPasswordEncoder(passwordEncoder());
 return authProvider;
 }

 private BCryptPasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder();
 }
 @Bean
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception{
 return super.authenticationManagerBean();
 }
 @Bean
 public JwtAuthenticationFilter authenticationFilterBean(){
 return new JwtAuthenticationFilter();
 }
 @Override
 protected void configure(HttpSecurity httpSecurity) throws Exception{
 httpSecurity.cors().and().csrf().disable()
         .authorizeRequests().antMatchers("/v2/api-docs","/configuration/**","/swagger*/**",
         "/webjars/**","/users/**").permitAll().anyRequest().authenticated().and().exceptionHandling()
         .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 httpSecurity.addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
 }
}
