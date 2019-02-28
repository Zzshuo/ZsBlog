'use strict'
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

const path = require('path')

const resolve = dir => {
  //__dirname是获取当前文件绝对路径的全局对象
  return path.join(__dirname, dir)
}

// 项目部署基础
// 默认情况下，我们假设你的应用将被部署在域的根目录下,
// 例如：https://www.my-app.com/
// 默认：'/'
// 如果您的应用程序部署在子路径中，则需要在这指定子路径
// 例如：https://www.foobar.com/my-app/
// 需要将它改为'/my-app/'
// iview-admin线上演示打包路径： https://file.iviewui.com/admin-dist/
const BASE_URL = process.env.NODE_ENV === 'production'
  ? '/'
  : '/'

module.exports = {
  publicPath: BASE_URL,
  // 构建输出目录
  outputDir: path.resolve(__dirname, '../blog-web/src/main/webapp'),
  //放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
  assetsDir: 'static',
  // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径。
  indexPath: path.resolve(__dirname, '../blog-web/src/main/webapp/index.html'),

  // 如果你不需要使用eslint，把lintOnSave设为false即可
  lintOnSave: true,

  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src')) // key,value自行定义，比如.set('@@', resolve('src/components'))
      .set('_c', resolve('src/components'))
  },
  // 设为false打包时不生成.map文件
  productionSourceMap: false,
  devServer: {
    open: true,
    host: '0.0.0.0',
    port: 8090,
    // 路由接口代理配置
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true, //可否跨域
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
