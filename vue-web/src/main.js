// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import fastClick from 'fastclick'
import store from './store'
import api from './assets/utils/api'
import mavonEditor from 'mavon-editor'

import 'mavon-editor/dist/css/index.css'
import 'bulma/css/bulma.css'

import iView from 'iview'
import 'iview/dist/styles/iview.css'
// 自定义css
import './assets/styles/base.styl'

Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(iView)

// fastClick
fastClick.attach(document.body)

//  将API方法绑定到全局
Vue.prototype.api = api

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
