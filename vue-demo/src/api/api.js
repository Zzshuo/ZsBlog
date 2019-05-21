import request from '@/utils/request'

export * from './uploadApi'

// 导出对象，然后在全局注册或在单个vue文件下调用

// demo
export function getXXX (data = {}) {
  return request({
    url: 'xxx',
    method: 'post',
    data
  })
}
