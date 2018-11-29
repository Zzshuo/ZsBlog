<template>
  <div class="article-info">
    <icon icon-class="icon-calendar"/>
    <span class="article-info-span">{{cal}}</span>
    <icon icon-class="icon-attention"/>
    <span class="article-info-span">{{eye}}</span>
    <icon icon-class="icon-comment"/>
    <span class="article-info-span">{{comment}}</span>
  </div>
</template>

<script>
export default {
  name: 'ArticleInfo',
  props: {
    calendar: {
      type: String,
      required: false,
      default: '0'
    },
    eye: {
      type: Number,
      required: false,
      default: 0
    },
    comment: {
      type: Number,
      required: false,
      default: 0
    }
  },
  data () {
    return {
      cal: this.calendar
    }
  }
}
</script>

<style scoped lang="stylus">
  .article-info
    color #999
  .article-info-span
    font-size .875rem
    padding-right .25rem
</style>
