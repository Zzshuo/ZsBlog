<template>
  <div>
    <div v-for="article of list" :key="article.id" class="columns is-mobile zs-relative">
      <div class="column is-one-quarter zs-column-left">
        <figure class="image is-square">
          <img v-if="article.img" :src="article.img">
          <img v-else src="../../../assets/img/default.gif">
        </figure>
      </div>
      <div class="column">
        <div>
          <p class="title is-4">{{article.title}}</p>
        </div>
        <div>
          <p class="subtitle is-6 is-hidden-mobile">{{article.description}}</p>
        </div>
        <div class="zs-info">
          <div class="article-info">
            <icon icon-class="icon-calendar"/>
            <span class="article-info-span">{{article.createTime | capitalize}}</span>
            <icon icon-class="icon-attention"/>
            <span class="article-info-span">1</span>
            <icon icon-class="icon-comment"/>
            <span class="article-info-span">1</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import date from '../../../assets/utils/date'
export default {
  name: 'HomeArticle',
  props: {
    list: Array
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      return date.formatDate(new Date(value))
    }
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
  .article-info
    color #999
  .article-info-span
    font-size .875rem
    padding-right .25rem
</style>
