<template>
  <div>
    <el-upload
      :show-file-list="false"
      :before-upload="beforeAvatarUpload"
      :http-request="addCoverImage"
      class="avatar-uploader"
      action="none">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"/>
    </el-upload>
  </div>
</template>

<script>
import { checkImage, getPolicyAndUpload } from '@/utils/aliOss'
export default {
  name: 'ImageItem',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  computed: {
    imageUrl: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      return checkImage(file)
    },
    addCoverImage(param) {
      getPolicyAndUpload( param.file,'cover').then(response => {
        this.imageUrl = response
      })
      .catch((err) => {
        console.log("err == ", err)
        this.$message({ message: err, type: 'error', duration: 5000 })
      })

      // const formdata = new FormData()
      // formdata.append('file', param.file)
      // this.api.addCoverImage(formdata).then(data => {
      //   this.imageUrl = data
      // })
    }
  }
}
</script>

<style scoped lang="stylus">
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
