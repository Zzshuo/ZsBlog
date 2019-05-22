<template>
  <div id="app">
    <Header></Header>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "./views/common/header";
import Footer from "./views/common/footer";
export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
} 
</script>
<style lang="scss">
</style>
