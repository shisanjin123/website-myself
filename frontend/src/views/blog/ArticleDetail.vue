<template>
  <div class="article-detail">
    <div class="container">
      <article v-if="article">
        <h1>{{ article.title }}</h1>
        <div class="meta">
          <span v-if="article.categoryName" class="category">{{ article.categoryName }}</span>
          <span class="date">{{ formatDate(article.createdAt) }}</span>
          <span class="views">{{ article.viewCount }} 次浏览</span>
        </div>
        <div class="content" v-html="renderedContent"></div>
      </article>
      <div v-else class="loading">加载中...</div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getArticle } from '../../api/article'

const route = useRoute()
const article = ref(null)

const renderedContent = computed(() => {
  if (!article.value?.content) return ''
  // 简单的Markdown渲染（生产环境建议使用markdown-it）
  return article.value.content
    .replace(/\n/g, '<br>')
    .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
    .replace(/\*(.*?)\*/g, '<em>$1</em>')
})

const formatDate = (dateStr) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleDateString('zh-CN')
}

onMounted(async () => {
  try {
    const res = await getArticle(route.params.id)
    article.value = res.data
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
  font-size: 36px;
  color: #303133;
  margin-bottom: 20px;
}

.meta {
  display: flex;
  gap: 15px;
  color: #909399;
  font-size: 14px;
  margin-bottom: 40px;
  padding-bottom: 20px;
  border-bottom: 1px solid #ebeef5;
}

.category {
  background: #ecf5ff;
  color: #409eff;
  padding: 2px 10px;
  border-radius: 4px;
}

.content {
  line-height: 1.8;
  color: #303133;
  font-size: 16px;
}

.loading {
  text-align: center;
  padding: 60px;
  color: #909399;
}
</style>
