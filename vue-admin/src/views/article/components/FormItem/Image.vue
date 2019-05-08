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
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    addCoverImage(param) {
      const formdata = new FormData()
      formdata.append('file', param.file)
      this.api.addCoverImage(formdata).then(response => {
        const res = response.data
        if (res && res.code === 20000) {
          this.imageUrl = res.data
        }
      })
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
