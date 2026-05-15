<template>
  <div class="home">
    <!-- 个人介绍 -->
    <section class="hero">
      <div class="container">
        <div class="hero-content">
          <h1>{{ personal.title || '关于我' }}</h1>
          <p class="welcome">{{ personal.welcomeText || '欢迎来到我的博客' }}</p>
          <p class="desc">{{ personal.content || '' }}</p>
        </div>
      </div>
    </section>

    <!-- 技术栈 -->
    <section class="section tech-stack">
      <div class="container">
        <h2>技术栈</h2>
        <div class="tech-grid">
          <div v-for="tech in techStacks" :key="tech.id" class="tech-item">
            <div class="tech-icon">{{ tech.name.charAt(0) }}</div>
            <span>{{ tech.name }}</span>
          </div>
        </div>
      </div>
    </section>

    <!-- 精选项目 -->
    <section class="section projects">
      <div class="container">
        <h2>精选项目</h2>
        <div class="project-grid">
          <div v-for="project in projects" :key="project.id" class="project-card">
            <h3>{{ project.name }}</h3>
            <p>{{ project.description }}</p>
            <div class="tech-tags">
              <span v-for="tech in project.techStack?.split(',')" :key="tech" class="tag">
                {{ tech.trim() }}
              </span>
            </div>
          </div>
        </div>
        <div class="more">
          <router-link to="/projects" class="btn">查看更多项目</router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getPersonalIntro } from '../../api/personal'
import { getTechStacks } from '../../api/techStack'
import { getProjects } from '../../api/project'

const personal = ref({})
const techStacks = ref([])
const projects = ref([])

onMounted(async () => {
  try {
    const [personalRes, techRes, projectRes] = await Promise.all([
      getPersonalIntro(),
      getTechStacks(),
      getProjects()
    ])
    personal.value = personalRes.data || {}
    techStacks.value = techRes.data || []
    projects.value = (projectRes.data || []).slice(0, 3)
  } catch (e) {
    console.error('加载数据失败', e)
  }
})
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.hero {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #fff;
  padding: 80px 0;
  text-align: center;
}

.hero h1 {
  font-size: 48px;
  margin-bottom: 20px;
}

.hero .welcome {
  font-size: 20px;
  margin-bottom: 15px;
  opacity: 0.9;
}

.hero .desc {
  font-size: 16px;
  opacity: 0.8;
  max-width: 600px;
  margin: 0 auto;
}

.section {
  padding: 60px 0;
}

.section h2 {
  text-align: center;
  font-size: 32px;
  margin-bottom: 40px;
  color: #303133;
}

.tech-stack {
  background: #f5f7fa;
}

.tech-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 20px;
}

.tech-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.tech-icon {
  width: 60px;
  height: 60px;
  background: #409eff;
  color: #fff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  font-weight: bold;
}

.project-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 20px;
}

.project-card {
  background: #fff;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.project-card:hover {
  transform: translateY(-5px);
}

.project-card h3 {
  font-size: 20px;
  margin-bottom: 10px;
  color: #303133;
}

.project-card p {
  color: #606266;
  margin-bottom: 15px;
  line-height: 1.6;
}

.tech-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag {
  background: #ecf5ff;
  color: #409eff;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 12px;
}

.more {
  text-align: center;
  margin-top: 40px;
}

.btn {
  display: inline-block;
  padding: 12px 32px;
  background: #409eff;
  color: #fff;
  border-radius: 4px;
  font-size: 16px;
  transition: background 0.3s;
}

.btn:hover {
  background: #66b1ff;
}
</style>
