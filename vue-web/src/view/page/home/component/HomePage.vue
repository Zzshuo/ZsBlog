<template>
  <div class="page-center">
    <div class="is-hidden-mobile">
      <Page :total="total" :current="current" @on-change="handleCurrentChange"/>
    </div>
    <div class="is-hidden-tablet">
      <Page :total="total" :current="current" size="small" @on-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  props: {
    pageNum: Number,
    total: Number,
    pageSize: Number
  },
  data () {
    return {
      'current': 1
    }
  },
  methods: {
    handleCurrentChange (val) {
      this.current = val

      console.log(`当前页: ${val}`)
    }
  },
  mounted () {
    // 注：window.onresize只能在项目内触发1次
    window.onresize = function windowResize () {
      // 通过捕获系统的onresize事件触发我们需要执行的事件
      // this.small = window.outerWidth <= 768
      // console.log(this.small)
    }
  }
}
</script>

<style scoped lang="stylus">
  .page-center
    text-align center
</style>
