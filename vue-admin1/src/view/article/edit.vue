<template>
  <div>
    <Form :model="article">
      <FormItem>
        <Input v-model="article.title" size="large" placeholder="请输入标题..." autofocus="false"/>
      </FormItem>
      <FormItem>
        <mavon-editor v-model="article.content"/>
      </FormItem>
      <Modal v-model="modalVisible"
             title="发布文章"
             @on-ok="ok"
             @on-cancel="cancel"
             :mask-closable="false">
        <FormItem>
          <Tag v-for="item in tags" :key="item" :name="item" closable @on-close="handleClose2">标签{{ item + 1 }}</Tag>
          <Button icon="ios-add" type="dashed" size="small" @click="handleAdd">添加标签</Button>
        </FormItem>
        <FormItem>
          <RadioGroup v-model="article.type" type="button">
            <Radio label="前端"></Radio>
            <Radio label="后端"></Radio>
            <Radio label="其他"></Radio>
          </RadioGroup>
        </FormItem>
        <FormItem>
          <Input v-model="article.title" size="large" placeholder="请输入标题..." autofocus="false"/>
        </FormItem>
      </Modal>
      <FormItem>
        <Button type="primary" @click="modalVisible = true">发布文章</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
export default {
  name: 'article-edit',
  data () {
    return {
      article: {},
      tags:["标签1","标签2","标签3"],
      modalVisible: false
    }
  },
  methods: {
    ok () {
      this.$Message.info('Clicked ok')
    },
    cancel () {
      this.$Message.info('Clicked cancel')
    }
  }
}
</script>

<style scoped lang="stylus">
.markdown-body
  z-index inherit
</style>
