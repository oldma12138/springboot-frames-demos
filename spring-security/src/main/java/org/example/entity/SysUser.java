package org.example.entity;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class SysUser {
    private Long id;
    private String username;
    private String password;
}
