<template>
  <div class="my-header">
    <div class="left">
      <div style="display: flex; align-items:center; height:1rem">hh</div>
      <div>
        <a-menu class="a-menu" mode="horizontal" defaultSelectedKeys="[home]">
          <a-menu-item key="home">首页</a-menu-item>
          <a-menu-item key="article">文章</a-menu-item>
          <a-menu-item key="about">关于</a-menu-item>
        </a-menu>
      </div>
    </div>
    <div class="right"></div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {}
  },
  methods: {},
  created () {},
  mounted () {}
}
</script>
<style lang="scss" scoped>
.my-header {
  display: flex;
  justify-content: space-around;
  width: 100%;
  .left {
    display: inherit;
  }
  .right {
    display: inherit;
  }
}
</style>
