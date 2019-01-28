<template>
  <div class="container zs-main">
    <div class="zs-box zs-article">
      <markdown-view :value="article.content"></markdown-view>
    </div>
  </div>
</template>

<script>
import HomeSide from '../home/component/HomeSide'
import MarkdownView from '../md/MarkdownView'
export default {
  name: 'Article',
  components: {
    HomeSide,
    MarkdownView
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
