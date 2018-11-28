<template>
  <div>
    <nav class="navbar is-fixed-top">
      <div class="container">
        <div class="navbar-brand">
          <!-- navbar items, navbar burger... -->
          <router-link class="" to="/" >
             图标
          </router-link>
          <div class="navbar-item is-hidden-mobile is-hidden-desktop">
            <header-menu></header-menu>
          </div>
          <div class="navbar-item is-hidden-desktop">
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
          <div class="navbar-end">
            <div class="navbar-item is-hidden-mobile">
              <header-menu></header-menu>
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
import HeaderMenu from './HeaderMenu'
export default {
  name: 'BlogHeader',
  mounted () {
    document.addEventListener('DOMContentLoaded', this.burger())
  },
  components: {
    HeaderSearch,
    HeaderMenu
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
