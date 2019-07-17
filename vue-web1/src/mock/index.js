import Mock from 'mockjs'

import { getArticle } from './Article'
import { getArticleList } from './ArticleList'
import { getTag } from './Tag.js'
import { getTagList } from './TagList.js'
import { getWebInfo } from './WebInfo.js'

// 配置Ajax请求延时，可用来测试网络延迟大时项目中一些效果
Mock.setup({
  timeout: 1000
})

// 登录相关和获取用户信息
Mock.mock(/\/article\/get/, getArticle)
Mock.mock(/\/article\/list/, getArticleList)
Mock.mock(/\/tag\/get/, getTag)
Mock.mock(/\/tag\/list/, getTagList)
Mock.mock(/\/web\/info/, getWebInfo)

export default Mock
