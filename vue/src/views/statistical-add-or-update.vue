<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '查看'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="ID" prop="id">
      <el-input v-model="dataForm.id" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="景区名字" prop="scenicName">
      <el-input v-model="dataForm.scenicName" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="景区位置" prop="areaName">
      <el-input v-model="dataForm.areaName" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="评论总数" prop="commentNums">
      <el-input v-model="dataForm.commentNums" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="评论均分" prop="commentAvgScore">
      <el-input v-model="dataForm.commentAvgScore" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="评论标签" prop="sentimentType">
      <el-input v-model="dataForm.sentimentType" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="男性数量" prop="manNums">
      <el-input v-model="dataForm.manNums" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="女性数量" prop="womenNums">
      <el-input v-model="dataForm.womenNums" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="男性平均年龄" prop="manAgeAvg">
      <el-input v-model="dataForm.manAgeAvg" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="女性平均年龄" prop="womenAgeAvg">
      <el-input v-model="dataForm.womenAgeAvg" placeholder="" disabled></el-input>
    </el-form-item>
    <el-form-item label="交通工具" prop="vehicle">
      <el-input v-model="dataForm.vehicle" placeholder="" disabled></el-input>
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
          areaName: '',
          commentNums: '',
          commentAvgScore: '',
          sentimentType: '',
          manNums: '',
          womenNums: '',
          manAgeAvg: '',
          womenAgeAvg: '',
          vehicle: ''
        },
        dataRule: {
          // scenicName: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // commentNums: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // commentAvgScore: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // sentimentType: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // manNums: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // womenNums: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // manAgeAvg: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // womenAgeAvg: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ],
          // vehicle: [
          //   { required: true, message: '不能为空', trigger: 'blur' }
          // ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            request.get('/statistical/selectById/'+id, id).then(res => {
              this.dataForm.id = res.id
              this.dataForm.scenicName = res.scenicName
              this.dataForm.areaName = res.areaName
              this.dataForm.commentNums = res.commentNums
              this.dataForm.commentAvgScore = res.commentAvgScore
              this.dataForm.manNums = res.manNums
              this.dataForm.womenNums = res.womenNums
              this.dataForm.manAgeAvg = res.manAgeAvg
              this.dataForm.womenAgeAvg = res.womenAgeAvg
              this.dataForm.vehicle = res.vehicle
              this.dataForm.sentimentType = res.sentimentType
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/statistical/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'scenicName': this.dataForm.scenicName,
                'commentNums': this.dataForm.commentNums,
                'commentAvgScore': this.dataForm.commentAvgScore,
                'sentimentType': this.dataForm.sentimentType,
                'manNums': this.dataForm.manNums,
                'womenNums': this.dataForm.womenNums,
                'manAgeAvg': this.dataForm.manAgeAvg,
                'womenAgeAvg': this.dataForm.womenAgeAvg,
                'vehicle': this.dataForm.vehicle
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
