<template>
  <div class="columns is-mobile zs-relative">
    <div class="column is-one-quarter zs-column-left">
      <figure class="image is-square">
        <img src="https://lqzhgood.github.io/bulma-docs-cn/images/placeholders/128x128.png">
      </figure>
    </div>
    <div class="column">
      <div>
        <p class="title is-4">Verti啊都市风口浪尖街坊邻居卡萨冷风机阿里双方均暗示阿发啊了附近</p>
      </div>
      <div>
        <p class="subtitle is-6 is-hidden-mobile">Top tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop</p>
      </div>
      <div class="zs-info">
        <article-info :calendar="20180808" :eye="12" :comment="20"></article-info>
      </div>
    </div>
  </div>
</template>

<script>
import ArticleInfo from './ArticleInfo'
export default {
  name: 'HomeArticle',
  components: {
    ArticleInfo
  }
}
</script>

<style scoped lang="stylus">
  @import "../../../assets/styles/mixins.styl"
  .zs-column-left
    max-width 152px
  .zs-info
    position absolute
    bottom .5rem
  .title, .subtitle
    ellipsis()
  .subtitle
    -webkit-line-clamp: 3 //在一个块元素显示的文本的行数
</style>
