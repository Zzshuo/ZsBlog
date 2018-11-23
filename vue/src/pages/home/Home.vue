<template>
  <div>
    <el-container>
      <el-header>123</el-header>
      <el-main>345</el-main>
      <el-footer>132</el-footer>
    </el-container>

    <el-button @click="handleClick">test</el-button>
    <div>{{content}}</div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  components: {
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

</style>
