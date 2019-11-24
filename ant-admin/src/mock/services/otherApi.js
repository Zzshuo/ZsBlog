import Mock from 'mockjs2'
import { builder } from '../util'

const getWebInfo = options => {
  return builder({
    data: {

    }
  })
}

const getPersionInfo = options => {
  return builder({
    data: {
      name: 'zshuo',
      qq: '694814357',
      weixin: 'zhengshuo_',
      github: 'https://github.com/Zzshuo',
      email: '694814357@qq.com',
      introduce: '简单的人',
      avatar: 'https://avatars0.githubusercontent.com/u/12946820?s=460&v=4'
    }
  })
}

const savePersionInfo = options => {
  return builder({}, 'xxx', 200)
}
Mock.mock(/\/admin\/web\/info/, 'post', getWebInfo)
Mock.mock(/\/admin\/person\/info/, 'post', getPersionInfo)
Mock.mock(/\/admin\/person\/save/, 'post', savePersionInfo)
