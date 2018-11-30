import request from './request'

const mock = true

const api = {
  // 获取文章列表
  getArticleList (data = {}) {
    const path = 'api/admin/article/list'
    if (mock) {
      return request(path + '.json', {data: data, method: 'get'})
    } else {
      return request(path, {data: data})
    }
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default api
