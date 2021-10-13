import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App.vue';
import Wechat from "@/components/Wechat";
import Notes from "@/components/Notes";
import Index from "@/components/Index";

Vue.config.productionTip = false;

Vue.use(VueRouter);

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
        title: "博客"
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
  if (to.meta.title) {
    document.title = "檬胧 | " + to.meta.title;
  }

  // 日出
  let style = document.getElementById("app").style;
  if (to.path == "/notes") {
    style.backgroundColor = "#666";
  } else {
    style.backgroundColor = "black";
  }

  next()
});

new Vue({
  render: h => h(App),
  router
}).$mount('#app');
