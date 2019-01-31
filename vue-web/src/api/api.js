import request from '../assets/utils/request'

const api = {
  // 获取文章列表
  getArticleList (data = {}) {
    const path = 'api/admin/article/list'
    return request(path, { data: data })
  },
  getArticleById (data = {}) {
    const path = 'api/admin/article/get'
    return request(path, { data: data })
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default api
