import request from '../utils/request'

const api = {
  /**
   * 文章
   */
  // 保存文章
  saveArticle(data = {}) {
    const path = 'admin/article/save'
    return request({ url: path, method: 'post', data })
  },
  // 获取文章列表
  getArticleList(data = {}) {
    const path = 'admin/article/list'
    return request({ url: path, method: 'post', data })
  },
  // 根据id获取文章
  getArticleById(data = {}) {
    const path = 'admin/article/get'
    return request({ url: path, method: 'post', data })
  },
  // 获取文章状态
  getArticleStateMap(data = {}) {
    const path = 'admin/article/getArticleStateMap'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 分类
   */
  saveType(data = {}) {
    const path = 'admin/type/save'
    return request({ url: path, method: 'post', data })
  },
  deleteType(data = {}) {
    const path = 'admin/type/delete'
    return request({ url: path, method: 'post', data })
  },
  getAllType(data = {}) {
    const path = 'admin/type/getAllType'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 标签
   */
  saveTag(data = {}) {
    const path = 'admin/tag/save'
    return request({ url: path, method: 'post', data })
  },
  deleteTag(data = {}) {
    const path = 'admin/tag/delete'
    return request({ url: path, method: 'post', data })
  },
  // 获取所有标签
  getAllTag(data = {}) {
    const path = 'admin/tag/getAllTag'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 配置
   */
  saveConfig(data = {}) {
    const path = 'admin/config/save'
    return request({ url: path, method: 'post', data })
  },
  getConfigListByType(data = {}) {
    const path = 'admin/config/getListByType'
    return request({ url: path, method: 'post', data })
  },
  getConfigTypeMap(data = {}) {
    const path = 'admin/config/getConfigTypeMap'
    return request({ url: path, method: 'post', data })
  },

  // 获取网站信息
  getWebInfo(data = {}) {
    const path = 'admin/web/info'
    return request({ url: path, method: 'post', data })
  },

  /**
   * 上传文件
   */
  // 上传文章图片
  addArticleImage(data = {}) {
    const path = 'admin/file/addArticleImage'
    return request({ url: path, method: 'post', data, headers: { 'Content-Type': 'multipart/form-data' }})
  },
  // 上传封面图片
  addCoverImage(data = {}) {
    const path = 'admin/file/addCoverImage'
    return request({ url: path, method: 'post', data, headers: { 'Content-Type': 'multipart/form-data' }})
  }
}

// 导出对象，然后在全局注册或在单个vue文件下调用
export default api

