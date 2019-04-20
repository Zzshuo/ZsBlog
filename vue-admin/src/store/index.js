import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import common from './modules/common'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    common
  },
  getters: {
    sidebar: state => state.app.sidebar,
    device: state => state.app.device,
    token: state => state.user.token,
    avatar: state => state.user.avatar,
    name: state => state.user.name,
    roles: state => state.user.roles,
    stateMap: state => state.common.stateMap,
    allType: state => state.common.allType,
    allTag: state => state.common.allTag,
    tagNames: state => {
      const allTag = state.common.allTag
      const tagNames = []
      for (const tag of allTag) {
        tagNames.push(tag.name)
      }
      return tagNames
    },
    original: state => state.common.original
  }
})

export default store
