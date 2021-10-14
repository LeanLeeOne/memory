<template>
  <div class="item">
    <div class="name"><span class="index">{{index+1}}</span>{{r.name}}</div>
    <div class="total">￥{{r.total}}, {{r.guests}}人，人均：￥{{avg}}</div>
    <div class="timestamp">{{r.timestamp}}</div>
    <div class="address" todo="地址改为图标">地址：{{r.address}}</div>
    <div class="summary">{{r.summary}}</div>
    <div class="tag"></div>
  </div>
</template>

<script>
export default {
  name: "Restaurant",
  props: ["r", "index"],
  data() {
    return {
      avg: 0
    }
  },
  mounted() {
    this.avg = (this.r.guests / this.r.total).toFixed(0);
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