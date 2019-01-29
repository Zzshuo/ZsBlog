<template>
  <div class="page-center">
    <div class="is-hidden-mobile">
      <!--<el-pagination-->
        <!--@current-change="handleCurrentChange"-->
        <!--:current-page="pageNum"-->
        <!--:page-sizes="[10, 20, 30]"-->
        <!--:page-size="pageSize"-->
        <!--layout="total, prev, pager, next, jumper"-->
        <!--:total="total">-->
      <!--</el-pagination>-->
      <Page :total="total" />
    </div>
    <div class="is-hidden-tablet">
      <!--<el-pagination-->
        <!--small-->
        <!--@current-change="handleCurrentChange"-->
        <!--:current-page="pageNum"-->
        <!--:page-sizes="[10, 20, 30]"-->
        <!--:page-size="pageSize"-->
        <!--layout="total, prev, pager, next, jumper"-->
        <!--:total="total">-->
      <!--</el-pagination>-->
      <Page :total="total" size="small" />
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
      'small': true
    }
  },
  methods: {
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
    }
  },
  mounted () {
    // 注：window.onresize只能在项目内触发1次
    window.onresize = function windowResize () {
      // 通过捕获系统的onresize事件触发我们需要执行的事件
      this.small = window.outerWidth <= 768
      // console.log(this.small)
    }
  }
}
</script>

<style scoped lang="stylus">
  .page-center
    text-align center
</style>
