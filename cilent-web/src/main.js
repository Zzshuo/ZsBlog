// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import fastClick from 'fastclick'
import api from './assets/utils/api'
Vue.config.productionTip = false

// 解决移动端300ms延迟问题
fastClick.attach(document.body)

//  将API方法绑定到全局
Vue.prototype.api = api

// 全局组件

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
