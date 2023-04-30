<template>
  <div class="home" style="position: absolute">
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 400px"></div>
        <h3 style="margin-right: 30px; margin-top: 20px">不同旅游工具分布图</h3>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
        <h3 style="margin-right: 200px; margin-top: 20px">不同年龄段旅游倾向分布图</h3>
      </el-col>
    </el-row>

    <hr />

    <el-row>
      <el-col :span="12">
        <test />
        <h3 style="margin-right: 80px; margin-top: 20px">景区热力词云图</h3>
      </el-col>

      <el-col :span="12">
        <topWords />
        <h3 style="margin-right: 50px; margin-top: 20px">评论Top10关键字分布图</h3>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import * as echarts from 'echarts';
import test from "@/views/WordCloud";
import topWords from "@/views/TopWords";


export default {
  name: 'SentimentAnalyse',
  components: {
    test,
    topWords
  },
  data() {
    return {
      input: '',
      activeIndex: '1',
      activeIndex2: '1',
    }
  },
  methods: {

  },
  created() {

  },
  mounted(){
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '55%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: [
            { value: 634, name: '飞机' },
            { value: 305, name: '轮船' },
            { value: 443, name: '高铁' },
            { value: 267, name: '自驾游' },
            { value: 300, name: '旅行团' }
          ]
        }
      ]
    };
    option && myChart.setOption(option);

    var chartDom1 = document.getElementById('pie');
    var myChart1 = echarts.init(chartDom1);
    var option1;

    option1 = {
      xAxis: {
        type: 'category',
        data: ['重庆', '四川', '北京', '陕西', '湖北', '河南', '江苏', '南京']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [19, 25, 43, 55, 43, 37, 23, 61],
          type: 'bar',
        },
        {
          data: [19, 25, 43, 55, 43, 37, 23, 61],
          type: 'line',
        }
      ]
    };

    option1 && myChart1.setOption(option1);
  }
}
</script>
