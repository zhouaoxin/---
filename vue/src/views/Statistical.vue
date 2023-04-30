<template>
  <div style="width: 100%">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/statistical' }">情感分析子系统</el-breadcrumb-item>
      <el-breadcrumb-item>统计信息</el-breadcrumb-item>
    </el-breadcrumb>

    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="请输入地区" suffix-icon="el-icon-search" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getDataList()">查询 <i class="el-icon-check"></i></el-button>
        <el-button type="info" @click="reset()">重置 <i class="el-icon-error"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除这些数据吗？不可逆"
            @confirm="deleteBatch()"
        >
          <el-button type="danger" slot="reference" :disabled="dataListSelections.length <= 0">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>
    <el-table
        :data="dataList"
        border
        stripe
        header-cell-class-name="headerClass"
        v-loading="dataListLoading"
        @selection-change="selectionChangeHandle"
        style="width: 100%;">
      <el-table-column
          type="selection"
          header-align="center"
          align="center"
          width="50">
      </el-table-column>
      <el-table-column
          prop="id"
          header-align="center"
          align="center"
          width="70"
          label="ID">
      </el-table-column>
      <el-table-column
          prop="scenicName"
          header-align="center"
          align="center"
          width="80"
          label="景区名字">
      </el-table-column>
      <el-table-column
          prop="areaName"
          header-align="center"
          align="center"
          width="100"
          label="景区位置">
      </el-table-column>
      <el-table-column
          prop="commentNums"
          header-align="center"
          align="center"
          width="100"
          label="评论总数">
      </el-table-column>
      <el-table-column
          prop="commentAvgScore"
          header-align="center"
          align="center"
          width="100"
          label="评论均分">
      </el-table-column>
      <el-table-column
          prop="sentimentType"
          header-align="center"
          align="center"
          label="评论标签">
      </el-table-column>
      <el-table-column
          prop="manNums"
          header-align="center"
          align="center"
          width="100"
          label="男性数量">
      </el-table-column>
      <el-table-column
          prop="womenNums"
          header-align="center"
          align="center"
          width="100"
          label="女性数量">
      </el-table-column>
      <el-table-column
          prop="manAgeAvg"
          header-align="center"
          align="center"
          width="100"
          label="男性平均年龄">
      </el-table-column>
      <el-table-column
          prop="womenAgeAvg"
          header-align="center"
          align="center"
          width="100"
          label="女性平均年龄">
      </el-table-column>
      <el-table-column
          prop="vehicle"
          header-align="center"
          align="center"
          width="100"
          label="交通工具">
      </el-table-column>
      <el-table-column
          fixed="right"
          header-align="center"
          align="center"
          width="190"
          label="操作">
        <template slot-scope="scope">
          <el-button type="success" size="small" @click="addOrUpdateHandle(scope.row.id)">查看 <i class="el-icon-info"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？不可逆"
              @confirm="deleteHandle(scope.row.id)"
          >
            <el-button slot="reference" type="danger" size="small">删除 <i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
        :current-page="pageIndex"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './statistical-add-or-update'
import request from "@/utils/request";
export default {
  name: 'scenic',
  data () {
    return {
      dataForm: {
        key: '',
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    // getDataList(){
    //   request.get("/comment/list").then(res => {
    //     console.log(res)
    //     this.dataList = res.comments
    //     this.totalPage = res.total
    //   })
    // },
    getDataList(){
      // console.log("名字" + this.scenicArea)
      request.get("/statistical/page",{
        params:{
          pageNum: this.pageIndex,
          pageSize: this.pageSize,
          areaName: this.dataForm.key
        }
      }).then(res => {
        this.dataList = res.records
        this.totalPage = res.total
      })
      console.log(this.dataForm.key)
    },
    // 每页数
    sizeChangeHandle (pageSize) {
      this.pageSize = pageSize
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (pageIndex) {
      this.pageIndex = pageIndex
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 批量删除
    deleteBatch(){
      let ids = this.dataListSelections.map(v => v.id)
      request.delete("/statistical/deleteBatch/",{data: ids}).then(res => {
        if (res){
          this.$message.success("批量删除成功!")
          this.getDataList()
        }else {
          this.$message.error("删除失败!")
        }
        this.getDataList()
      })
    },
    // 重置
    reset() {
      this.dataForm.key = ''
      this.getDataList()
    },
    // 查看
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id) {
      request.delete("/statistical/deleteById/"+id, id).then(res => {
        if (res){
          this.$message.success("删除成功")
          this.getDataList()
        }else {
          this.$message.error("删除失败")
        }
        this.getDataList()
      })
    }
  }
}
</script>
