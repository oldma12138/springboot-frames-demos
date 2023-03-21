package org.example.config.service;

import org.example.entity.SysUser;
import org.example.mapper.SysUserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 数据库中查找用户
        SysUser user = sysUserDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户" + username + "不存在!");
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}
