import axios from 'axios'
// import {Message} from 'element-ui'

/*
* 增加请求拦截器
*/
axios.interceptors.request.use((config) => {
  // 预处理请求的信息
  return config
}, (error) => {
  // 预处理请求有异常error时抛出错误
  return Promise.reject(error)
}
)

/*
* 增加相应拦截器
*/
axios.interceptors.response.use((response) => {
  // 预处理相应的数据
  const responseVo = response.data
  if (responseVo) {
    checkCode(responseVo)
    return responseVo
  }
}, (error) => {
  // Message.error(error)
  // 错误返回
  return Promise.reject(error)
}
)

/**
 * 状态码校验
 * @param responseVo
 * @return {*}
 */
function checkCode (responseVo) {
  switch (responseVo.code) {
    case 200:
      break
    default:
    // Message.error(responseVo.code + ':' + responseVo.message)
  }
}

/**
 * 返回axios方法
 * @param url（如果传绝对地址则baseURL不会追加到url之前）
 * @param method
 * @param timeout
 * @param data
 * @param headers
 * @param dataType
 * @param baseURL
 * @returns {AxiosPromise}
 */
export default function request (url, {
  // 默认求情方式post
  method = 'post',
  // 超时
  timeout = 10000,
  // 请求主题
  data = {},
  // 请求头
  headers = {'Content-Type': 'application/json;charset=UTF-8;'},
  // 文件类型
  dataType = 'json',
  baseURL = '/'
}) {
  // 可根据不同method动态配置headers
  if (method === 'get') {
    headers = Object.assign({}, headers)
  } else {
    headers = Object.assign({}, headers)
  }
  const config = {
    method: method,
    timeout: timeout,
    url: url,
    // 在外部文件配置axios的基础路径 ip地址
    baseURL: baseURL,
    data: data,
    headers: headers,
    dataType: dataType
  }
  return axios(config)
}
