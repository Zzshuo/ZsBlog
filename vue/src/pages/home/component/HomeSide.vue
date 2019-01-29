<template>
  <div>
    <Card :bordered="false" class="zs-box">
      <div style="text-align:center">
        <img src="../../../assets/img/logo.png">
        <h3>A high quality UI Toolkit based on Vue.js</h3>
      </div>
    </Card>
    <Card :bordered="false" class="zs-box">
      <div>
        <p>
          <Icon type="md-home" />
          <Divider type="vertical" />
          关于我
        </p>
        <Divider />
        <p>Content of no border type. Content of no border type. Content of no border type. Content of no border type. </p>
      </div>
    </Card>
    <Card class="zs-box">
      <div>
        <p>
          <Icon type="md-pricetags" />
          <Divider type="vertical" />
          文章标签
        </p>
        <Divider />
        <p></p>
      </div>
    </Card>
    <Card class="zs-box">
      <div>
        <p>
          <Icon type="md-alert" />
          <Divider type="vertical" />
          网站信息
        </p>
        <Divider />
        <p>
          <Icon type="md-document" />文章总数：2 篇<br>
          <Icon type="md-pricetag" />标签总数：7 个<br>
          <Icon type="ios-folder-open" />分类总数：3 个<br>
          <Icon type="ios-text" />留言数量：1 条<br>
          <Icon type="md-person" />在线人数：1人<br>
          <Icon type="ios-time" />运行天数：113天<br>
        </p>
      </div>
    </Card>
  </div>
</template>

<script>
export default {
  name: 'HomeSide'
}
</script>

<style scoped lang="stylus">
  .svg-icon
    font-size 1.125rem
    margin-right .25rem
  .icon-large
    font-size 1.25rem
  .zs-box
    margin-bottom 1.5rem
  .ivu-divider-horizontal
    margin .5rem 0
</style>
