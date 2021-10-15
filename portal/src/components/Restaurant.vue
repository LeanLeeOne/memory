<template>
  <div class="item">
    <div class="name"><span class="index">{{index+1}}</span>{{name}}</div>
    <div class="total">￥{{total}}，{{guests}}人，人均: ￥{{avg}}</div>
    <div class="timestamp">{{timestamp}}</div>
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
    summary: String
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
  width: 300px;
  background-color: #fff3;
  margin-bottom: 5px;
  border-top: 3px solid #09bb07;
  padding: 3px 5px;
  font-size: 14px;
  text-align: left;
  backdrop-filter: blur(10px);
}
.index {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: #3e75c3;
  color: white;
  display: inline-block;
  text-align: center;
  margin-right: 3px;
}
.name {
  font-size: 18px;
  margin-bottom: 5px;
}
.timestamp,
.address {
  color: #888;
  font-size: 14px;
}
</style>