import axios from './axios_util'

const mock = true

const requests = {
  // 获取文章列表
  getArticleList (data = {}) {
    const path = 'api/admin/article/list'
    if (mock) {
      return axios(path + '.json', {data: data, method: 'get'})
    } else {
      return axios(path, {data: data})
    }
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default requests
