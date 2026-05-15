package com.brix.blog.controller;

import com.brix.blog.entity.Admin;
import com.brix.blog.service.AdminService;
import com.brix.blog.util.JwtUtil;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "认证管理", description = "管理员登录认证")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginRequest request) {
        String token = adminService.login(request.getUsername(), request.getPassword());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return Result.success("登录成功", data);
    }

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/info")
    public Result<?> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Long userId = jwtUtil.getUserIdFromToken(token);
        Admin admin = adminService.getById(userId);
        if (admin == null) {
            return Result.error(401, "用户不存在");
        }
        admin.setPassword(null);
        return Result.success(admin);
    }
}
