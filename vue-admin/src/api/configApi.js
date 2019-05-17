import request from '@/utils/request'

/**
 * 配置
 */
export function saveConfig(data = {}) {
  const path = 'admin/config/save'
  return request({
    url: path,
    method: 'post',
    data
  })
}

export function getConfigListByType(data = {}) {
  const path = 'admin/config/getListByType'
  return request({
    url: path,
    method: 'post',
    data
  })
}

export function getConfigTypeMap(data = {}) {
  const path = 'admin/config/getConfigTypeMap'
  return request({
    url: path,
    method: 'post',
    data
  })
}
