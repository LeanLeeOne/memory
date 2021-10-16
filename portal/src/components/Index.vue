<template>
  <div class="main">
    <img class="moon" alt="Moon" src="../assets/moon.png">
    <MoonBack v-if="enough"></MoonBack>
    <h1 hidden>{{ msg }}</h1>
    <label for="search"></label>
    <input id="search" placeholder="搜索框" hidden>
    <ul>
      <li>
        <a href="http://leanlee.top/notes/index"
           class="notes"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-gangbi"></i>
          <span>Notes</span>
        </a>
      </li>
      <li>
        <a @click="jump2('/map')"
           class="map">
          <i class="iconfont icon-lemon1"></i>
          <span>Lemon</span>
        </a>
      </li>
      <li>
        <a href="https://www.cnblogs.com/lean-lee/"
           class="cnblogs"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-cnblogs"></i>
          <span>CNBlogs</span>
        </a>
      </li>
      <li>
        <a href="https://github.com/LeanLeeOne/"
           class="github"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-github"></i>
          <span>Github</span>
        </a>
      </li>
      <li>
        <a href="https://space.bilibili.com/13913026"
           class="bilibili"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-bilibili"></i>
          <span>Bilibili</span>
        </a>
      </li>
      <li>
        <a @click="jump2('/wechat')"
           class="wechat">
          <i class="iconfont icon-weixin"></i>
          <span>Wechat</span>
        </a>
      </li>
    </ul>
    <a class="beian" href="https://beian.miit.gov.cn/" target="_blank">鲁ICP备18052178号</a>
  </div>
</template>

<script>
import MoonBack from "@/components/MoonBack";

export default {
  name: 'Index',
  components: {
    MoonBack
  },
  props: {
    msg: String
  },
  data() {
    return {
      enough: true
    }
  },
  mounted() {
    this.enough = window.innerWidth > 800;
    let that = this;
    window.onresize = () => {
      that.enough = window.innerWidth > 800;
    }
  },
  methods: {
    jump2(url) {
      this.$router.push({
        path: url,
      })
    }
  }
}
</script>

<style scoped>
@import "https://at.alicdn.com/t/font_2864663_8ld2a3y8d18.css";

.main {
  position: relative;
}
.moon {
  width: 100%;
  max-width: 800px;
}
ul {
  width: 500px;
  list-style-type: none;
  margin: 0 calc(50% - 250px);
  padding: 0;
  position: absolute;
  bottom: 60px;
}
li {
  width: 60px;
  height: 60px;
  margin: 0 10px;
  padding: 10px;
  display: inline-block;
}
li i {
  font-size: 35px;
  transition: all 0.3s;
}
li i:hover {
  transform: scale(1.2);
}
a {
  color: LemonChiffon;
  text-decoration: none;
  height: 100%;
  font-size: 13px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  transition: color .25s ease-out .1s;
  text-shadow: 2px 2px 4px #00000070;
}
a.notes:hover span {
  color: #999;
}
a.lemon:hover span {
  color: #ead050;
}
a.cnblogs:hover span {
  color: #1296db;
}
a.github:hover span {
  color: #3e75c3;
}
a.bilibili:hover span {
  color: #1296db;
}
a.wechat:hover span {
  color: #09bb07;
}
a.notes:hover span,
a.lemon:hover span,
a.cnblogs:hover span,
a.github:hover span,
a.bilibili:hover span,
a.wechat:hover span {
  font-weight: bold;
}
.beian {
  width: 100%;
  height: 1em;
  position: absolute;
  bottom: 20px;
  left: 0;
}
.beian:hover {
  color: limegreen;
}
</style>
