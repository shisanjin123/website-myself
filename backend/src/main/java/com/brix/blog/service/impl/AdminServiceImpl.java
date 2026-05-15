package com.brix.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brix.blog.entity.Admin;
import com.brix.blog.mapper.AdminMapper;
import com.brix.blog.service.AdminService;
import com.brix.blog.util.BusinessException;
import com.brix.blog.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    private final JwtUtil jwtUtil;

    @Override
    public Admin findByUsername(String username) {
        return getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, username));
    }

    @Override
    public String login(String username, String password) {
        Admin admin = findByUsername(username);
        if (admin == null) {
            throw new BusinessException(401, "用户名或密码错误");
        }
        // 简单密码验证（生产环境应使用BCrypt）
        if (!admin.getPassword().equals(password)) {
            throw new BusinessException(401, "用户名或密码错误");
        }
        return jwtUtil.generateToken(admin.getId(), admin.getUsername());
    }
}
