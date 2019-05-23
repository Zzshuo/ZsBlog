<template>
  <div id="app">
    <my-header></my-header>
    <div class="main-container">
      <a-row type="flex" justify="center">
        <a-col class="main-content">
          <router-view />
        </a-col>
      </a-row>
    </div>
    <my-footer></my-footer>
  </div>
</template>

<script>
import MyHeader from './views/common/MyHeader'
import MyFooter from './views/common/MyFooter'
export default {
  name: 'App',
  components: {
    MyHeader,
    MyFooter
  }
}

</script>
<style lang="scss" scoped>
</style>
