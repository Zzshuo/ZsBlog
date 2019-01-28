<template>
  <div>
    <div v-for="article of list" :key="article.id" class="columns is-mobile zs-relative zs-item">
      <div class="column is-one-quarter zs-column-left">
        <router-link :to="/article/ + article.id">
          <figure class="image is-square">
            <img v-if="article.img" :src="article.img">
            <img v-else src="../../../assets/img/default.gif">
          </figure>
        </router-link>
      </div>
      <div class="column">
        <div class="meta-row">
          <div>java</div>
        </div>
        <div class="title-row">
          <router-link :to="/article/ + article.id">
            <div class="title-content is-4">{{article.title}}</div>
          </router-link>
        </div>
        <div class="info-row">
            <div class="info-box">
              <Icon type="ios-calendar" />
              <span class="article-info-span">{{article.createTime | capitalize}}</span>
            </div>
            <div class="info-box">
              <Icon type="md-eye" />
              <span class="article-info-span">1</span>
            </div>
            <div class="info-box">
              <Icon type="ios-text" />
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
    max-width 110px
  .article-info-span
    font-size .875rem
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
  .info-box
    padding: 0 .75rem 0 0;
    border-radius: 1px;
</style>
