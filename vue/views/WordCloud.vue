// 封装成组件
<template>
  <div>
    <div id="chart" style="width: 500px; height: 400px"></div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import "echarts-wordcloud";
import "echarts-wordcloud/dist/echarts-wordcloud.min";
export default {
  name: 'WordCloud',
  data() {
    return {
      wordList: [
        {
          name: "香格里拉",
          value: 15000,
        },
        {
          name: "九寨沟",
          value: 10081,
        },
        {
          name: "乐山大佛",
          value: 9386,
        },
        {
          name: "故宫",
          value: 7500,
        },
        {
          name: "九江河画廊",
          value: 7500,
        },
        {
          name: "桂林漓江",
          value: 6500,
        },
        {
          name: "鼓浪屿",
          value: 6500,
        },
        {
          name: "西湖风景区",
          value: 6000,
        },
        {
          name: "丽江古城",
          value: 4500,
        },
        {
          name: "长城",
          value: 3800,
        },
        {
          name: "西塘古镇",
          value: 3000,
        },
        {
          name: "秦始皇兵马俑",
          value: 2500,
        },
        {
          name: "陕西华山",
          value: 2300,
        },
        {
          name: "青海湖",
          value: 2000,
        },
        {
          name: "峨眉山",
          value: 1900,
        },
        {
          name: "黄果树瀑布",
          value: 1800,
        },
        {
          name: "珠江夜景",
          value: 1700,
        },
        {
          name: "熊猫基地",
          value: 1600,
        },
        {
          name: "青岛八大关",
          value: 1500,
        },
        {
          name: "张家界国家森林公园",
          value: 1200,
        },
      ],
    };
  },
  mounted() {
    this.initchart();
  },
  methods: {
    initchart() {
      let myChart = echarts.init(document.querySelector("#chart"));
      myChart.setOption({
        series: [
          {
            type: "wordCloud",
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 60],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 0],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              color: function () {
                return (
                    "rgb(" +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ")"
                );
              },
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: "center",
            top: "center",
            right: null,
            bottom: null,
            width: "200%",
            height: "200%",
            //数据
            data: this.wordList,
          },
        ],
      });
    },
  },
};
</script>

<!--<wordcloud />-->
