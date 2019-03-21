import Mock from 'mockjs'
import userApi from './user'
import articleApi from './article'
import tagApi from './tag'
import webInfoApi from './webInfo'

// Fix an issue with setting withCredentials = true, cross-domain request lost cookies
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
Mock.XHR.prototype.send = function() {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false
  }
  this.proxy_send(...arguments)
}
// Mock.setup({
//   timeout: '350-600'
// })

// User
Mock.mock(/\/user\/login/, 'post', userApi.login)
Mock.mock(/\/user\/info/, 'get', userApi.getInfo)
Mock.mock(/\/user\/logout/, 'post', userApi.logout)

// 文章相关
Mock.mock(/\/article\/get/s, articleApi.getArticle)
Mock.mock(/\/article\/saveOrUpdate/, articleApi.saveArticle)
Mock.mock(/\/article\/list/, articleApi.getArticleList)
Mock.mock(/\/tag\/get/, tagApi.getTag)
Mock.mock(/\/tag\/list/, tagApi.getTagList)
Mock.mock(/\/web\/info/, webInfoApi.getWebInfo)
export default Mock
