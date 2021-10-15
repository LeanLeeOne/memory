<template>
  <div class="item">
    <div class="name">
      <span class="index">{{index+1}}</span>
      <a :href="url"
         :title="url"
      >{{name}}</a>
    </div>
    <div class="total"><span class="total">￥{{total}}</span> / {{guests}}人=￥{{avg}}</div>
    <div class="timestamp">{{ $moment(timestamp).format("YYYY-MM-DD")}}</div>
    <div class="address"><i class="iconfont icon-"></i>{{address}}</div>
    <div class="summary">{{summary}}</div>
    <div class="tag"></div>
  </div>
</template>

<script>
export default {
  name: "Restaurant",
  props: {
    index: Number,
    name: String,
    total: Number,
    guests: Number,
    timestamp: String,
    address: String,
    summary: String,
    url: String
  },
  data() {
    return {
      avg: 0
    }
  },
  mounted() {
    this.avg = (this.total / this.guests).toFixed(0);
  }
}
</script>

<style scoped>
.item {
  color: #333;
  width: 250px;
  background-color: #fff3;
  margin-top: 5px;
  border-bottom: 3px solid #09bb07;
  padding: 3px 5px;
  font-size: 14px;
  text-align: left;
  backdrop-filter: blur(10px);
}
.index {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background-color: #1296db;
  color: white;
  display: inline-block;
  text-align: center;
  margin-right: 3px;
}
.name {
  font-size: 18px;
  margin-bottom: 5px;
}
.name a {
  color: #333;;
  text-decoration: none;
}
.name a:hover {
  color: #09bb07;
  cursor: pointer;
}
span.total {
  font-size: 18px;
}
.timestamp,
.address {
  color: #888;
  font-size: 14px;
  line-height: 16px;
}
</style>