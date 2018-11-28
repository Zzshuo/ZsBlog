<template>
  <div>
    <blog-header></blog-header>
    <home-hero></home-hero>
    <div class="container">
      <div class="tile is-ancestor">
        <div class="tile is-vertical is-8">
          <div class="tile is-parent is-vertical">
            <div class="tile is-child zs-content">
              <home-article></home-article>
              <home-article></home-article>
            </div>
            <div class="tile is-child zs-content">
              <home-page></home-page>
            </div>
          </div>
        </div>
        <div class="tile is-parent">
          <div class="tile is-child zs-content">
            <home-side></home-side>
          </div>
        </div>
      </div>
    </div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import HomeHero from './component/HomeHero'
import HomeArticle from './component/HomeArticle'
import HomePage from './component/HomePage'
import HomeSide from './component/HomeSide'
export default {
  name: 'Home',
  components: {
    HomeHero,
    HomeArticle,
    HomePage,
    HomeSide
  },
  data () {
    return {
      content: 'xxx',
      reqVo: {
        id: 1
      }
    }
  },
  methods: {
    handleClick () {
      this.axios.getArticleList(this.reqVo).then((res) => {
        this.content = res
        console.log('123' + res)
      }).catch((err) => {
        console.log('234' + err)
      })
    }
  }
}
</script>

<style scoped lang="stylus">
  .zs-content
    padding 1rem
</style>
