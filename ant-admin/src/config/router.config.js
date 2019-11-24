// eslint-disable-next-line
import { UserLayout, BasicLayout, RouteView, BlankLayout, PageView } from '@/layouts'

export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BasicLayout,
    meta: { title: '首页' },
    redirect: '/home/index',
    children: [
      // 首页
      {
        path: '/home/index',
        name: 'home',
        component: () => import('@/views/home/Index'),
        meta: { title: '首页', icon: 'home', keepAlive: true, permission: ['home'] }
      },
      // 写文章
      {
        path: '/article/articleEdit/:id([1-9]\\d*)?',
        name: 'articleEdit',
        component: () => import('@/views/article/ArticleEdit'),
        meta: { title: '写文章', icon: 'edit', keepAlive: true, permission: ['article'] }
      },
      // 文章列表
      {
        path: '/article/articleList',
        name: 'articleList',
        component: () => import('@/views/article/ArticleList'),
        meta: { title: '文章列表', icon: 'copy', keepAlive: true, permission: ['article'] }
      },
      // 标签管理
      {
        path: '/tag/tagList',
        name: 'tag',
        component: () => import('@/views/tag/TagList'),
        meta: { title: '标签管理', icon: 'tags', keepAlive: true, permission: ['tag'] }
      },
      // 分类管理
      {
        path: '/type/typeList',
        name: 'type',
        component: () => import('@/views/type/TypeList'),
        meta: { title: '分类管理', icon: 'profile', keepAlive: true, permission: ['type'] }
      },
      // 评论管理
      // 个人设置
      {
        path: '/personal/personalInfo',
        name: 'personal',
        component: () => import('@/views/personal/PersonalInfo'),
        meta: { title: '个人设置', icon: 'user', keepAlive: true, permission: ['personal'] }
      },
      // 系统设置
      {
        path: '/config',
        component: RouteView,
        redirect: '/config/configList',
        name: 'config',
        meta: { title: '系统设置', icon: 'appstore', keepAlive: true, permission: ['config'] },
        children: [
          {
            path: '/config/configList',
            name: 'configList',
            component: () => import('@/views/config/ConfigList'),
            meta: { title: '配置项', icon: 'setting', keepAlive: true, permission: ['config'] }
          },
          {
            path: '/config/icon-selector',
            name: 'TestIconSelect',
            component: () => import('@/views/config/IconSelectorView'),
            meta: { title: 'IconSelector', icon: 'tool', keepAlive: true, permission: ['config'] }
          }
        ]
      },
      // //配置项
      // //web动态路由
      // //权限控制
      // Exception
      {
        path: '/exception',
        name: 'exception',
        component: RouteView,
        redirect: '/exception/403',
        meta: { title: '异常页', icon: 'warning', permission: ['exception'] },
        children: [
          {
            path: '/exception/403',
            name: 'Exception403',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/403'),
            meta: { title: '403', permission: ['exception'] }
          },
          {
            path: '/exception/404',
            name: 'Exception404',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
            meta: { title: '404', permission: ['exception'] }
          },
          {
            path: '/exception/500',
            name: 'Exception500',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/500'),
            meta: { title: '500', permission: ['exception'] }
          }
        ]
      }
    ]
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      }
    ]
  },

  {
    path: '/test',
    component: BlankLayout,
    redirect: '/test/home',
    children: [
      {
        path: 'home',
        name: 'TestHome',
        component: () => import('@/views/Home')
      }
    ]
  },

  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  }

]
