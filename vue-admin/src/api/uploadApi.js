import request from '@/utils/request'

// 上传文章图片
export function addImage(data = {}) {
  return request({
    url: 'admin/file/addImage',
    method: 'post',
    data: data,
    headers: {'Content-Type': 'multipart/form-data; charset=utf-8'}
  })
}

// 上传文章图片
export function addArticleImage(data = {}) {
  return request({
    url: 'admin/file/addArticleImage',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data; charset=utf-8' }
  })
}

// 上传封面图片
export function addCoverImage(data = {}) {
  return request({
    url: 'admin/file/addCoverImage',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data; charset=utf-8' }
  })
}

// oss上传签名生成
export function policy(data = {}) {
  return request({
    url:'/admin/oss/policy',
    method:'post',
    data
  })
}
