import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import * as api from './api/api'
import 'ant-design-vue/dist/antd.css'

Vue.config.productionTip = false

//  将API方法绑定到全局
Vue.prototype.api = api

Vue.use(Antd)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
