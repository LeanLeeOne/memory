import Vue from 'vue';
import VueRouter from 'vue-router';
import ViewUI from 'view-design';
import BaiduMap from 'vue-baidu-map'
import axios from 'axios'
import qs from 'qs'
import App from './App.vue';
import Index from "@/components/Index";
import Notes from "@/components/Notes";
import Map from "@/components/Map";
import Wechat from "@/components/Wechat";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

Vue.use(VueRouter);
Vue.use(ViewUI);
Vue.use(BaiduMap, {
  ak: 'M076MNMLZmNDOlsP1vsDkKTNSjn3qBgt'
});

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: Index,
      meta: {
        title: "李晓辉的个人网站"
      }
    },
    {
      path: "/notes/",
      component: Notes,
      meta: {
        title: "笔记"
      }
    },
    {
      path: "/map/",
      component: Map,
      meta: {
        title: "地图"
      }
    },
    {
      path: "/wechat/",
      component: Wechat,
      meta: {
        title: "微信"
      }
    }
  ]
});

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.path === "/") {
    Time_Waits_For_No_Oen();
  } else if (to.meta.title) {
    document.title = "檬胧 | " + to.meta.title;
  }

  // 日出
  let style = document.getElementById("app").style;
  if (to.path === "/notes") {
    style.backgroundColor = "#666";
  } else {
    style.backgroundColor = "black";
  }

  next()
});


axios.defaults.baseURL = '/api';
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 参数格式转换
  if (config.method === "post") {
    config.data = qs.stringify(config.data);
  }
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

new Vue({
  render: h => h(App),
  router
}).$mount('#app');

function Time_Waits_For_No_Oen() {
  let words = "檬胧 | Time waits for no one";
  let i = 3;
  let id = setInterval(() => {
    document.title = words.slice(0, ++i);
    if (i >= words.length) {
      clearInterval(id);
    }
  }, 50);
}