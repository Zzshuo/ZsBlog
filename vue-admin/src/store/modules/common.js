import * as api from '../../api/api'

const common = {
  state: {
    // 文章状态
    stateMap: {},
    // 文章分类
    allType: [],
    // 标签
    allTag: [],
    original: []
  },

  mutations: {
    SET_STATEMAP: (state, stateMap) => {
      state.stateMap = stateMap
    },
    SET_ALLTYPE: (state, allType) => {
      state.allType = allType
    },
    SET_ALLTAG: (state, allTag) => {
      state.allTag = allTag
    },
    SET_ORIGINAL: (state, original) => {
      state.original = original
    }
  },

  actions: {
    // 获取文章状态
    getStateMap({ commit }) {
      return new Promise((resolve, reject) => {
        api.getArticleStateMap().then(data => {
          commit('SET_STATEMAP', data)
          resolve()
        })
      })
    },
    // 获取文章分类
    getAllType({ commit }) {
      return new Promise((resolve, reject) => {
        api.getAllType().then(data => {
          commit('SET_ALLTYPE', data)
          resolve()
        })
      })
    },
    // 获取标签
    getAllTag({ commit }) {
      return new Promise((resolve, reject) => {
        api.getAllTag().then(data => {
          commit('SET_ALLTAG', data)
          resolve()
        })
      })
    },
    //
    getOriginal({ commit }) {
      const options = [{ value: true, name: '原创' }, { value: false, name: '转载' }]
      commit('SET_ORIGINAL', options)
    }
  }
}

export default common
