import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/home/Home'
import Article from '../pages/article/Article'
import About from '../pages/about/About'
import Page401 from '../pages/error/401'
import Page404 from '../pages/error/404'
import Demo from '../pages/md/Demo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/article/:id',
      name: 'Article',
      component: Article
    },
    {
      path: '/about',
      name: 'About',
      component: About
    },
    {
      path: '/401',
      name: '401',
      component: Page401
    },
    {
      path: '/404',
      name: '404',
      component: Page404
    },
    {
      path: '/demo',
      name: 'Demo',
      component: Demo
    }
  ]
})
