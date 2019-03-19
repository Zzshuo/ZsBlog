import Mock from 'mockjs'
import loginAPI from './login'
import remoteSearchAPI from './remoteSearch'
import transactionAPI from './transaction'
import { getArticle, saveArticle } from './Article'
import { getArticleList } from './ArticleList'
import { getTag } from './Tag.js'
import { getTagList } from './TagList.js'
import { getWebInfo } from './WebInfo.js'

// 修复在使用 MockJS 情况下，设置 withCredentials = true，且未被拦截的跨域请求丢失 Cookies 的问题
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
Mock.XHR.prototype.send = function() {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false
  }
  this.proxy_send(...arguments)
}

// 配置Ajax请求延时，可用来测试网络延迟大时项目中一些效果
// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
Mock.mock(/\/login\/login/, 'post', loginAPI.loginByUsername)
Mock.mock(/\/login\/logout/, 'post', loginAPI.logout)
Mock.mock(/\/user\/info\.*/, 'get', loginAPI.getUserInfo)

// 文章相关

// 搜索相关
Mock.mock(/\/search\/user/, 'get', remoteSearchAPI.searchUser)

// 账单相关
Mock.mock(/\/transaction\/list/, 'get', transactionAPI.getList)

Mock.mock(/\/article\/get/, getArticle)
Mock.mock(/\/article\/saveOrUpdate/, saveArticle)
Mock.mock(/\/article\/list/, getArticleList)
Mock.mock(/\/tag\/get/, getTag)
Mock.mock(/\/tag\/list/, getTagList)
Mock.mock(/\/web\/info/, getWebInfo)
export default Mock
