import { axios } from '@/utils/request'

// 保存文章
export function saveArticle (parameter) {
  const path = 'admin/article/save'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

// 获取文章列表
export function getArticleList (parameter) {
  const path = 'admin/article/list'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

// 根据id获取文章
export function getArticleById (parameter) {
  const path = 'admin/article/get'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

// 更改文章状态
export function articleUpdateState (parameter) {
  const path = 'admin/article/updateState'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}
