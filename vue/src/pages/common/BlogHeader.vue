<template>
  <div>
    <nav class="navbar is-fixed-top">
      <div class="container">
        <div class="navbar-brand">
          <!-- navbar items, navbar burger... -->
          <a class="navbar-item iconfont is-large" style="font-size: 1.5rem">
            &#xe6c5;
          </a>
          <div class="navbar-item is-hidden-desktop is-pulled-right">
            <header-search></header-search>
          </div>
          <div class="navbar-burger" data-target="navMenu">
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>

        <div class="navbar-menu" id="navMenu">
          <!-- navbar start, navbar end -->
          <div class="navbar-start">
            <!-- navbar items -->
            <router-link class="navbar-item" to="/">首页</router-link>
            <router-link class="navbar-item" to="/about">关于</router-link>
          </div>
          <div class="navbar-end">
            <div class="navbar-item is-hidden-mobile">
              <header-search></header-search>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import HeaderSearch from './HeaderSearch'
export default {
  name: 'BlogHeader',
  mounted () {
    document.addEventListener('DOMContentLoaded', this.burger())
  },
  components: {
    HeaderSearch
  },
  methods: {
    burger () {
      var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0)
      // Check if there are any navbar burgers
      if ($navbarBurgers.length > 0) {
        // Add a click event on each of them
        $navbarBurgers.forEach(function ($el) {
          $el.addEventListener('click', function () {
            // Get the target from the "data-target" attribute
            var target = $el.dataset.target
            var $target = document.getElementById(target)

            // Toggle the class on both the "navbar-burger" and the "navbar-menu"
            $el.classList.toggle('is-active')
            $target.classList.toggle('is-active')
          })
        })
      }
    }
  }
}
</script>

<style scoped lang="stylus">
</style>
