import request from '../utils/request'

const api = {
  /**
   * 文章
   */
  // 保存文章
  saveArticle(data = {}) {
    const path = 'api/article/save'
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
  // 获取文章状态
  getArticleStateMap(data = {}) {
    const path = 'api/article/getArticleStateMap'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 分类
   */
  saveType(data = {}) {
    const path = 'api/type/save'
    return request({ url: path, method: 'post', data })
  },
  deleteType(data = {}) {
    const path = 'api/type/delete'
    return request({ url: path, method: 'post', data })
  },
  getAllType(data = {}) {
    const path = 'api/type/getAllType'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 标签
   */
  saveTag(data = {}) {
    const path = 'api/tag/save'
    return request({ url: path, method: 'post', data })
  },
  deleteTag(data = {}) {
    const path = 'api/tag/delete'
    return request({ url: path, method: 'post', data })
  },
  // 获取所有标签
  getAllTag(data = {}) {
    const path = 'api/tag/getAllTag'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 配置
   */
  saveConfig(data = {}) {
    const path = 'api/config/save'
    return request({ url: path, method: 'post', data })
  },
  getConfigListByType(data = {}) {
    const path = 'api/config/getListByType'
    return request({ url: path, method: 'post', data })
  },
  getConfigTypeMap(data = {}) {
    const path = 'api/config/getConfigTypeMap'
    return request({ url: path, method: 'post', data })
  },

  // 获取网站信息
  getWebInfo(data = {}) {
    const path = 'api/web/info'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 上传文件
   */
  // 上传文章图片
  addArticleImage(data = {}) {
    const path = 'api/file/addArticleImage'
    return request({ url: path, method: 'post', data, headers: { 'Content-Type': 'multipart/form-data' }})
  },
  // 上传封面图片
  addCoverImage(data = {}) {
    const path = 'api/file/addCoverImage'
    return request({ url: path, method: 'post', data, headers: { 'Content-Type': 'multipart/form-data' }})
  }
}

// 导出对象，然后在全局注册或在单个vue文件下调用
export default api

