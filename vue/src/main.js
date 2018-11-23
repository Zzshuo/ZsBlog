// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import fastClick from 'fastclick'
import store from './store'
import axios from './utils/api'

import 'element-ui/lib/theme-chalk/index.css'
// import './assets/styles/reset.css'
// import './assets/styles/border.css'

Vue.config.productionTip = false
Vue.use(ElementUI)

// fastClick
fastClick.attach(document.body)

//  将API方法绑定到全局
Vue.prototype.axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
