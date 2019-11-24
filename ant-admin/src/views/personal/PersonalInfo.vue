<template>
  <a-card :bordered="false">
    <div class="account-settings-info-view">
      <a-spin :spinning="confirmLoading">
        <a-form :form="form" layout="vertical">
          <a-row :gutter="16">
            <a-col :md="24" :lg="16">

              <a-form-item
                label="昵称"
              >
                <a-input v-decorator="['name', {rules: [{required: true}]}]" placeholder="给自己起个名字" />
              </a-form-item>

              <a-form-item
                label="微信"
              >
                <a-input v-decorator="['weixin', {rules: [{required: true}]}]" placeholder="微信"/>
              </a-form-item>
              <a-form-item
                label="QQ"
              >
                <a-input v-decorator="['qq', {rules: [{required: true}]}]" placeholder="QQ"/>
              </a-form-item>
              <a-form-item
                label="github"
              >
                <a-input v-decorator="['github', {rules: [{required: true}]}]" placeholder="github"/>
              </a-form-item>

              <a-form-item
                label="电子邮件"
              >
                <a-input v-decorator="['email', {rules: [{required: true}]}]" placeholder="exp@admin.com"/>
              </a-form-item>
              <a-form-item
                label="自我介绍"
              >
                <a-textarea v-decorator="['introduce', {rules: [{required: true}]}]" rows="4" placeholder="You are not alone."/>
              </a-form-item>

              <a-form-item>
                <a-button type="primary" @click="handleSubmit">提交</a-button>
              </a-form-item>

            </a-col>
            <a-col :md="24" :lg="8" :style="{ minHeight: '180px' }">
              <a-form-item>
                <div class="ant-upload-preview" @click="$refs.modal.edit(1)" >
                  <a-icon type="cloud-upload-o" class="upload-icon"/>
                  <div class="mask">
                    <a-icon type="plus" />
                  </div>
                  <img :src="avatar"/>
                </div>
              </a-form-item>
            </a-col>

          </a-row>
        </a-form>
      </a-spin>
      <avatar-modal ref="modal" @ok="setavatar"/>

    </div>
  </a-card></template>

<script>
import AvatarModal from './AvatarModal'
export default {
  components: {
    AvatarModal
  },
  data () {
    return {
      confirmLoading: false,
      form: this.$form.createForm(this),
      avatar: '/avatar2.jpg'
    }
  },
  methods: {
    setavatar (url) {
      this.avatar = url
    },
    handleSubmit () {
      const { form: { validateFields } } = this
      this.confirmLoading = true
      validateFields((errors, values) => {
        if (!errors) {
          values.avatar = this.avatar
          this.api.savePersonInfo(values).then(res => {
            console.log(res)
            if (res && res.code === 200) {
              this.confirmLoading = false
              this.$message.info(`保存成功`)
            } else {
              this.confirmLoading = false
              this.$message.error(res.message)
            }
          })
        } else {
          this.confirmLoading = false
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>

  .avatar-upload-wrapper {
    height: 200px;
    width: 100%;
  }

  .ant-upload-preview {
    position: relative;
    margin: 0 auto;
    width: 100%;
    max-width: 180px;
    border-radius: 50%;
    box-shadow: 0 0 4px #ccc;

    .upload-icon {
      position: absolute;
      top: 0;
      right: 10px;
      font-size: 1.4rem;
      padding: 0.5rem;
      background: rgba(222, 221, 221, 0.7);
      border-radius: 50%;
      border: 1px solid rgba(0, 0, 0, 0.2);
    }
    .mask {
      opacity: 0;
      position: absolute;
      background: rgba(0,0,0,0.4);
      cursor: pointer;
      transition: opacity 0.4s;

      &:hover {
        opacity: 1;
      }

      i {
        font-size: 2rem;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -1rem;
        margin-top: -1rem;
        color: #d6d6d6;
      }
    }

    img, .mask {
      width: 100%;
      max-width: 180px;
      height: 100%;
      border-radius: 50%;
      overflow: hidden;
    }
  }
</style>
