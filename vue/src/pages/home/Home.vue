<template>
  <div>
    <blog-header></blog-header>
    <home-hero></home-hero>
    <div class="container zs-main">
      <div class="tile is-ancestor">
        <div class="tile is-vertical is-8">
          <div class="tile is-parent is-vertical">
            <div class="tile is-child">
              <home-article-list :list="articleList"></home-article-list>
            </div>
            <div class="tile is-child">
              <home-page :pageNum="pageNum"
                         :pageSize="pageSize"
                         :total="total"
              ></home-page>
            </div>
          </div>
        </div>
        <div class="tile is-parent">
          <div class="tile is-child">
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
import HomeArticleList from './component/HomeArticleList'
import HomePage from './component/HomePage'
import HomeSide from './component/HomeSide'

export default {
  name: 'Home',
  components: {
    HomeHero,
    HomeArticleList,
    HomePage,
    HomeSide
  },
  data () {
    return {
      articleList: [],
      pageNum: 1,
      total: 1,
      pageSize: 10,
      reqVo: {}
    }
  },
  methods: {
    getArticleList () {
      this.api.getArticleList(this.reqVo).then((res) => {
        if (res && res.code === 200) {
          const data = res.data
          this.articleList = data.list
          this.pageNum = data.pageNum
          this.total = data.total
          this.pageSize = data.pageSize
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
</style>
