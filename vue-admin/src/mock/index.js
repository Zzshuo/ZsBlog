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
Mock.mock(/\/api\/user\/login/, 'post', userApi.login)
Mock.mock(/\/api\/user\/info/, 'get', userApi.getInfo)
Mock.mock(/\/api\/user\/logout/, 'post', userApi.logout)

// article
Mock.mock(/\/api\/article\/get/, articleApi.getArticle)
Mock.mock(/\/api\/article\/saveOrUpdate/, articleApi.saveArticle)
Mock.mock(/\/api\/article\/list/, articleApi.getArticleList)

// tag
Mock.mock(/\/api\/tag\/getTagById/, tagApi.getTag)
Mock.mock(/\/api\/tag\/list/, tagApi.getTagList)
Mock.mock(/\/api\/tag\/getAllTag/, tagApi.getAllTag())

// web
Mock.mock(/\/api\/web\/info/, webInfoApi.getWebInfo)

// common
Mock.mock(/\/api\/common\/getArticleStateMap/, commonApi.getArticleStateMap())

// type
Mock.mock(/\/api\/type\/getAllType/, typeApi.getAllType())
export default Mock
