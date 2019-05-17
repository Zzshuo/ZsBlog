import request from '@/utils/request'

// 上传文章图片
export function addArticleImage(data = {}) {
  return request({
    url: 'admin/file/addArticleImage',
    method: 'post',
    date: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 上传封面图片
export function addCoverImage(data = {}) {
  return request({
    url: 'admin/file/addCoverImage',
    method: 'post',
    date: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}
