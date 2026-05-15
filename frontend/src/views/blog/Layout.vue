<template>
  <div class="blog-layout">
    <header class="header">
      <div class="container">
        <div class="logo">
          <router-link to="/">Brix Blog</router-link>
        </div>
        <nav class="nav">
          <router-link to="/">首页</router-link>
          <router-link to="/projects">项目</router-link>
          <router-link to="/articles">文章</router-link>
          <router-link to="/admin" class="admin-link">管理</router-link>
        </nav>
      </div>
    </header>
    <main class="main">
      <router-view />
    </main>
    <footer class="footer">
      <div class="container">
        <p>&copy; 2024 Brix. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style scoped>
.blog-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.header .container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
}

.logo a {
  font-size: 24px;
  font-weight: bold;
  color: #409eff;
}

.nav {
  display: flex;
  gap: 20px;
}

.nav a {
  color: #606266;
  font-size: 16px;
  padding: 8px 16px;
  border-radius: 4px;
  transition: all 0.3s;
}

.nav a:hover,
.nav a.router-link-active {
  color: #409eff;
  background: #ecf5ff;
}

.admin-link {
  margin-left: 20px;
  border: 1px solid #409eff;
}

.main {
  flex: 1;
  padding: 40px 0;
}

.footer {
  background: #545c64;
  color: #fff;
  text-align: center;
  padding: 20px 0;
}
</style>
