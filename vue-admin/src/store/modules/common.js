import api from '../../api/api'

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
        api.getArticleStateMap().then(response => {
          const res = response.data
          if (res && res.code === 20000) {
            commit('SET_STATEMAP', res.data)
            resolve()
          } else {
            reject(res)
          }
        })
      })
    },
    // 获取文章分类
    getAllType({ commit }) {
      return new Promise((resolve, reject) => {
        api.getAllType().then(response => {
          const res = response.data
          if (res && res.code === 20000) {
            commit('SET_ALLTYPE', res.data)
            resolve()
          } else {
            reject(res)
          }
        })
      })
    },
    // 获取标签
    getAllTag({ commit }) {
      return new Promise((resolve, reject) => {
        api.getAllTag().then(response => {
          const res = response.data
          if (res && res.code === 20000) {
            commit('SET_ALLTAG', res.data)
            resolve()
          } else {
            reject(res)
          }
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
