<template>
  <div>
    <blog-header></blog-header>
    <div class="container">
      {{article}}
    </div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import HomeSide from '../home/component/HomeSide'
export default {
  name: 'Article',
  components: {
    HomeSide
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
