import request from '@/utils/request'

export * from './articleApi'
export * from './configApi'
export * from './tagApi'
export * from './typeApi'
export * from './uploadApi'
export * from './userApi'

// 导出对象，然后在全局注册或在单个vue文件下调用

// 获取网站信息
export function getWebInfo(data = {}) {
  return request({
    url: 'admin/web/info',
    method: 'post',
    data
  })
}
