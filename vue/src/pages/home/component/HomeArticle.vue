<template>
  <div class="columns is-mobile zs-relative">
    <div class="column is-one-quarter zs-column-left">
      <figure class="image is-square">
        <img src="https://lqzhgood.github.io/bulma-docs-cn/images/placeholders/128x128.png">
      </figure>
    </div>
    <div class="column">
      <div>
        <p class="title is-4">Verti啊都市风口浪尖街坊邻居卡萨冷风机阿里双方均暗示阿发啊了附近</p>
      </div>
      <div>
        <p class="subtitle is-6 is-hidden-mobile">Top tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop tileTop</p>
      </div>
      <div class="zs-info">
        <span class="zs-span">
          <i class="fa fa-calendar-o fa-fw"></i>2018-11-28
        </span>
        <span class="zs-span" title="浏览次数">
          <i class="fa fa-eye fa-fw"></i>1
        </span>
        <span class="zs-span">
          <i class="fa fa-comment-o fa-fw"></i>0
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeArticle'
}
</script>

<style scoped lang="stylus">
  .zs-column-left
    max-width 152px
  .zs-span
    font-size .9rem
    text-align center
    line-height 1rem
    color #999

  .zs-info
    position absolute
    bottom 1rem
  .zs-relative
    position relative
  .title
    -webkit-line-clamp: 1 //在一个块元素显示的文本的行数
  .subtitle
    -webkit-line-clamp: 3 //在一个块元素显示的文本的行数
  .title, .subtitle
    overflow: hidden //溢出的部分隐藏；
    text-overflow: ellipsis //文本溢出显示省略号；
    display: -webkit-box  //必须结合的属性 ，将对象作为弹性伸缩盒子模型显示
    -webkit-box-orient: vertical //必须结合的属性 ，设置或检索伸缩盒对象的子元素的排列方式 。
</style>
