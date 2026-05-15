import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  // 博客界面
  {
    path: '/',
    component: () => import('../views/blog/Layout.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('../views/blog/Home.vue')
      },
      {
        path: 'projects',
        name: 'Projects',
        component: () => import('../views/blog/Projects.vue')
      },
      {
        path: 'articles',
        name: 'Articles',
        component: () => import('../views/blog/Articles.vue')
      },
      {
        path: 'articles/:id',
        name: 'ArticleDetail',
        component: () => import('../views/blog/ArticleDetail.vue')
      }
    ]
  },
  // 后台管理
  {
    path: '/admin/login',
    name: 'AdminLogin',
    component: () => import('../views/admin/Login.vue')
  },
  {
    path: '/admin',
    component: () => import('../views/admin/Layout.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        name: 'Dashboard',
        component: () => import('../views/admin/Dashboard.vue')
      },
      {
        path: 'personal',
        name: 'PersonalManage',
        component: () => import('../views/admin/PersonalManage.vue')
      },
      {
        path: 'tech-stack',
        name: 'TechStackManage',
        component: () => import('../views/admin/TechStackManage.vue')
      },
      {
        path: 'projects',
        name: 'ProjectManage',
        component: () => import('../views/admin/ProjectManage.vue')
      },
      {
        path: 'articles',
        name: 'ArticleManage',
        component: () => import('../views/admin/ArticleManage.vue')
      },
      {
        path: 'articles/edit/:id?',
        name: 'ArticleEdit',
        component: () => import('../views/admin/ArticleEdit.vue')
      },
      {
        path: 'categories',
        name: 'CategoryManage',
        component: () => import('../views/admin/CategoryManage.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    next('/admin/login')
  } else {
    next()
  }
})

export default router
