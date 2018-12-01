<template>
  <div class="footer">
      <div class="hidden">
        <scroll-top></scroll-top>
      </div>
      <div class="container">
        <div class="content has-text-centered">
          <a href="https://bulma.io">
            <img src="https://lqzhgood.github.io/bulma-docs-cn/images/made-with-bulma.png" alt="Made with Bulma" width="128" height="24">
          </a>
          <div>
            <small>
              托管于
              <a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=wylo59db">阿里云</a> &amp;
              <a href="https://portal.qiniu.com/signup?code=3l8yx2v0f21ci">七牛云</a> ·
              <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备18111814号-1</a>
              <br/>
              Copyright©2018-2018 博客 · Powered by <a href="https://github.com/Zzshuo/ZsBlog"><strong>ZsBlog</strong></a> ·
            </small>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import ScrollTop from './ScrollTop'
export default {
  name: 'BlogFooter',
  components: {
    ScrollTop
  }
}
</script>

<style scoped lang="stylus">
</style>
