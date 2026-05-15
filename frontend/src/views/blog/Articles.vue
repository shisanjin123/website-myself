<template>
  <div class="articles-page">
    <div class="container">
      <h1>文章列表</h1>
      <div class="article-list">
        <div v-for="article in articles" :key="article.id" class="article-item">
          <router-link :to="`/articles/${article.id}`" class="article-link">
            <div class="article-content">
              <h2>{{ article.title }}</h2>
              <p class="summary">{{ article.summary }}</p>
              <div class="meta">
                <span v-if="article.categoryName" class="category">{{ article.categoryName }}</span>
                <span class="date">{{ formatDate(article.createdAt) }}</span>
                <span class="views">{{ article.viewCount }} 次浏览</span>
              </div>
            </div>
          </router-link>
        </div>
        <div v-if="articles.length === 0" class="empty">
          <p>暂无文章</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getArticles } from '../../api/article'

const articles = ref([])

const formatDate = (dateStr) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleDateString('zh-CN')
}

onMounted(async () => {
  try {
    const res = await getArticles()
    articles.value = res.data || []
  } catch (e) {
    console.error('加载文章失败', e)
  }
})
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 0 20px;
}

h1 {
  text-align: center;
  font-size: 36px;
  margin-bottom: 40px;
  color: #303133;
}

.article-item {
  background: #fff;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s;
}

.article-item:hover {
  transform: translateY(-3px);
}

.article-link {
  display: block;
  padding: 24px;
}

.article-content h2 {
  font-size: 22px;
  color: #303133;
  margin-bottom: 10px;
}

.summary {
  color: #606266;
  line-height: 1.6;
  margin-bottom: 15px;
}

.meta {
  display: flex;
  gap: 15px;
  color: #909399;
  font-size: 14px;
}

.category {
  background: #ecf5ff;
  color: #409eff;
  padding: 2px 10px;
  border-radius: 4px;
}

.empty {
  text-align: center;
  padding: 60px;
  color: #909399;
}
</style>
