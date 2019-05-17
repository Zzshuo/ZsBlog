import request from '@/utils/request'

// 保存文章
export function saveArticle(data = {}) {
  const path = 'admin/article/save'
  return request({
    url: path,
    method: 'post',
    data
  })
}

// 获取文章列表
export function getArticleList(data = {}) {
  const path = 'admin/article/list'
  return request({
    url: path,
    method: 'post',
    data
  })
}

// 根据id获取文章
export function getArticleById(data = {}) {
  const path = 'admin/article/get'
  return request({
    url: path,
    method: 'post',
    data
  })
}

// 获取文章状态
export function getArticleStateMap(data = {}) {
  const path = 'admin/article/getArticleStateMap'
  return request({
    url: path,
    method: 'post',
    data
  })
}
