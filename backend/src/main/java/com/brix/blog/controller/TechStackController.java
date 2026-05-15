package com.brix.blog.controller;

import com.brix.blog.entity.TechStack;
import com.brix.blog.service.TechStackService;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "技术栈", description = "技术栈管理")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TechStackController {

    private final TechStackService techStackService;

    @Operation(summary = "获取技术栈列表")
    @GetMapping("/tech-stack")
    public Result<List<TechStack>> getAllTechStacks() {
        return Result.success(techStackService.getAllTechStacks());
    }

    @Operation(summary = "添加技术栈")
    @PostMapping("/admin/tech-stack")
    public Result<?> addTechStack(@RequestBody TechStack techStack) {
        techStackService.save(techStack);
        return Result.success("添加成功");
    }

    @Operation(summary = "更新技术栈")
    @PutMapping("/admin/tech-stack/{id}")
    public Result<?> updateTechStack(@PathVariable Long id, @RequestBody TechStack techStack) {
        techStack.setId(id);
        techStackService.updateById(techStack);
        return Result.success("更新成功");
    }

    @Operation(summary = "删除技术栈")
    @DeleteMapping("/admin/tech-stack/{id}")
    public Result<?> deleteTechStack(@PathVariable Long id) {
        techStackService.removeById(id);
        return Result.success("删除成功");
    }
}
