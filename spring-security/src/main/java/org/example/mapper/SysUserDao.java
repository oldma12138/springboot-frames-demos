package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.SysUser;

/**
 * DAO层
 */
@Mapper
public interface SysUserDao {
    SysUser findByUsername(String username);
    void insertSysUser(SysUser sysUser);
}
