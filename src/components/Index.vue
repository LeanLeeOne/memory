<template>
  <div class="main">
    <h1 hidden>{{ msg }}</h1>
    <input placeholder="搜索框" hidden>
    <ul title="高斯模糊、响应式布局">
      <li>
        <a href="http://blog.leanlee.top"
           rel="noopener">
          <i class="iconfont icon-ningmeng"></i>
          <span>Blog</span>
        </a>
      </li>
      <li>
        <a href="https://www.cnblogs.com/lean-lee/"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-cnblogs"></i>
          <span>CNBlogs</span>
        </a>
      </li>
      <li>
        <a href="https://github.com/LeanLeeOne/"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-github"></i>
          <span>Github</span>
        </a>
      </li>
      <li>
        <a href="https://space.bilibili.com/13913026"
           target="_blank"
           rel="noopener">
          <i class="iconfont icon-bilibili"></i>
          <span>Bilibili</span>
        </a>
      </li>
      <li>
        <a href="wechat.leanlee.top"
           target="_blank"
           rel="noopener">
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
  }
}
</script>

<style scoped>
@import "https://at.alicdn.com/t/font_2864663_7j50opb9ofa.css";
.main {

}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  width: 60px;
  height: 60px;
  margin: 0 10px;
  padding: 10px;
  display: inline-block;
}
li i{
  font-size: 35px;
  transition: all 0.3s;
}
li i:hover{
  transform: scale(1.2);
}
a {
  color: LemonChiffon;
  text-decoration:none;
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
a:hover{
  color: limegreen;
}
.beian{
  width: 100%;
  height: 1em;
  position: fixed;
  bottom: 20px;
  left: 0;
}
</style>
