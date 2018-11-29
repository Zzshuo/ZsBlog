<template>
  <div class="article-info">
    <icon icon-class="calendar"/>
    <span class="article-info-span">{{cal}}</span>
    <icon icon-class="eye"/>
    <span class="article-info-span">{{eye}}</span>
    <icon icon-class="check"/>
    <span class="article-info-span">{{comment}}</span>
  </div>
</template>

<script>
export default {
  name: 'ArticleInfo',
  props: {
    // message:String
    calendar: {
      type: String,
      required: false,
      default: '0'
    },
    eye: {
      type: String,
      required: false,
      default: '0'
    },
    comment: {
      type: String,
      required: false,
      default: '0'
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
</style>
