<template>
  <div>
    <Card :bordered="false" class="zs-box">
      <div style="text-align:center">
        <img src="../../../../assets/img/logo.png">
        <h3>A high quality UI Toolkit based on Vue.js</h3>
      </div>
    </Card>
    <Card :bordered="false" class="zs-box">
      <div>
        <p>
          <Icon type="md-home" />
          <Divider type="vertical" />
          关于我
        </p>
        <Divider />
        <p>Content of no border type. Content of no border type. Content of no border type. Content of no border type. </p>
      </div>
    </Card>
    <Card class="zs-box">
      <div>
        <p>
          <Icon type="md-pricetags" />
          <Divider type="vertical" />
          文章标签
        </p>
        <Divider />
        <div v-for="tag of tagList" :key="tag.id" style="display: inline-flex">
          <Tag :color="colorList[Math.floor(Math.random() * (colorList.length-1))]">
            {{tag.name}}
            <span>
              {{tag.count}}
            </span>
          </Tag>
        </div>
      </div>
    </Card>
    <Card class="zs-box">
      <div>
        <p>
          <Icon type="md-alert" />
          <Divider type="vertical" />
          网站信息
        </p>
        <Divider />
        <p>
          <Icon type="md-document" />文章总数：{{webInfo.articleCount}} 篇<br>
          <Icon type="md-pricetag" />标签总数：{{webInfo.tagCount}} 个<br>
          <Icon type="ios-folder-open" />分类总数：{{webInfo.typeCount}} 个<br>
          <Icon type="ios-text" />留言数量：{{webInfo.msgCount}} 条<br>
          <Icon type="md-person" />在线人数：{{webInfo.online}} 人<br>
          <Icon type="ios-time" />运行天数：{{webInfo.runDay}} 天<br>
        </p>
      </div>
    </Card>
  </div>
</template>

<script>
export default {
  name: 'HomeSide',
  data () {
    return {
      tagList: [],
      webInfo: {},
      reqVo: {},
      colorList: ['red', 'volcano', 'orange', 'gold', 'yellow', 'lime', 'green', 'cyan', 'blue', 'geekblue', 'purple']
    }
  },
  methods: {
    getTagList () {
      this.api.getTagList(this.reqVo).then((res) => {
        if (res && res.code === 200) {
          const data = res.data
          this.tagList = data.list
        } else {
          console.log(res.code + res.message)
        }
      })
    },
    getWebInfo () {
      this.api.getWebInfo(this.reqVo).then((res) => {
        if (res && res.code === 200) {
          const data = res.data
          this.webInfo = data
        } else {
          console.log(res.code + res.message)
        }
      })
    }
  },
  mounted () {
    this.getTagList()
    this.getWebInfo()
  }
}
</script>

<style scoped lang="stylus">
  .zs-box
    margin-bottom 1.5rem
</style>
