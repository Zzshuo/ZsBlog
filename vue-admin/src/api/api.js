import request from '../utils/request'

const api = {
  // 保存文章
  saveArticle(data = {}) {
    const path = 'api/article/saveOrUpdate'
    return request({ url: path, method: 'post', data })
  },
  // 获取文章列表
  getArticleList(data = {}) {
    const path = 'api/article/list'
    return request({ url: path, method: 'post', data })
  },
  // 根据id获取文章
  getArticleById(data = {}) {
    const path = 'api/article/get'
    return request({ url: path, method: 'post', data })
  },
  // 获取标签列表
  getTagList(data = {}) {
    const path = 'api/tag/list'
    return request({ url: path, method: 'post', data })
  },
  getTagById(data = {}) {
    const path = 'api/tag/get'
    return request({ url: path, method: 'post', data })
  },
  // 获取网站信息
  getWebInfo(data = {}) {
    const path = 'api/web/info'
    return request({ url: path, method: 'post', data })
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default api

