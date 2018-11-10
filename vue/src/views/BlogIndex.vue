<template>
  <div>
    <blog-header></blog-header>
    <div>
      首页
    </div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>

  import BlogHeader from "../components/BlogHeader";
  import BlogFooter from "../components/BlogFooter";

  export default {
    name: "BlogIndex",
    components: {BlogFooter, BlogHeader}
  }
</script>

<style scoped>

</style>
