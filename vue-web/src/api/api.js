import request from '../libs/request'

const api = {
  // 获取文章列表
  getArticleList (data = {}) {
    const path = 'api/article/list'
    return request(path, { data: data })
  },
  // 根据id获取文章
  getArticleById (data = {}) {
    const path = 'api/article/get'
    return request(path, { data: data })
  },
  // 获取标签列表
  getTagList (data = {}) {
    const path = 'api/tag/list'
    return request(path, { data: data })
  },
  getTagById (data = {}) {
    const path = 'api/tag/get'
    return request(path, { data: data })
  },
  // 获取网站信息
  getWebInfo (data = {}) {
    const path = 'api/web/info'
    return request(path, { data: data })
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default api
