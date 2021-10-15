<template>
  <div class="main">
    <baidu-map class="map" center="北京">
      <bm-marker v-for="(r, i) in restaurants" :key="'p-'+r.id"
                 :position="{lng:r.longitude,lat:r.latitude}">
        <bm-label :content="(i+1)+'.'+r.name"
                  :labelStyle="label.style"
                  :offset="label.offset"/>
      </bm-marker>
    </baidu-map>
    <div class="list">
      <Restaurant v-for="(r, i) in restaurants" :key="'r-'+r.id"
                  :index="i"
                  :name="r.name"
                  :total="r.total*1"
                  :guests="r.guests*1"
                  :timestamp="r.timestamp"
                  :address="r.address"
                  :summary="r.summary"
                  :url="r.url">
      </Restaurant>
    </div>
  </div>
</template>

<script>
import Restaurant from "./Restaurant";

export default {
  name: "Map",
  data() {
    return {
      restaurants: [],
      sites: [],
      label: {
        style: {
          backgroundColor: "#fffa",
          fontSize: "16px"
        },
        offset: {
          width: -3,
          height: -30
        }
      }
    }
  },
  components: {
    Restaurant
  },
  mounted() {
    // 最近
    this.$axios
      .get("/api/restaurant/query/")
      .then(response => {
        this.restaurants = response.data;
      });

    // 所有点位
    this.$axios
      .get("/api/restaurant/sites/")
      .then(response => {
        this.sites = response.data;
      });
  }
}
</script>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
.list {
  position: absolute;
  top: 1em;
  left: 1em;
}
</style>