package org.example.service;

import org.example.config.ResponseJson;
import org.example.entity.SysUser;
import org.example.mapper.SysUserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 服务层
 */
@Service
public class SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    public ResponseJson<SysUser> register(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())) {
            return ResponseJson.error("用户名或密码不能为空", null);
        }
        String encodePassword = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        sysUserDao.insertSysUser(sysUser);
        return ResponseJson.success("注册成功", sysUser);
    }
}

