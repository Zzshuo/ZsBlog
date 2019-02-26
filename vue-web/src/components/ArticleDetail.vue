<template>
  <div class="info-row">
    <div class="info-row-item">
      <Icon type="ios-calendar" />
      <span class="info-row-item-span">{{createTime | capitalize}}</span>
    </div>
    <div class="info-row-item">
      <Icon type="md-eye" />
      <span class="info-row-item-span">{{views}}</span>
    </div>
    <div class="info-row-item">
      <Icon type="ios-text" />
      <span class="info-row-item-span">{{comments}}</span>
    </div>
  </div>
</template>

<script>
import date from '../assets/utils/date'
export default {
  name: 'ArticleDetail',
  props: {
    createTime: String,
    views: Number,
    comments: Number
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
  .ivu-icon
    padding-bottom 2px
    padding-right 2px
  .info-row
    color: #b3bac1
    display: inline-flex;
    white-space: nowrap;
  .info-row-item
    padding: 0 .75rem 0 0;
    border-radius: 1px;
  .info-row-item-span
    font-size .875rem
</style>
