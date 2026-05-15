package com.brix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brix.blog.entity.Admin;

public interface AdminService extends IService<Admin> {

    Admin findByUsername(String username);

    String login(String username, String password);
}
