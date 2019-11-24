import { axios } from '@/utils/request'

// oss上传签名生成
export function policy (parameter) {
  const path = '/admin/oss/policy'
  return axios({
    url: path,
    method: 'post',
    params: parameter
  })
}
