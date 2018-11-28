<template>
  <div class="is-inline-flex">
    <router-link class="navbar-item" to="/">首页</router-link>
    <router-link class="navbar-item" to="/">后端</router-link>
    <router-link class="navbar-item" to="/">前端</router-link>
    <router-link class="navbar-item" to="/">在线工具</router-link>
    <router-link class="navbar-item" to="/">其他</router-link>
    <router-link class="navbar-item" to="/about">关于</router-link>
  </div>
</template>

<script>
export default {
  name: 'HeaderMenu'
}
</script>

<style scoped lang="stylus">
</style>
