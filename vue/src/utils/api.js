import axios from '../utils/axios_util'

const mock = true

const requests = {
  // 获取文章列表
  getArticleList (data = {}) {
    if (mock) {
      return axios('api/admin/article/list.json', {data: data, method: 'get'})
    } else {
      return axios('api/admin/article/list', {data: data})
    }
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default requests
