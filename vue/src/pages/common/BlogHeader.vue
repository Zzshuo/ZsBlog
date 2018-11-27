<template>
  <div>
    <nav class="navbar is-primary is-fixed-top" role="navigation" aria-label="main navigation">
      <div class="container">
        <div class="navbar-brand">
          <!-- navbar items, navbar burger... -->
          <a class="navbar-item">
            <img src="https://lqzhgood.github.io/bulma-docs-cn/images/bulma-logo.png" width="112" height="28">
          </a>

          <div class="navbar-burger" data-target="navMenu" @click="openPopup">
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>

        <div class="navbar-menu" id="navMenu">
          <!-- navbar start, navbar end -->
          <div class="navbar-start">
            <!-- navbar items -->
            <a class="navbar-item">
              Home
            </a>
            <div class="navbar-item has-dropdown is-hoverable">
              <a class="navbar-link">
                Docs
              </a>
              <div class="navbar-dropdown is-boxed">
                <a class="navbar-item">
                  Overview
                </a>
                <a class="navbar-item">
                  Elements
                </a>
                <a class="navbar-item">
                  Components
                </a>
                <hr class="navbar-divider">
                <div class="navbar-item">
                  Version 0.6.2
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'BlogHeader',
  mounted () {
    document.addEventListener('DOMContentLoaded', this.burger())
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
    },
    openPopup () {

    }
  }
}
</script>

<style scoped lang="stylus">
</style>
