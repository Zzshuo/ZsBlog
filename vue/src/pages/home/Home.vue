<template>
  <div>
    <blog-header></blog-header>
    <home-hero></home-hero>
    <div class="container">
      <div class="tile is-ancestor">
        <div class="tile is-vertical is-8">
          <div class="tile is-parent is-vertical">
            <div class="tile is-child zs-content">
              <home-article :list = "articleList"></home-article>
            </div>
            <div class="tile is-child zs-content">
              <home-page :page = "page"></home-page>
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
      articleList: [],
      page: [],
      reqVo: {
      }
    }
  },
  methods: {
    getArticleList () {
      this.axios.getArticleList(this.reqVo).then((res) => {
        if (res && res.code === 200) {
          const data = res.data
          this.articleList = data.list
          this.page.isFirstPage = data.isFirstPage
          this.page.isLastPage = data.isLastPage
          this.page.hasPreviousPage = data.hasPreviousPage
          this.page.hasNextPage = data.hasNextPage
          this.page.navigatepageNums = data.navigatepageNums
          this.page.navigateFirstPage = data.navigateFirstPage
          this.page.navigateLastPage = data.navigateLastPage
        } else {
          console.log(res.code + res.message)
        }
      })
    }
  },
  mounted () {
    this.getArticleList()
  }
}
</script>

<style scoped lang="stylus">
  .zs-content
    padding 1rem
</style>
