import axios from 'axios'

// 创建axios 实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 10000 // 请求超时时间
})

// request 拦截器
service.interceptors.request.use(
  config => {
    // 这里可以自定义一些config 配置
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = token
    }
    return config
  },
  error => {
    //  这里处理一些请求出错的情况

    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    // 这里处理一些response 正常放回时的逻辑

    return res
  },
  error => {
    // 这里处理一些response 出错时的逻辑
    // 断网 或者 请求超时 状态
    if (!error.response) {
      // 请求超时状态
      if (error.message.includes('timeout')) {
        this.$message.error('请求超时，请检查网络是否连接正常')
      } else {
        // 可以展示断网组件
        this.$message.error('请求失败，请检查网络是否已连接')
      }
      return
    }
    return Promise.reject(error)
  }
)

export default service
