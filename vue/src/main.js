// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import fastClick from 'fastclick'
import store from './store'
import axios from './assets/utils/api'
import mavonEditor from 'mavon-editor'

// import './assets/styles/reset.css'
// import './assets/styles/border.css'
import 'mavon-editor/dist/css/index.css'
import 'bulma/css/bulma.css'

import './assets/icons/iconfont'

import BlogHeader from './pages/common/BlogHeader'
import BlogFooter from './pages/common/BlogFooter'
import Icon from './assets/icons/icon'

// 自定义css
import './assets/styles/zs-style.css'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(ElementUI)

// fastClick
fastClick.attach(document.body)

//  将API方法绑定到全局
Vue.prototype.axios = axios

// 全局组件
Vue.component('BlogHeader', BlogHeader)
Vue.component('BlogFooter', BlogFooter)
Vue.component('Icon', Icon)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
