<template>
  <div>
    <blog-header></blog-header>
    <hr/>
    <div>
      用户名:<input type="text" v-model="loginVo.username" placeholder="请输入用户名"/>
      <br/>
      密码:<input type="password" v-model="loginVo.password" placeholder="请输入密码"/>
      <br/>
      <button v-on:click="login">login</button>
      <br/>
      <textarea cols="30" rows="10" v-model="responseVo"></textarea>
    </div>
    <hr/>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
  import BlogHeader from "../components/BlogHeader";
  import BlogFooter from "../components/BlogFooter";

  export default {
    name: "BlogLogin",
    components: {BlogFooter, BlogHeader},

    data() {
      return {
        loginVo: {username: '', password: ''},
        responseVo: []
      }
    },
    methods: {
      login() {
        this.$axios.post(
          '/api/admin/submit',
          {
            username: this.loginVo.username,
            password: this.loginVo.password
          },
        ).then(response => {
          this.responseVo = JSON.stringify(response.data)
          if (response.data.code === 200) {
            this.$router.replace({path: '/index'})
          }
        }).catch(function (err) {
          if (err.response) {
            console.log(err.response)
            //控制台打印错误返回的内容
          }
        })

      }
    }
  }
</script>

<style scoped>

</style>
