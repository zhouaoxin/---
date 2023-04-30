<template>
  <div>
    <div v-loading="loadings" ref="chart" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import request from "@/utils/request";

export default {
  name: "topWords",
  data(){
    return{
      topWords:[],
      loadings: true
    }
  },
  methods: {
    async getDataList (){
      await request.get("/comment/getData").then(res => {
        let obj = res.result
        this.topWords = obj.map(item => {
          let key = Object.keys(item)[0]
          return {name: key, value: item[key]}
        })
      })
      console.log(this.topWords)
      this.drawChart(this.topWords)
      this.loadings = false
    },
    drawChart(data) {
      const chart = echarts.init(this.$refs.chart);
      const option = {
        series: [
          {
            type: 'wordCloud',
            shape: 'circle',
            left: 'center',
            top: 'center',
            width: '100%',
            height: '100%',
            right: null,
            bottom: null,
            sizeRange: [12, 60], // 字体大小范围，根据需求调整
            rotationRange: [-90, 90], // 字体旋转角度范围，根据需求调整
            rotationStep: 45,
            gridSize: 8, // 网格大小，根据需求调整
            drawOutOfBound: false,
            textStyle: {
              normal: {
                fontFamily: 'sans-serif',
                fontWeight: 'bold',
                color: () => {
                  return `rgb(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255})`;
                },
              },
              emphasis: {
                shadowBlur: 10,
                shadowColor: '#333',
              },
            },
            data: data.map((item) => {
              return {
                name: item.name,
                value: Math.round(item.value * 1000), // 将比重转换为数值
              };
            }),
          },
        ],
      };
      chart.setOption(option);
    },
  },
  mounted() {
    this.getDataList()
  },

}
</script>

<style scoped>

</style>