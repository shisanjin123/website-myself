package com.brix.blog.controller;

import com.brix.blog.entity.Category;
import com.brix.blog.service.CategoryService;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "分类管理", description = "分类管理")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取分类列表")
    @GetMapping("/categories")
    public Result<List<Category>> getAllCategories() {
        return Result.success(categoryService.getAllCategories());
    }

    @Operation(summary = "添加分类")
    @PostMapping("/admin/categories")
    public Result<?> addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Result.success("添加成功");
    }

    @Operation(summary = "更新分类")
    @PutMapping("/admin/categories/{id}")
    public Result<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.updateById(category);
        return Result.success("更新成功");
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/admin/categories/{id}")
    public Result<?> deleteCategory(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.success("删除成功");
    }
}
