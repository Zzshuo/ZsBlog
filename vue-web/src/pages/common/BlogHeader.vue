<template>
  <div>
    <nav class="navbar is-transparent is-fixed-top zs-box">
      <div class="container zs-main-header">
        <div class="navbar-brand">
          <!-- navbar items, navbar burger... -->

          <div class="navbar-item img-item">
            <div class="dropdown is-left is-hoverable">
              <router-link class="navbar-item" to="/">
                ZsBlog
              </router-link>
              <div class="dropdown-menu" role="menu">
                <div class="dropdown-content">
                  <router-link class="dropdown-item" to="/edit">写文章</router-link>
                  <hr class="dropdown-divider">
                  <router-link class="dropdown-item" to="/">退出</router-link>
                </div>
              </div>
            </div>
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
            <router-link class="navbar-item" to="/">首页</router-link>
            <router-link class="navbar-item" to="/">后端</router-link>
            <router-link class="navbar-item" to="/">前端</router-link>
            <router-link class="navbar-item" to="/">在线工具</router-link>
            <router-link class="navbar-item" to="/">其他</router-link>
            <router-link class="navbar-item" to="/about">关于</router-link>
          </div>
          <div class="navbar-end">
            <div class="navbar-item">
              <Input search placeholder="Enter something..." />
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
  components: {
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
  .img-item
    padding 0.25rem 0.75rem
  .img-circle
    border-radius: 1500px
    width 38px
    height 38px
    max-height 38px !important
</style>
