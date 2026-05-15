package com.brix.blog.controller;

import com.brix.blog.entity.Project;
import com.brix.blog.service.ProjectService;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "项目管理", description = "项目管理")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "获取项目列表")
    @GetMapping("/projects")
    public Result<List<Project>> getAllProjects() {
        return Result.success(projectService.getAllProjects());
    }

    @Operation(summary = "获取项目详情")
    @GetMapping("/projects/{id}")
    public Result<Project> getProject(@PathVariable Long id) {
        return Result.success(projectService.getById(id));
    }

    @Operation(summary = "添加项目")
    @PostMapping("/admin/projects")
    public Result<?> addProject(@RequestBody Project project) {
        projectService.save(project);
        return Result.success("添加成功");
    }

    @Operation(summary = "更新项目")
    @PutMapping("/admin/projects/{id}")
    public Result<?> updateProject(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        projectService.updateById(project);
        return Result.success("更新成功");
    }

    @Operation(summary = "删除项目")
    @DeleteMapping("/admin/projects/{id}")
    public Result<?> deleteProject(@PathVariable Long id) {
        projectService.removeById(id);
        return Result.success("删除成功");
    }
}
