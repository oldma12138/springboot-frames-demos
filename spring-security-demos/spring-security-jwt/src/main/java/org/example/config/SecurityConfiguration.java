package org.example.config;


import org.example.config.entrypoint.AuthenticationEntryPointImpl;
import org.example.config.filter.JwtAuthenticationFilter;
import org.example.config.filter.JwtLoginFilter;
import org.example.config.provider.AuthenticationProviderImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 全局请求忽略规则配置
     */
    @Override
    public void configure(WebSecurity web) {
        // 需要放行的URL
        web.ignoring().antMatchers("/register", "/hello");
    }


    /**
     * 自定义认证策略：登录的时候会进入
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        // 2.通过实现 AuthenticationProvider 自定义身份认证验证组件
        auth.authenticationProvider(new AuthenticationProviderImpl(userDetailsService, bCryptPasswordEncoder));
    }

    /**
     * HTTP 验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 所有请求需要身份认证
                .and().authorizeRequests().anyRequest().authenticated()
                .and()
                // 3.自定义JWT登录过滤器
                .addFilter(new JwtLoginFilter(authenticationManager()))
                // 4.自定义JWT认证过滤器
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // 5.自定义认证拦截器，也可以直接使用内置实现类Http403ForbiddenEntryPoint
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl())
                // 允许跨域
                .and().cors()
                // 禁用跨站伪造
                .and().csrf().disable();
    }









}
