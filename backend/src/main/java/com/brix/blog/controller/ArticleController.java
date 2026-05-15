package com.brix.blog.controller;

import com.brix.blog.entity.Article;
import com.brix.blog.service.ArticleService;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章管理", description = "文章管理")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "获取已发布文章列表")
    @GetMapping("/articles")
    public Result<List<Article>> getPublishedArticles() {
        return Result.success(articleService.getPublishedArticles());
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/articles/{id}")
    public Result<Article> getArticle(@PathVariable Long id) {
        return Result.success(articleService.getArticleDetail(id));
    }

    @Operation(summary = "获取所有文章（后台）")
    @GetMapping("/admin/articles")
    public Result<List<Article>> getAllArticles() {
        return Result.success(articleService.getAllArticles());
    }

    @Operation(summary = "发表文章")
    @PostMapping("/admin/articles")
    public Result<?> addArticle(@RequestBody Article article) {
        articleService.save(article);
        return Result.success("发表成功");
    }

    @Operation(summary = "更新文章")
    @PutMapping("/admin/articles/{id}")
    public Result<?> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        articleService.updateById(article);
        return Result.success("更新成功");
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/admin/articles/{id}")
    public Result<?> deleteArticle(@PathVariable Long id) {
        articleService.removeById(id);
        return Result.success("删除成功");
    }
}
