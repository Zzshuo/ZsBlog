<template>
  <div>
    <button @click="handleClick">list</button>
    <el-button @click="handleClick">默认按钮</el-button>
    <div class="">{{this.content}}</div>
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
      this.axios.post('/api/admin/article/list', this.reqVo)
        .then(this.handleClickSucc)
    },
    handleClickSucc (res) {
      this.content = res.data.data.list
      console.log(res)
      console.log(this.content)
    }
  }
}
</script>

<style scoped lang="stylus">
</style>
