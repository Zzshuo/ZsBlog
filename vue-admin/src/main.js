import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import Cookies from 'js-cookie'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission'
import api from './api/api' // permission control
import * as filters from './filters' // global filters
import * as formatUtil from './utils/formatUtil'

// if (process.env.NODE_ENV !== 'production') require('@/mock')
Vue.use(ElementUI, { size: Cookies.get('size') || 'medium' })

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false
//  将API方法绑定到全局
Vue.prototype.api = api
Vue.prototype.FormatUtil = formatUtil
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
