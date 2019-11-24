import { axios } from '@/utils/request'

/**
 * 标签
 */
export function getTagList (parameter) {
  const path = 'admin/tag/list'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

export function saveTag (parameter) {
  const path = 'admin/tag/save'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

export function deleteTag (parameter) {
  const path = 'admin/tag/delete'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

// 获取所有标签
export function getAllTags (parameter) {
  const path = 'admin/tag/getAllTags'
  return axios({
    url: path,
    method: 'post',
    parameter
  })
}
