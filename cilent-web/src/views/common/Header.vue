<template>
  <div>
    <b-navbar  toggleable="md" type="light" variant="light" fixed="top">

      <b-navbar-brand href="#">
        <img src="../../img/logo.jpeg" alt="zsblog" style="height: 40px">
        ZsBlog
      </b-navbar-brand>
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-collapse is-nav id="nav_collapse">
        <b-navbar-nav>
          <b-nav-item href="#">首页</b-nav-item>
          <b-nav-item href="#">后端</b-nav-item>
          <b-nav-item href="#">前端</b-nav-item>
          <b-nav-item href="#">其他</b-nav-item>
          <b-nav-item href="#">关于</b-nav-item>
          <b-nav-item href="#">在线工具</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav  class="ml-auto">
          <b-nav-form>
            <b-form-input class="mr-sm-2" type="text" placeholder="Search"></b-form-input>
            <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

          <!-- Navbar dropdowns -->
          <b-nav-item-dropdown text="User" right>
            <b-dropdown-item href="#">Account</b-dropdown-item>
            <b-dropdown-item href="#">Settings</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.min.css'
export default {
  name: 'Header'
}
</script>

<style scoped lang="stylus">
.navbar
  height 5rem
</style>
