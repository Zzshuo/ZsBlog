<template>
  <div class="app-container">

    <el-table v-loading="listLoading" :data="articleList" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="300px" label="标题">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="类型">
        <template slot-scope="scope">
          <span v-if="scope.row.original">原创</span>
          <span v-else>转载</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="分类">
        <template slot-scope="scope">
          <span>{{ scope.row.typeId }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="标签">
        <template slot-scope="scope">
          <el-tag v-for="tagId in scope.row.tagIdList" :key="tagId">{{ FormatUtil.formatTag(tagId) }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state | stateFilter">{{ FormatUtil.formatState(scope.row.state) }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="创建日期">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="修改日期">
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="其他">
        <template slot-scope="scope">
          <span><svg-icon icon-class="eye-open" /> {{ scope.row.views }} &nbsp;&nbsp;</span>
          <span><svg-icon icon-class="message" /> {{ scope.row.comments }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions" width="120">
        <template slot-scope="scope">
          <router-link :to="'/article/edit/'+scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">Edit</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination :total="total" :page.sync="reqVo.pageNum" :limit.sync="reqVo.pageSize" @pagination="getArticleList" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import { mapGetters } from 'vuex'
export default {

  name: 'ArticleList',
  components: { Pagination },
  filters: {
    stateFilter(state) {
      const stateFilter = {
        1: 'success',
        2: 'info',
        3: 'danger'
      }
      return stateFilter[state]
    }
  },
  data() {
    return {
      s111: [],
      articleList: null,
      total: 0,
      listLoading: true,
      reqVo: {
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  computed: {
    ...mapGetters([
      'stateMap'
    ])
  },
  created() {
    this.getArticleList()
  },
  methods: {
    getArticleList() {
      this.listLoading = true
      this.api.getArticleList(this.reqVo).then(response => {
        const res = response.data
        if (res && res.code === 200) {
          const data = res.data
          this.articleList = data.list
          this.total = data.total
        } else {
          console.log(res.code + res.message)
        }
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      this.reqVo.pageSize = val
      this.getArticleList()
    },
    handleCurrentChange(val) {
      this.reqVo.pageNum = val
      this.getArticleList()
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
