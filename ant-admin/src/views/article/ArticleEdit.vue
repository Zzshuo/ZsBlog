<template>
  <a-card :bordered="false">
    <a-spin :spinning="confirmLoading">
      <a-form layout="inline">
        <a-row>
          <a-col :lg="20" :md="18" :sm="24">
            <a-input v-model="postForm.title" placeholder="输入标题..."></a-input>
          </a-col>
          <a-col :lg="4" :md="6" :sm="24">
            <span :style="{ float: 'right', overflow: 'hidden' } ">
              <a-button type="primary" @click="handleModal">发布</a-button>
            </span>
          </a-col>
        </a-row>
        <a-row>
          <mavon-editor
            ref="md"
            v-model="postForm.content"
            :box-shadow="false"
            class="mavonEditor"
            @imgAdd="addArticleImage"
          />
        </a-row>
        <modal-form ref="modalForm" @handleSubmit="handleSubmit" />
      </a-form>
    </a-spin>
  </a-card>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import ModalForm from './modules/ModalForm'
import { checkImage, getPolicyAndUpload } from '@/utils/aliOss'
export default {
  components: {
    mavonEditor,
    ModalForm
  },
  data () {
    return {
      postForm: {
        title: '',
        content: ''
      },
      confirmLoading: false
    }
  },
  created () {
    console.log(this.$route.params)
    const id = this.$route.params && this.$route.params.id
    if (id !== undefined) {
      this.getArticleById(id)
    }
  },
  methods: {
    getArticleById (id) {
      this.confirmLoading = true
      this.api.getArticleById(id).then(res => {
        if (res && res.code === 200) {
          this.confirmLoading = false
          console.log(res.data.data)
          this.postForm = res.data.data
        } else {
          this.confirmLoading = false
          this.$message.error(res.message)
        }
      })
    },
    handleModal () {
      this.$refs.modalForm.edit(this.postForm)
    },
    handleSubmit (values) {
      if (this.isEmpty(this.postForm.title)) {
        this.$message.error('请输入标题')
        return
      }
      const formData = {}
      Object.assign(formData, values)
      formData.id = this.postForm.id
      formData.title = this.postForm.title
      formData.content = this.postForm.content
      console.log(formData)
      this.confirmLoading = true
      this.api.saveArticle(formData).then(res => {
        if (res && res.code === 200) {
          this.confirmLoading = false
          this.$message.info('文章保存成功')
          this.$router.push('/article/articleList')
        } else {
          this.confirmLoading = false
          this.$message.error(res.message)
        }
      })
    },
    addArticleImage (pos, $file) {
      if (checkImage($file)) {
        getPolicyAndUpload($file, 'article').then(response => {
          const $vm = this.$refs.md
          $vm.$img2Url(pos, response)
        }).catch((err) => {
          console.log('err == ', err)
          this.$message.error(err)
        })
      }

      // // 第一步.将图片上传到服务器.
      // const formdata = new FormData()
      // formdata.append('file', $file)
      // console.log($file)
      // this.api.addArticleImage(formdata).then(data => {
      //   // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
      //   /**
      //    * $vm 指为mavonEditor实例，可以通过如下两种方式获取
      //    * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
      //    * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
      //    */
      //   const $vm = this.$refs.md
      //   $vm.$img2Url(pos, data)
      // })
    },
    // 判断字符是否为空的方法
    isEmpty (obj) {
      if (typeof obj === 'undefined' || obj == null || obj === '') {
        return true
      } else {
        return false
      }
    }
  }
}
</script>

<style  lang="less" scoped>
  /deep/ .ant-input {
    font-size: 28px ;
    height: 80px ;
    font-weight: 500 ;
    border: none ;
    box-shadow: none ;
    outline: none;
    padding: 0px 20px;
  }
  /deep/ .ant-input:focus {
    border: none;
    box-shadow: none;
    font-size: 30px ;
}
  /deep/ .ant-card-body {
    padding: 0px 0px;
  }
  /deep/ .ant-btn {
    margin: 20px 10px;
  }
  .mavonEditor {
    height: 600px;
    width: 100%;
    z-index: 0;
  }
  .v-note-wrapper {
    border-radius: 0px
  }
  /deep/ .ant-switch {
    background-color: #ff8d18
  }
  /deep/ .ant-switch-checked {
    background-color: #1890ff
  }
</style>
