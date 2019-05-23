<template>
  <div class="container">
    <a-row>
      <a-col class="left" :span="24" :lg="18" :xl="18">1</a-col>
      <a-col class="right" :span="24" :lg="6" :xl="6">2</a-col>
    </a-row>
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
  .left {
    background-color: aqua
  }

  .right {
    background-color: red
  }
</style>
