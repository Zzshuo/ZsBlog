<template>
  <div>
    <blog-header></blog-header>
    <a class="button" @click="handleClick">button</a>
    <div style="height: 100rem; background-color: #666">{{content}}</div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
export default {
  name: 'Home',
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
</style>
