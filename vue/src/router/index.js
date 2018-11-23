import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/home/Home'
import Page401 from '@/pages/error/401'
import Page404 from '@/pages/error/404'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
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
    }
  ]
})
