import request from '@/utils/request'

/**
 * 标签
 */
export function saveTag(data = {}) {
  const path = 'admin/tag/save'
  return request({
    url: path,
    method: 'post',
    data
  })
}

export function deleteTag(data = {}) {
  const path = 'admin/tag/delete'
  return request({
    url: path,
    method: 'post',
    data
  })
}

// 获取所有标签
export function getAllTag(data = {}) {
  const path = 'admin/tag/getAllTag'
  return request({
    url: path,
    method: 'post',
    data
  })
}
