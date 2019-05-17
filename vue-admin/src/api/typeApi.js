import request from '@/utils/request'

/**
 * 分类
 */
export function saveType(data = {}) {
  return request({
    url: 'admin/type/save',
    method: 'post',
    data
  })
}

export function deleteType(data = {}) {
  return request({
    url: 'admin/type/delete',
    method: 'post',
    data
  })
}

export function getAllType(data = {}) {
  return request({
    url: 'admin/type/getAllType',
    method: 'post',
    data
  })
}
