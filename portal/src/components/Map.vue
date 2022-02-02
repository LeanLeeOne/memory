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
        <Restaurant v-for="(r, i) in restaurants"
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
        </Restaurant>
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
    <Modal v-model="addRestaurantView"
           title="饭店信息"
           :loading="true"
           ok-text="增加"
           @on-ok="validateRestaurant('addRestaurantModel')"
           @on-cancel="hideAddRestaurantView">
      <Form ref="addRestaurantModel"
            :model="addRestaurantModel"
            :rules="addRestaurantRule"
            :label-width="100">
        <Form-item label="视频地址"
                   prop="url">
          <Input v-model="addRestaurantModel.url"
                 placeholder="请输入视频地址">
          </Input>
        </Form-item>
        <Form-item label="饭店名"
                   prop="name">
          <Input v-model="addRestaurantModel.name"
                 placeholder="请输入饭店名">
          </Input>
        </Form-item>
        <Form-item label="消费金额"
                   prop="total">
          <Input v-model="addRestaurantModel.total"
                 placeholder="请输入消费金额">
          </Input>
        </Form-item>
        <Form-item label="就餐人数"
                   prop="guests">
          <Input v-model="addRestaurantModel.guests"
                 placeholder="请选择人数">
          </Input>
          <Slider v-model="addRestaurantModel.guests"
                  show-stops
                  :min="1"
                  :max="10">
          </Slider>
        </Form-item>
        <Form-item label="探店时间"
                   prop="timestamp">
          <Input v-model="addRestaurantModel.timestamp"
                 placeholder="请输入探店时间">
          </Input>
        </Form-item>
        <Form-item label="地址"
                   prop="address">
          <Input v-model="addRestaurantModel.address"
                 placeholder="请输入地址">
          </Input>
        </Form-item>
        <Form-item label="经度"
                   prop="longitude">
          <Input v-model="addRestaurantModel.longitude"
                 placeholder="请输入经度">
          </Input>
        </Form-item>
        <Form-item label="纬度"
                   prop="latitude">
          <Input v-model="addRestaurantModel.latitude"
                 placeholder="请输入纬度">
          </Input>
        </Form-item>
        <Form-item label="是否有分店"
                   prop="branch">
          <Radio-group v-model="addRestaurantModel.branch">
            <Radio :label="0">否</Radio>
            <Radio :label="1">是</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="总结"
                   prop="summary">
          <Input v-model="addRestaurantModel.summary"
                 placeholder="请输入总结">
          </Input>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import Restaurant from "./restaurant/Item";

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
      addRestaurantModel: {
        url: "",
        name: "",
        total: "",
        guests: 3,
        timestamp: null,
        address: null,
        longitude: null,
        latitude: null,
        branch: 0,
        summary: ""
      },
      addRestaurantRule: {
        url: [
          {required: true, message: '视频链接不能为空', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '饭店名不能为空', trigger: 'blur'}
        ],
        total: [
          {required: true, message: '总计花费不能为空', trigger: 'blur'}
        ],
      }
    }
  },
  components: {
    Restaurant
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
    validateRestaurant(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.addRestaurant(() => {
            this.hideAddRestaurantView();
            this.$Message.success('提交成功!');
            this.initAddRestaurantModel();
          });
        } else {
          this.$Message.error('表单验证失败!');
        }
      })
    },
    addRestaurant(callback) {
      this.addRestaurantModel.branch = !!this.addRestaurantModel.branch;
      this.$axios
          .post("/api/restaurant/add/", this.addRestaurantModel)
          .then(response => {
            response.data;
            callback();
          });
    },
    initAddRestaurantModel() {
      this.addRestaurantModel.url = "";
      this.addRestaurantModel.name = "";
      this.addRestaurantModel.total = "";
      this.addRestaurantModel.guests = 3;
      this.addRestaurantModel.timestamp = null;
      this.addRestaurantModel.address = null;
      this.addRestaurantModel.longitude = null;
      this.addRestaurantModel.latitude = null;
      this.addRestaurantModel.branch = 0;
      this.addRestaurantModel.summary = "";
    }
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