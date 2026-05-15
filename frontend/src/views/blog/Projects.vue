<template>
  <div class="projects-page">
    <div class="container">
      <h1>项目展示</h1>
      <div class="project-grid">
        <div v-for="project in projects" :key="project.id" class="project-card">
          <div class="project-header">
            <h3>{{ project.name }}</h3>
            <div class="links">
              <a v-if="project.githubUrl" :href="project.githubUrl" target="_blank">GitHub</a>
              <a v-if="project.demoUrl" :href="project.demoUrl" target="_blank">Demo</a>
            </div>
          </div>
          <p class="desc">{{ project.description }}</p>
          <div class="tech-tags">
            <span v-for="tech in project.techStack?.split(',')" :key="tech" class="tag">
              {{ tech.trim() }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getProjects } from '../../api/project'

const projects = ref([])

onMounted(async () => {
  try {
    const res = await getProjects()
    projects.value = res.data || []
  } catch (e) {
    console.error('加载项目失败', e)
  }
})
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

h1 {
  text-align: center;
  font-size: 36px;
  margin-bottom: 40px;
  color: #303133;
}

.project-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 24px;
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

.project-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 12px;
}

.project-header h3 {
  font-size: 20px;
  color: #303133;
}

.links {
  display: flex;
  gap: 12px;
}

.links a {
  color: #409eff;
  font-size: 14px;
}

.links a:hover {
  text-decoration: underline;
}

.desc {
  color: #606266;
  line-height: 1.6;
  margin-bottom: 16px;
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
</style>
