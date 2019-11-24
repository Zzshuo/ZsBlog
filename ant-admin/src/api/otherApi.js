import { axios } from '@/utils/request'

// 获取网站信息
export function getWebInfo (parameter) {
  return axios({
    url: 'admin/web/info',
    method: 'post',
    params: parameter
  })
}

// 保存个人信息
export function savePersonInfo (parameter) {
  return axios({
    url: 'admin/person/save',
    method: 'post',
    params: parameter
  })
}
// 获取个人信息
export function getPersonInfo (parameter) {
  return axios({
    url: 'admin/person/get',
    method: 'post',
    params: parameter
  })
}
