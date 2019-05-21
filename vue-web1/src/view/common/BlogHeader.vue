<template>
  <div class="zs-box zs-fix-top" >
    <div class="container zs-main-header">
      <div style="display: flex; align-items: Center">
        <div>
          <router-link class="is-hidden-mobile img-item"  to="/">
            ZsBlog
          </router-link>
        </div>
        <Menu mode="horizontal" :theme="'light'" active-name="1">
          <MenuItem name="1" to="/">首页</MenuItem>
          <MenuItem name="2" to="/back">后端</MenuItem>
          <MenuItem name="3" to="/front">前端</MenuItem>
          <MenuItem name="4" to="/util">在线工具</MenuItem>
          <MenuItem name="5" to="/about">关于</MenuItem>
        </Menu>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogHeader'
}
</script>

<style scoped lang="stylus">
  .zs-fix-top
    top 0
    position: fixed;
    z-index: 30
    width 100%
    display flex
  .img-item
    padding 0.25rem 0.75rem
  .img-circle
    border-radius: 1500px
    width 38px
    height 38px
    max-height 38px !important
</style>
