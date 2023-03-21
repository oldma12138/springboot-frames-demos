package org.example.api;

import org.example.config.ResponseJson;
import org.example.entity.SysUser;
import org.example.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 接口
 */
@RestController
public class SysUserApi {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("/register")
    public ResponseJson<SysUser> register(SysUser sysUser) {
        return sysUserService.register(sysUser);
    }

    @GetMapping("/hello")
    public ResponseJson<Void> hello() {
        return ResponseJson.success("访问成功！公开接口：/hello", null);
    }

    @GetMapping("/private")
    public ResponseJson<Void> hello2() {
        return ResponseJson.success("访问成功！非公开接口：/private", null);
    }
}


