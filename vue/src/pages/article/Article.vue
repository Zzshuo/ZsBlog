<template>
  <div>
    <blog-header></blog-header>
    <div class="container zs-main" style="max-width: 1000px">
      <markdown-view :value="article.content"></markdown-view>
      <div class="navbar-divider"></div>
      <markdown-viewdemo :value="article.content"></markdown-viewdemo>
    </div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import HomeSide from '../home/component/HomeSide'
import MarkdownView from '../md/MarkdownView'
import MarkdownViewdemo from '../md/MarkdownViewdemo'
export default {
  name: 'Article',
  components: {
    HomeSide,
    MarkdownView,
    MarkdownViewdemo
  },
  data () {
    return {
      article: {},
      id: this.$route.params.id
    }
  },
  methods: {
    getArticle () {
      this.api.getArticleById(this.id).then((res) => {
        if (res && res.code === 200) {
          this.article = res.data
        } else {
          console.log(res.code + res.message)
        }
      })
    }
  },
  mounted () {
    this.getArticle()
  }
}
</script>

<style scoped lang="stylus">
</style>
