import Vue from 'vue'
import Router from 'vue-router'
import Home from './view/page/home/Home'
import Back from './view/page/back/Back'
import Front from './view/page/front/Front'
import About from './view/page/about/About'
import Util from './view/page/util/Util'
import Article from './view/article/Article'
import Page401 from './view/error/401'
import Page404 from './view/error/404'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/back',
      name: 'Back',
      component: Back
    },
    {
      path: '/front',
      name: 'Front',
      component: Front
    },
    {
      path: '/util',
      name: 'Util',
      component: Util
    },
    {
      path: '/about',
      name: 'About',
      component: About
    },
    {
      path: '/article/:articleId',
      name: 'Article',
      component: Article
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
