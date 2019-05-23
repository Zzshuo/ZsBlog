import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: '首页',
      component: () => import('./views/home/home.vue')
    },
    {
      path: '/article',
      name: '关于',
      component: () => import('./views/article/article.vue')
    },
    {
      path: '/about',
      name: '关于',
      component: () => import('./views/about/about.vue')
    }
  ]
})
