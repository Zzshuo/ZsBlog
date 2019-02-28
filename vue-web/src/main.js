import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import fastClick from 'fastclick'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import 'bulma/css/bulma.css'

import iView from 'iview'
import 'iview/dist/styles/iview.css'
// 自定义css
import './assets/styles/base.styl'
import api from './api/api'

//if (process.env.NODE_ENV !== 'production') require('@/mock')

Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(iView)

// fastClick
fastClick.attach(document.body)

//  将API方法绑定到全局
Vue.prototype.api = api
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
