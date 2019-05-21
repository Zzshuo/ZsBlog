import request from '@/utils/request'

// 上传图片
export function addImage (data = {}) {
  return request({
    url: 'admin/file/addArticleImage',
    method: 'post',
    date: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}
