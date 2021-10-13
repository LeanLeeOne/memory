import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App.vue';
import Wechat from "@/components/Wechat";
import Blog from "@/components/Blog";
import Index from "@/components/Index";

Vue.config.productionTip = false;

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {path: "/", component: Index},
    {path: "/blog/", component: Blog},
    {path: "/wechat/", component: Wechat}
  ]
});

new Vue({
    render: h => h(App),
    router
}).$mount('#app');
