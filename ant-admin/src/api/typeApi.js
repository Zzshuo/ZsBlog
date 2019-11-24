import { axios } from '@/utils/request'

/**
 * 分类
 */
export function saveType (parameter) {
  return axios({
    url: 'admin/type/save',
    method: 'post',
    params: parameter
  })
}

export function deleteType (parameter) {
  return axios({
    url: 'admin/type/delete',
    method: 'post',
    params: parameter
  })
}

export function getTypeList (parameter) {
  return axios({
    url: 'admin/type/list',
    method: 'post',
    params: parameter
  })
}

// 获取所有标签
export function getAllTypes (parameter) {
  const path = 'admin/type/getAllTypes'
  return axios({
    url: path,
    method: 'post',
    parameter
  })
}
