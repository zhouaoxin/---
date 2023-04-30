<template>
  <el-dialog
    :title="!dataForm.userId ? '新增' : '查看'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户ID" prop="scenicId">
      <el-input v-model="dataForm.scenicId" placeholder="" disabled></el-input>
    </el-form-item>
      <el-form-item label="景区ID" prop="scenicId">
        <el-input v-model="dataForm.scenicId" placeholder="" disabled></el-input>
      </el-form-item>
    <el-form-item label="姓名" prop="observerName">
      <el-input v-model="dataForm.observerName" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="评论时间" prop="commentTime">
      <el-input v-model="dataForm.commentTime" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="景区名字" prop="scenicArea">
      <el-input v-model="dataForm.scenicArea" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="评论内容" prop="textContent">
      <el-input v-model="dataForm.textContent" placeholder="" disabled></el-input>
    </el-form-item>
      <el-form-item label="情感极性" prop="sentiment">
        <el-input v-model="dataForm.sentiment" placeholder="" disabled></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="visible = false">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import request from "@/utils/request";

  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userId: '',
          scenicId: '',
          observerName: '',
          commentTime: '',
          scenicArea: '',
          textContent: '',
          sentiment: ''
        },
        dataRule: {
          // observerName: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // commentTime: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // scenicArea: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // textContent: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            request.get('/comment/selectById/'+id, id).then(res => {
              this.dataForm.userId = res.userId
              this.dataForm.scenicId = res.scenicId
              this.dataForm.observerName = res.observerName
              this.dataForm.commentTime = res.commentTime
              this.dataForm.scenicArea = res.scenicArea
              this.dataForm.textContent = res.textContent
              this.dataForm.sentiment = res.sentiment
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/comment/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'scenicId': this.dataForm.scenicId,
                'observerName': this.dataForm.observerName,
                'commentTime': this.dataForm.commentTime,
                'scenicArea': this.dataForm.scenicArea,
                'textContent': this.dataForm.textContent
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
