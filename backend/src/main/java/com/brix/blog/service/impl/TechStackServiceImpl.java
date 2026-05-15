package com.brix.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brix.blog.entity.TechStack;
import com.brix.blog.mapper.TechStackMapper;
import com.brix.blog.service.TechStackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechStackServiceImpl extends ServiceImpl<TechStackMapper, TechStack> implements TechStackService {

    @Override
    public List<TechStack> getAllTechStacks() {
        return list(new LambdaQueryWrapper<TechStack>().orderByAsc(TechStack::getSortOrder));
    }
}
