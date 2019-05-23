<template>
  <div class="my-header">
    <div class="header-content">
      <div class="left">
        <div style="display: flex; align-items:center;">hh</div>
        <div>
          <a-menu mode="horizontal" @click="menuClick">
            <a-menu-item key="home">首页</a-menu-item>
            <a-menu-item key="article">文章</a-menu-item>
            <a-menu-item key="about">关于</a-menu-item>
          </a-menu>
        </div>
      </div>
      <div class="right"></div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {}
  },
  methods: {
    menuClick ({ item, key, keyPath }) {
      console.log(item)
      console.log(key)
      console.log(keyPath)
      this.$router.push(key)
    }
  },
  created () {},
  mounted () {}
}
</script>
<style lang="scss" scoped>
.my-header {
  .left {
    display: inherit;
  }
  .right {
    display: inherit;
  }
}
</style>
