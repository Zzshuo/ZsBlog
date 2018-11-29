<template>
  <div class=" ">
    <div class="  is-vertical">
      <div class="message is-info ">
        <div class="message-header">
          <div>
            <icon class="icon-large" icon-class="icon-home-white"/>
            关于我
          </div>
        </div>
        <div class="message-body">
        </div>
      </div>
      <div class="message is-info ">
        <div class="message-header">
          <div>
            <icon class="icon-large" icon-class="icon-tag"/>
            文章标签
          </div>
        </div>
        <div class="message-body">
        </div>
      </div>
      <div class="message is-info ">
        <div class="message-header">
          <div>
            <icon class="icon-large" icon-class="icon-comment"/>
            近期评论
          </div>
        </div>
        <div class="message-body">
        </div>
      </div>
      <div class="message is-info">
        <div class="message-header">
          <div>
            <icon class="icon-large" icon-class="icon-info"/>
            网站信息
          </div>
        </div>
        <div class="message-body">
          <article>
            <icon icon-class="icon-formfill"/>文章总数：2 篇<br>
            <icon icon-class="icon-tagfill"/>标签总数：7 个<br>
            <icon icon-class="icon-comment"/>分类总数：3 个<br>
            <icon icon-class="icon-commentfill"/>留言数量：1 条<br>
            <icon icon-class="icon-friendfill"/>在线人数：1人<br>
            <icon icon-class="icon-timefill"/>运行天数：113天<br>
            <icon icon-class="icon-writefill"/>最后更新：2018年01月19日22点<br>
          </article>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeSide'
}
</script>

<style scoped lang="stylus">
  .svg-icon
    font-size 1.125rem
    margin-right .25rem
  .icon-large
    font-size 1.25rem
</style>
