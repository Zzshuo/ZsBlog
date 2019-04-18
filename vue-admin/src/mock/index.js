import Mock from 'mockjs'
import userApi from './user'
import articleApi from './article'
import tagApi from './tag'
import webInfoApi from './webInfo'
import commonApi from './common'
import typeApi from './type'

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

// article
Mock.mock(/\/article\/get/, articleApi.getArticle)
Mock.mock(/\/article\/saveOrUpdate/, articleApi.saveArticle)
Mock.mock(/\/article\/list/, articleApi.getArticleList)

// tag
Mock.mock(/\/tag\/getTagById/, tagApi.getTag)
Mock.mock(/\/tag\/list/, tagApi.getTagList)
Mock.mock(/\/tag\/getAllTag/, tagApi.getAllTag())

// web
Mock.mock(/\/web\/info/, webInfoApi.getWebInfo)

// common
Mock.mock(/common\/getArticleStateMap/, commonApi.getArticleStateMap())

// type
Mock.mock(/\/type\/getAllType/, typeApi.getAllType())
export default Mock
