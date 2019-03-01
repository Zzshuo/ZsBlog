import request from '../assets/utils/request'

const api = {
  // 获取文章列表
  getArticleList (data = {}) {
    const path = 'article/list'
    return request(path, { data: data })
  },
  // 根据id获取文章
  getArticleById (data = {}) {
    const path = 'article/get'
    return request(path, { data: data })
  },
  // 获取标签列表
  getTagList (data = {}) {
    const path = 'tag/list'
    return request(path, { data: data })
  },
  getTagById (data = {}) {
    const path = 'tag/get'
    return request(path, { data: data })
  },
  // 获取网站信息
  getWebInfo (data = {}) {
    const path = 'web/info'
    return request(path, { data: data })
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default api
