<template>
  <div style="width: 100%">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">情感分析子系统</el-breadcrumb-item>
      <el-breadcrumb-item>评论列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="请输入景区" suffix-icon="el-icon-search" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.username" placeholder="请输入姓名" suffix-icon="el-icon-search" clearable></el-input>
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
        prop="userId"
        header-align="center"
        align="center"
        width="70"
        label="用户ID">
      </el-table-column>
      <el-table-column
        prop="scenicId"
        header-align="center"
        align="center"
        width="80"
        label="景区ID">
      </el-table-column>
      <el-table-column
        prop="observerName"
        header-align="center"
        align="center"
        label="评论者名字">
      </el-table-column>
      <el-table-column
        prop="commentTime"
        header-align="center"
        align="center"
        label="评论时间">
      </el-table-column>
      <el-table-column
        prop="scenicArea"
        header-align="center"
        align="center"
        label="景区名字">
      </el-table-column>
      <el-table-column
        prop="textContent"
        header-align="center"
        align="center"
        width="400"
        label="评论内容">
      </el-table-column>
      <el-table-column
          prop="sentiment"
          header-align="center"
          align="center"
          width="75"
          label="情感极性">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="190"
        label="操作">
        <template slot-scope="scope">
          <el-button type="success" size="small" @click="addOrUpdateHandle(scope.row.userId)">查看 <i class="el-icon-info"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？不可逆"
              @confirm="deleteHandle(scope.row.userId)"
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
  import AddOrUpdate from './comment-add-or-update'
  import request from "@/utils/request";
  export default {
    name: 'comment',
    data () {
      return {
        dataForm: {
          key: '',
          username: ''
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
        request.get("/comment/page",{
          params:{
            pageNum: this.pageIndex,
            pageSize: this.pageSize,
            username: this.dataForm.username,
            scenicArea: this.dataForm.key
          }
        }).then(res => {
          this.dataList = res.records
          this.totalPage = res.total
        })
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
        let ids = this.dataListSelections.map(v => v.userId)
        request.delete("/comment/deleteBatch/",{data: ids}).then(res => {
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
        this.dataForm.username = ''
        this.getDataList()
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        request.delete("/comment/deleteById/"+id, id).then(res => {
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
