<template>
  <el-tabs v-model="activeName" type="border-card" style="position:absolute; height: 100%;width: 100%" @tab-click="handleTabClick">
    <el-tab-pane
      v-for="(value,key) in configTypeMap"
      :key="key"
      :label="value"
      :name="key">
      <el-table v-loading="listLoading" :data="configList" stripe border fit highlight-current-row>
        <el-table-column width="200px" label="名称">
          <template slot-scope="scope">
            <span>{{ scope.row.configName }}</span>
          </template>
        </el-table-column>
        <el-table-column width="300px" label="key">
          <template slot-scope="scope">
            <span>{{ scope.row.configKey }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="300px" label="value">
          <template slot-scope="scope">
            <template v-if="scope.row.edit">
              <el-input v-model="scope.row.configValue" class="edit-input" size="small" />
              <el-button class="cancel-btn" size="small" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">cancel</el-button>
            </template>
            <span v-else>{{ scope.row.configValue }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="120">
          <template slot-scope="scope">
            <el-button v-if="scope.row.edit" type="success" size="small" icon="el-icon-circle-check-outline" @click="confirmEdit(scope.row)">Ok</el-button>
            <el-button v-else type="primary" size="small" icon="el-icon-edit" @click="scope.row.edit=!scope.row.edit">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: 'Config',
  data() {
    return {
      activeName: '1',
      listLoading: true,
      configTypeMap: {},
      reqVo: {
        configType: 1
      },
      configList: []
    }
  },
  created() {
    this.getConfigTypeMap()
    this.getConfigListByType()
  },
  methods: {
    handleTabClick(tab, event) {
      this.reqVo.configType = tab.name
      this.getConfigListByType()
    },
    getConfigTypeMap() {
      this.api.getConfigTypeMap().then(data => {
        this.configTypeMap = data
      })
    },
    getConfigListByType() {
      this.listLoading = true
      this.api.getConfigListByType(this.reqVo).then(data => {
        this.configList = data.map(v => {
          this.$set(v, 'edit', false)
          v.originalConfigValue = v.configValue
          return v
        })
        this.listLoading = false
      })
    },
    cancelEdit(row) {
      row.configValue = row.originalConfigValue
      row.edit = false
    },
    confirmEdit(row) {
      this.listLoading = true
      this.api.saveConfig(row).then(data => {
        row.edit = false
        row.originalConfigValue = row.configValue
        this.$message({
          message: '保存配置成功',
          type: 'success',
          duration: 1000
        })
        this.listLoading = false
      }, () => {
        this.listLoading = false
      })
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
