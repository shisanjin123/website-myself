<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>文章数量</template>
          <div class="stat-value">{{ stats.articles }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>项目数量</template>
          <div class="stat-value">{{ stats.projects }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>技术栈</template>
          <div class="stat-value">{{ stats.techStacks }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>分类数量</template>
          <div class="stat-value">{{ stats.categories }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card>
          <template #header>最近文章</template>
          <div v-for="article in recentArticles" :key="article.id" class="recent-item">
            <span class="title">{{ article.title }}</span>
            <span class="date">{{ formatDate(article.createdAt) }}</span>
          </div>
          <div v-if="recentArticles.length === 0" class="empty">暂无文章</div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>快速操作</template>
          <div class="quick-actions">
            <el-button type="primary" @click="$router.push('/admin/articles/edit')">
              写文章
            </el-button>
            <el-button @click="$router.push('/admin/projects')">管理项目</el-button>
            <el-button @click="$router.push('/admin/tech-stack')">管理技术栈</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getAllArticles } from '../../api/article'
import { getProjects } from '../../api/project'
import { getTechStacks } from '../../api/techStack'
import { getCategories } from '../../api/category'

const stats = ref({
  articles: 0,
  projects: 0,
  techStacks: 0,
  categories: 0
})

const recentArticles = ref([])

const formatDate = (dateStr) => {
  if (!dateStr) return ''
  return new Date(dateStr).toLocaleDateString('zh-CN')
}

onMounted(async () => {
  try {
    const [articlesRes, projectsRes, techRes, categoriesRes] = await Promise.all([
      getAllArticles(),
      getProjects(),
      getTechStacks(),
      getCategories()
    ])
    stats.value.articles = articlesRes.data?.length || 0
    stats.value.projects = projectsRes.data?.length || 0
    stats.value.techStacks = techRes.data?.length || 0
    stats.value.categories = categoriesRes.data?.length || 0
    recentArticles.value = (articlesRes.data || []).slice(0, 5)
  } catch (e) {
    console.error('加载数据失败', e)
  }
})
</script>

<style scoped>
.stat-value {
  font-size: 36px;
  font-weight: bold;
  color: #409eff;
  text-align: center;
}

.recent-item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #ebeef5;
}

.recent-item:last-child {
  border-bottom: none;
}

.title {
  color: #303133;
}

.date {
  color: #909399;
  font-size: 14px;
}

.empty {
  text-align: center;
  color: #909399;
  padding: 20px;
}

.quick-actions {
  display: flex;
  gap: 10px;
}
</style>
