<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
html {
  height: 100%;
  background-color: #000;
}
body{
  height: 100%;
  margin: 0;
  background-color: #000;
}
.main {
  width: 100%;
  height: 100%;
}
#app {
  height: 100%;
  color: LemonChiffon;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
