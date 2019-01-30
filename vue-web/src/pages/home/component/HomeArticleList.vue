<template>
  <div>
    <div v-for="article of list" :key="article.id">
      <router-link :to="/article/ + article.id">
        <div class="zs-content" :class="{itemHover:itemHoverIndex===article.id}" @mouseenter="mouseEnter(article.id)" @mouseleave="mouseLeave">
          <div class="content-left">
              <img v-if="article.img" :src="article.img">
              <img v-else src="../../../assets/img/moon.jpg">
          </div>
          <div class="content-right">
            <div class="meta-row">
              <div>java</div>
            </div>
            <div class="title-row">
                <div class="title-content is-4">{{article.title}}</div>
            </div>
            <div class="info-row">
                <div class="info-row-item">
                  <Icon type="ios-calendar" />
                  <span class="info-row-item-span">{{article.createTime | capitalize}}</span>
                </div>
                <div class="info-row-item">
                  <Icon type="md-eye" />
                  <span class="info-row-item-span">1</span>
                </div>
                <div class="info-row-item">
                  <Icon type="ios-text" />
                  <span class="info-row-item-span">1</span>
                </div>
            </div>
          </div>
        </div>
      </router-link>
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
  data () {
    return {
      itemHoverIndex: null
    }
  },
  methods: {
    mouseEnter (index) {
      this.itemHoverIndex = index
    },
    mouseLeave () {
      this.itemHoverIndex = null
    }
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
  .zs-content
    display: flex;
    align-items: center;
    min-width: 0;
    border-radius 2px
    box-shadow 0 1px 2px 0 rgba(0,0,0,.05)
    border-bottom 1px solid rgba(178,186,194,.15)
  .content-left
    width: 86px
    height: 86px
    max-width 86px
    max-height  86px
  .content-right
    margin 0 1rem

  .meta-row
    color: #b3bac1
  .title-row
    ellipsis()
    margin: .25rem 0 .5rem
  .title-content
    font-size: 1.4rem;
    font-weight: 500;
    line-height: 1.2;
    color: #2e3135;
  .info-row
    color: #b3bac1
    display: inline-flex;
    white-space: nowrap;
  .info-row-item
    padding: 0 .75rem 0 0;
    border-radius: 1px;
  .info-row-item-span
    font-size .875rem
  .itemHover
    background-color #fafafa;
</style>
