import { axios } from '@/utils/request'

/**
 * 配置
 */
export function saveConfig (parameter) {
  const path = 'admin/config/save'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

export function getConfigListByType (parameter) {
  const path = 'admin/config/getListByType'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}

export function getALLConfigType (parameter) {
  const path = 'admin/config/getAllConfigType'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}
