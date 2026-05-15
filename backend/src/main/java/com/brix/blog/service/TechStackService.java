package com.brix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brix.blog.entity.TechStack;

import java.util.List;

public interface TechStackService extends IService<TechStack> {

    List<TechStack> getAllTechStacks();
}
