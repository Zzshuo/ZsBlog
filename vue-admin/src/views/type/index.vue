<template>
  <div class="app-container">
    <div class="app-banner">
      <el-input v-model="newTypeName" placeholder="请输入内容" style="width: 300px"/>
      <el-button slot="append" type="primary" @click="addType()">添加分类</el-button>
    </div>

    <el-table v-loading="listLoading" :data="tagList" stripe border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="序号" type="index" width="50"/>
      <el-table-column label="分类名称" min-width="100">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input v-model="scope.row.name" class="edit-input" size="small" />
            <el-button class="cancel-btn" size="small" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">cancel</el-button>
          </template>
          <span v-else>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="文章数" width="70" prop="count" />
      <el-table-column align="center" label="操作" width="150">
        <template slot-scope="scope">
          <el-button v-if="scope.row.edit" type="success" size="small" @click="confirmEdit(scope.row)">保存</el-button>
          <el-button v-else type="primary" size="small" @click="scope.row.edit=!scope.row.edit">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteType(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Type',
  data() {
    return {
      listLoading: false,
      tagList: [],
      total: 0,
      reqVo: {},
      newTypeName: ''
    }
  },
  created() {
    this.getAllType()
  },
  methods: {
    getAllType() {
      this.listLoading = true
      this.api.getAllType().then(data => {
        this.tagList = data.map(v => {
          this.$set(v, 'edit', false)
          v.originalValue = v.name
          return v
        })
        this.listLoading = false
      }, () => {
        this.listLoading = false
      })
    },
    deleteType(tagId) {
      this.reqVo.id = tagId
      this.api.deleteType(this.reqVo).then(data => {
        this.tagList.splice(this.tagList.map(tag => tag.id).indexOf(tagId), 1)
      })
    },
    cancelEdit(row) {
      row.name = row.originalValue
      row.edit = false
    },
    confirmEdit(row) {
      if (row.name === '') {
        this.$message({ message: '请输入内容', type: 'error', duration: 1000 })
      }
      this.listLoading = true
      this.api.saveType(row).then(data => {
        row.edit = false
        row.originalValue = row.name
        this.$message({ message: '保存分类成功', type: 'success', duration: 1000 })
        this.listLoading = false
      }, () => {
        this.listLoading = false
      })
    },
    addType() {
      if (this.newTypeName === '') {
        this.$message({ message: '请输入内容', type: 'error', duration: 1000 })
      } else {
        this.reqVo = {}
        this.reqVo.name = this.newTypeName

        this.listLoading = true
        this.api.saveType(this.reqVo).then(data => {
          this.$message({ message: '添加分类成功', type: 'success', duration: 1000 })
          this.getAllType()
          this.listLoading = false
        }, () => {
          this.listLoading = false
        })
      }
    }
  }
}
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }
  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
