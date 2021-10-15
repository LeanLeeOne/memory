<template>
  <div class="main">
    <baidu-map class="map" :center="center" :zoom="zoom" @ready="resetMap">
      <bm-marker v-for="(r, i) in restaurants" :key="'p-'+r.id"
                 :position="{lng:r.longitude,lat:r.latitude}">
        <bm-label :content="(i+1)+'.'+r.name"
                  :labelStyle="label.style"
                  :offset="label.offset"/>
      </bm-marker>
    </baidu-map>
    <div class="list">
      <div class="menu">
        <Button type="success"
                @click="collapse"
                v-show="!isCollapse">收起
        </Button>
        <Button type="error"
                @click="collapse"
                v-show="isCollapse">展开
        </Button>
        <Button type="info"
                @click="resetMap">重置地图
        </Button>
      </div>
      <Restaurant v-for="(r, i) in restaurants" :key="'r-'+r.id"
                  :index="i"
                  :name="r.name"
                  :total="r.total*1"
                  :guests="r.guests"
                  :timestamp="r.timestamp"
                  :address="r.address"
                  :summary="r.summary"
                  :url="r.url"
                  v-show="!isCollapse"
                  @click.native="setCenter(r.longitude, r.latitude)">
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
      center: {lng: 0, lat: 0},
      zoom: 3,
      restaurants: [],
      isCollapse: false,
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
    this.drawRecent();
    this.drawAllSite();
  },
  methods: {
    resetMap({BMap, map}) {
      BMap && map && console.log(BMap, map);
      this.setCenter(116.404001, 39.915001, 12);
      setTimeout(() => {
        this.setCenter(116.404, 39.915, 12);
      }, 100);
    },
    setCenter(lng, lat, zoom) {
      zoom || (zoom = 14);
      this.center.lng = lng;
      this.center.lat = lat;
      this.zoom = zoom;
    },
    drawRecent() { // 最近
      this.$axios
        .get("/api/restaurant/query/")
        .then(response => {
          this.restaurants = response.data;
        });
    },
    drawAllSite() { // 所有点位
      this.$axios
        .get("/api/restaurant/sites/")
        .then(response => {
          this.sites = response.data;
        });
    },
    collapse() {
      this.isCollapse = !this.isCollapse;
    }
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
  text-align: left;
}
.menu button:nth-child(3) {
  margin-left: 3px;
}
</style>