<template>
  <div class="moonback">
    <div class="halo"></div>
    <div class="star" style="top:20px;right:220px;"></div>
    <div class="star" style="top:50px;right:120px;"></div>
    <div class="star" style="top:190px;left:20px;"></div>
    <div class="star" style="top:220px;left:50px;"></div>
  </div>
</template>

<script>
export default {
  name: "MoonBack"
}
</script>

<style scoped>
/*背景*/
.moonback {
  width: 100%;
  height: 900px;
  margin: 0 auto;
  position: absolute;
  top: 0;
  overflow: hidden;
  text-align: center;
}
/*背景*/

/*天狗*/
.moonback::after {
  content: " ";
  display: block;
  position: absolute;
  width: 700px;
  height: 700px;
  background-color: #000;
  border-radius: 50%;
  animation: 60s dog linear infinite;
}
@keyframes dog {
  0% {
    top: calc(56px - 693px);
    left: calc(50% - 688px - 344px);
  }
  100% {
    top: calc(56px + 693px);
    left: calc(50% + 344px);
  }
}
/*天狗*/

/*光环*/
.halo {
  width: 688px;
  height: 693px;
  margin: 60px auto;
  border-radius: 50%;
  box-shadow: 0 0 50px 50px #dbd86a;
  animation-name: lightHalo;
  animation-duration: 60s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}
@keyframes lightHalo {
  from {
    opacity: 0.4;
  }
  to {
    opacity: 0;
  }
}
/*光环*/

/*星辰*/
.star {
  position: absolute;
}
.star::before {
  content: "★";
  display: block;
  position: absolute;
  left: 10px;
  top: 20px;
  width: auto;
  height: auto;
  color: #fff;
  transform: scale(0.1);
  animation: 11s starlight linear infinite;
}
.star::after {
  content: "★";
  display: block;
  position: absolute;
  left: 40px;
  top: 120px;

  width: auto;
  height: auto;
  color: #fff;
  transform: scale(0.5);
  animation: 12s starlight linear infinite;
}
@keyframes starlight {
  0% {
    transform: scale(0.5);
  }
  100% {
    transform: scale(0.1);
  }
}
/*星辰*/
</style>