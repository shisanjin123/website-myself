package com.brix.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brix.blog.entity.Project;
import com.brix.blog.mapper.ProjectMapper;
import com.brix.blog.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public List<Project> getAllProjects() {
        return list(new LambdaQueryWrapper<Project>().orderByAsc(Project::getSortOrder));
    }
}
