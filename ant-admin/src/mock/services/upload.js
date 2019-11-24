import Mock from 'mockjs2'
import { builder } from '../util'

const getPolicy = options => {
  return builder({
    data: 'testpolicy'
  })
}

Mock.mock(/\/admin\/oss\/policy/, 'post', getPolicy)
