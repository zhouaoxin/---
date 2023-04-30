import Vue from 'vue'
import VueRouter from 'vue-router'
import comment from "@/views/comment";
import SentimentAnalyse from "@/views/SentimentAnalyse";
import Scenic from "@/views/Scenic";
import Statistical from "@/views/Statistical";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'comment',
    component: comment
  },
  {
    path: '/scenic',
    name: 'scenic',
    component: Scenic
  },
  {
    path: '/SentimentAnalyse',
    name: 'SentimentAnalyse',
    component: SentimentAnalyse
  },
  {
    path: '/statistical',
    name: 'statistical',
    component: Statistical
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
