<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '查看'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="ID" prop="scenicId">
      <textarea rows="2" cols="82" style="resize: none;" v-model="dataForm.scenicId" placeholder="" disabled></textarea>
    </el-form-item>
    <el-form-item label="景区名字" prop="scenicName">
      <textarea rows="2" cols="82" style="resize: none;" v-model="dataForm.scenicName" placeholder="" disabled></textarea>
    </el-form-item>
    <el-form-item label="景区位置" prop="scenicArea">
      <textarea rows="2" cols="82" style="resize: none;" v-model="dataForm.scenicArea" placeholder="" disabled></textarea>
    </el-form-item>
    <el-form-item label="景区描述" prop="scenicIntroduce">
      <textarea rows="5" cols="82" style="resize: none;" v-model="dataForm.scenicIntroduce" placeholder="" disabled></textarea>
    </el-form-item>
    <el-form-item label="景区得分" prop="scenicScore">
      <textarea rows="2" cols="82" style="resize: none;" v-model="dataForm.scenicScore" placeholder="" disabled></textarea>
    </el-form-item>
    <el-form-item label="景区标签" prop="scenicTag">
      <textarea rows="2" cols="82" style="resize: none;" v-model="dataForm.scenicTag" placeholder="" disabled></textarea>
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
          id: 0,
          scenicName: '',
          scenicArea: '',
          scenicIntroduce: '',
          scenicScore: '',
          scenicTag: ''
        },
        dataRule: {
          // scenicName: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // scenicArea: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // scenicIntroduce: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // scenicScore: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // scenicTag: [
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
            request.get('/scenic/selectById/'+id, id).then(res => {
              this.dataForm.scenicId = res.scenicId
              this.dataForm.scenicName = res.scenicName
              this.dataForm.scenicArea = res.scenicArea
              this.dataForm.scenicIntroduce = res.scenicIntroduce
              this.dataForm.scenicScore = res.scenicScore
              this.dataForm.scenicTag = res.scenicTag
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/scenic/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'scenicName': this.dataForm.scenicName,
                'scenicArea': this.dataForm.scenicArea,
                'scenicIntroduce': this.dataForm.scenicIntroduce,
                'scenicScore': this.dataForm.scenicScore,
                'scenicTag': this.dataForm.scenicTag
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
