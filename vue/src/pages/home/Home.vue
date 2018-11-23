<template>
  <div>
    <el-container>
      <el-header>123</el-header>
      <el-main>345</el-main>
      <el-footer>132</el-footer>
    </el-container>

    <el-button @click="watchHtml">watch</el-button>
    <el-button @click="unWatchHtml">unwatch</el-button>
    <md-editor ref="markdown"></md-editor>
  </div>
</template>

<script>
import MdEditor from '../MdEditor'
export default {
  name: 'Home',
  components: {
    MdEditor
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
      this.axios.post('/api/admin/article/list', this.reqVo)
        .then(this.handleClickSucc)
    },
    handleClickSucc (res) {
      const data = res.data
      this.content = data.data.list
      console.log(data)
    },
    watchHtml: function () {
      this.$refs['markdown'].watch()
    },
    unWatchHtml: function () {
      this.$refs['markdown'].unwatch()
    }
  }
}
</script>

<style scoped lang="stylus">

</style>
