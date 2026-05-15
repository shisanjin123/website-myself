package com.brix.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brix.blog.entity.Article;
import com.brix.blog.mapper.ArticleMapper;
import com.brix.blog.service.ArticleService;
import com.brix.blog.util.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> getPublishedArticles() {
        return baseMapper.selectPublishedArticles();
    }

    @Override
    public List<Article> getAllArticles() {
        return baseMapper.selectAllArticles();
    }

    @Override
    public Article getArticleDetail(Long id) {
        Article article = getById(id);
        if (article == null) {
            throw new BusinessException(404, "文章不存在");
        }
        // 增加浏览量
        article.setViewCount(article.getViewCount() + 1);
        updateById(article);
        return article;
    }
}
