<template>
  <div class="admin-layout">
    <aside class="sidebar">
      <div class="logo">
        <h2>Brix Blog</h2>
      </div>
      <el-menu :default-active="route.path" router>
        <el-menu-item index="/admin">
          <el-icon><Monitor /></el-icon>
          <span>仪表盘</span>
        </el-menu-item>
        <el-menu-item index="/admin/personal">
          <el-icon><User /></el-icon>
          <span>个人介绍</span>
        </el-menu-item>
        <el-menu-item index="/admin/tech-stack">
          <el-icon><Collection /></el-icon>
          <span>技术栈</span>
        </el-menu-item>
        <el-menu-item index="/admin/projects">
          <el-icon><Folder /></el-icon>
          <span>项目管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/articles">
          <el-icon><Document /></el-icon>
          <span>文章管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/categories">
          <el-icon><Menu /></el-icon>
          <span>分类管理</span>
        </el-menu-item>
      </el-menu>
    </aside>
    <div class="main-area">
      <header class="header">
        <div class="header-left">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ currentTitle }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              {{ userStore.userInfo?.nickname || '管理员' }}
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="home">博客首页</el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </header>
      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '../../stores/user'
import { Monitor, User, Collection, Folder, Document, Menu, ArrowDown } from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const titleMap = {
  '/admin': '仪表盘',
  '/admin/personal': '个人介绍',
  '/admin/tech-stack': '技术栈',
  '/admin/projects': '项目管理',
  '/admin/articles': '文章管理',
  '/admin/categories': '分类管理'
}

const currentTitle = computed(() => titleMap[route.path] || '管理后台')

const handleCommand = (command) => {
  if (command === 'home') {
    router.push('/')
  } else if (command === 'logout') {
    userStore.logout()
    router.push('/admin/login')
  }
}

onMounted(async () => {
  try {
    await userStore.fetchUserInfo()
  } catch (e) {
    console.error('获取用户信息失败', e)
  }
})
</script>

<style scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
}

.sidebar {
  width: 220px;
  background: #304156;
  color: #fff;
}

.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #263445;
}

.logo h2 {
  color: #fff;
  font-size: 20px;
}

.sidebar .el-menu {
  border-right: none;
  background: #304156;
}

.sidebar .el-menu-item {
  color: #bfcbd9;
}

.sidebar .el-menu-item:hover,
.sidebar .el-menu-item.is-active {
  background: #263445;
  color: #409eff;
}

.main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  background: #f0f2f5;
}

.header {
  height: 60px;
  background: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.user-info {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #606266;
}

.content {
  flex: 1;
  padding: 20px;
}
</style>
