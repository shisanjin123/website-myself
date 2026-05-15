package com.brix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brix.blog.entity.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {

    List<Project> getAllProjects();
}
