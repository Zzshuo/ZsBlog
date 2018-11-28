<template>
  <div class="container">
    <section class="hero bd-rainbow">
      <div class="hero-body">
        <div class="container">
          <h1 class="title">
            Hero title
          </h1>
          <h2 class="subtitle">
            Hero subtitle
          </h2>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomeHero'
}
</script>

<style scoped lang="stylus">
  /*重写随机背景色*/
  .bd-rainbow {
    -webkit-animation: rainbow 8s ease infinite;
    animation: rainbow 8s ease infinite;
    background-image: linear-gradient(124deg, #00d1b2, #55edf5, #82ff59, #3273dc);
    background-size: 800% 800%;
  }
</style>
