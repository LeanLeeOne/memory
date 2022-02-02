<template>
  <div class="main">
    <baidu-map class="map"
               :center="center"
               :zoom="zoom"
               :scroll-wheel-zoom="true"
               @ready="resetMap">
      <bm-marker v-for="(r, i) in !this.isCollapse ? this.restaurants : []"
                 :key="'p-'+r.id"
                 :position="{lng:r.longitude,lat:r.latitude}">
        <bm-label :content="(i+1)+'.'+r.name"
                  :labelStyle="label.style"
                  :offset="label.offset"/>
      </bm-marker>
      <bm-point-collection :points="sites"
                           shape="BMAP_POINT_SHAPE_CIRCLE"
                           size="BMAP_POINT_SIZE_SMALL"
                           color="red">
      </bm-point-collection>
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                      :showAddressBar="true"
                      :autoLocation="true">
      </bm-geolocation>
    </baidu-map>
    <div class="left-panel">
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
      <div class="list"
           v-show="!isCollapse">
        <RestaurantItem v-for="(r, i) in restaurants"
                        :key="'r-'+r.id"
                        :index="page.offset + i"
                        :name="r.name"
                        :total="r.total*1"
                        :guests="r.guests"
                        :timestamp="r.timestamp"
                        :address="r.address"
                        :summary="r.summary"
                        :url="r.url"
                        @click.native="setCenter(r.longitude, r.latitude)">
        </RestaurantItem>
      </div>
      <Spin v-show="page.loading"
            v-bind:class="{loading: true}"
            fix>
      </Spin>
      <Page :total="page.count"
            v-show="!isCollapse"
            @on-change="changePage">
      </Page>
    </div>
    <div class="add-item-wrapper">
      <Button type="success"
              shape="circle"
              icon="md-add"
              @click="showAddRestaurantView">
      </Button>
    </div>
    <RestaurantAdd :show="addRestaurantView"
                   @hide="hideAddRestaurantView">
    </RestaurantAdd>
  </div>
</template>

<script>
import RestaurantItem from "./restaurant/Item";
import RestaurantAdd from "./restaurant/Add";

export default {
  name: "Map",
  data() {
    return {
      center: {lng: 0, lat: 0},
      zoom: 3,
      restaurants: [],
      page: {
        loading: true,
        count: 0,
        offset: 0,
        size: 10
      },
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
      },
      addRestaurantView: false,
    }
  },
  components: {
    RestaurantItem,
    RestaurantAdd,
  },
  mounted() {
    this.drawList();
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
      zoom || (zoom = 16);
      this.center.lng = lng;
      this.center.lat = lat;
      this.zoom = zoom;
    },
    changePage(page) {
      this.page.loading = true;

      let limit = this.page.size * page;
      let offset = this.page.size * --page
      this.page.offset = offset;
      this.drawList(offset, limit);
    },
    drawList(offset, limit) {
      this.$axios
          .get("/api/restaurant/count/")
          .then(response => {
            this.page.count = response.data;
          });
      this.$axios
          .get("/api/restaurant/query/", {
                params: {
                  limit: limit,
                  offset: offset
                }
              }
          )
          .then(response => {
            this.page.loading = false;

            // 不是数组
            if (!Array.isArray(response.data)) {
              console.error(response.data);
              return true;
            }

            this.restaurants = response.data;
          });
    },
    drawAllSite() { // 所有点位
      this.$axios
          .get("/api/restaurant/sites/")
          .then(response => {
            // 不是数组
            if (!Array.isArray(response.data)) {
              console.error(response.data);
              return true;
            }

            this.sites = response.data;
          });
    },
    collapse() {
      this.isCollapse = !this.isCollapse;
    },
    showAddRestaurantView() {
      this.addRestaurantView = true;
    },
    hideAddRestaurantView() {
      this.addRestaurantView = false;
    },
  }
}
</script>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
.left-panel {
  width: 270px;
  height: calc(100% - 150px);
  position: absolute;
  top: 1em;
  left: 1em;
  text-align: left;
}
.loading {
  margin-top: 32px;
  background-color: #fff7;
}
.add-item-wrapper {
  width: 32px;
  height: 32px;
  position: absolute;
  bottom: 30px;
  right: 55px;
}
.list {
  height: 100%;
  margin-bottom: 1em;
  overflow-y: scroll;
}
.menu button:nth-child(3) {
  margin-left: 3px;
}
::-webkit-scrollbar {
  width: 6px;
  height: 16px;
  background-color: #0002;
}
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #1296db;
}
</style>