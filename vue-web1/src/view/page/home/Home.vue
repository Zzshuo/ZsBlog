<template>
  <div class="container zs-main">
    <div class="tile is-ancestor">
      <div class="tile is-8">
        <div class="tile is-parent is-vertical">
          <div class="tile is-child">
            <home-article-list :list="articleList"></home-article-list>
          </div>
          <div class="tile is-child">
            <div class="page-center">
              <div class="is-hidden-mobile">
                <Page :total="total" :current="pageNum" @on-change="handleCurrentChange"/>
              </div>
              <div class="is-hidden-tablet">
                <Page :total="total" :current="pageNum" size="small" @on-change="handleCurrentChange"/>
              </div>
            </div>
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
</template>

<script>
import HomeArticleList from './component/HomeArticleList'
import HomeSide from './component/HomeSide'

export default {
  name: 'Home',
  components: {
    HomeArticleList,
    HomeSide
  },
  props: {
    showType: {
      type: String,
      default: 'normal'
    }
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
      this.reqVo.showType = this.showType
      this.reqVo.pageNum = this.pageNum
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
    },
    handleCurrentChange (val) {
      this.pageNum = val
      this.getArticleList()
      console.log(`当前页: ${val}`)
    }
  },
  mounted () {
    this.getArticleList()
  }
}
</script>

<style scoped lang="stylus">
  .page-center
    text-align center
</style>
