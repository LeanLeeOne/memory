<template>
  <div class="main">
    <img alt="Vue logo" src="../assets/moon.png">
    <h1 hidden>{{ msg }}</h1>
    <input placeholder="搜索框" hidden>
    <ul>
      <li>
        <a href="http://leanlee.top/notes/index"
           class="blog"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-ningmeng"></i>
          <span>Notes</span>
        </a>
      </li>
      <li>
        <a @click="jump2('/notes')"
           class="blog">
          <i class="iconfont icon-ningmeng"></i>
          <span>Notes</span>
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
export default {
  name: 'Index',
  props: {
    msg: String
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
@import "https://at.alicdn.com/t/font_2864663_7j50opb9ofa.css";

.main {
  width: 100%;
  height: 100%;
  position: relative;
}
h3 {
  margin: 40px 0 0;
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
  -webkit-transition: color .25s ease-out .1s;
  -moz-transition: color .25s ease-out .1s;
  -o-transition: color .25s ease-out .1s;
  -ms-transition: color .25s ease-out .1s;
  transition: color .25s ease-out .1s;
  text-shadow: 2px 2px 4px #00000070;
}
a.blog:hover {
  color: #f9f967;
}
a.cnblogs:hover {
  color: #1296db;
}
a.github:hover {
  color: #3e75c3;
}
a.bilibili:hover {
  color: #1296db;
}
a.wechat:hover {
  color: #09bb07;
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
