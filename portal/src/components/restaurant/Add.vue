<template>
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
               placeholder="请输入饭店名"
               @on-blur="searchByName">
        </Input>
      </Form-item>
      <Form-item label="消费金额"
                 prop="total">
        <Input v-model="addRestaurantModel.total"
               placeholder="请输入消费金额">
        </Input>
      </Form-item>
      <Form-item label="探店时间"
                 prop="timestamp">
        <Input v-model="addRestaurantModel.timestamp"
               placeholder="请输入探店时间">
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
      <Form-item label="地址">
        <Row>
          <Col span="18">
            <Select v-model="select"
                    @on-change="selectSite">
              <Option v-for="(site, i) in sites"
                      :key="site.uid"
                      :value="i">
                {{ site.name }} @ {{ site.address }}
              </Option>
            </Select>
          </Col>
          <Col span="5" offset="1">
            <Input v-model="region"
                   placeholder="行政区划"
                   @on-blur="searchByName">
            </Input>
          </Col>
        </Row>
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
</template>

<script>
export default {
  name: "RestaurantAdd",
  props: {
    show: Boolean,
  },
  data() {
    return {
      addRestaurantView: false,
      select: "",
      region: "北京",
      sites: [],
      addRestaurantModel: {
        url: "",
        name: "",
        total: "",
        guests: 3,
        timestamp: "",
        address: "",
        longitude: "",
        latitude: "",
        branch: 1,
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
        timestamp: [
          {required: true, message: '探店时间不能为空', trigger: 'blur'}
        ],
      }
    }
  },
  watch: {
    show() {
      this.addRestaurantView = this.show;
    }
  },
  methods: {
    hideAddRestaurantView() {
      this.addRestaurantView = false;
      this.$emit("hide", false);
    },
    searchByName() {
      if (!this.addRestaurantModel.name) {
        return;
      }
      this.$axios
          .get("/api/restaurant/search/", {
            params: {
              name: this.addRestaurantModel.name,
              region: this.region
            }
          })
          .then(response => {
            let result = response.data.results;
            // 不是数组
            if (!Array.isArray(result)) {
              console.error(result);
              return true;
            }

            this.sites = result;
          });
    },
    selectSite(index) {
      if (index === undefined) {
        return;
      }

      let site = this.sites[index];
      this.addRestaurantModel.address = site.address;
      this.addRestaurantModel.longitude = site.location.lng;
      this.addRestaurantModel.latitude = site.location.lat;
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
      let data = this.prepareProcess();
      this.$axios
          .post("/api/restaurant/add/", data)
          .then(response => {
            if (response.data > 0) {
              callback();
            } else {
              this.$Message.error('增加失败!');
            }
          });
    },
    prepareProcess() {
      return {
        url: this.addRestaurantModel.url.split("?")[0],
        name: this.addRestaurantModel.name.trim(),
        total: this.addRestaurantModel.total.trim(),
        guests: this.addRestaurantModel.guests,
        timestamp: this.addRestaurantModel.timestamp.trim(),
        address: this.addRestaurantModel.address.trim(),
        longitude: this.addRestaurantModel.longitude,
        latitude: this.addRestaurantModel.latitude,
        branch: !!this.branch,
        summary: this.addRestaurantModel.summary.trim(),
      };
    },
    initAddRestaurantModel() {
      this.addRestaurantModel.url = "";
      this.addRestaurantModel.name = "";
      this.addRestaurantModel.total = "";
      this.addRestaurantModel.guests = 3;
      this.addRestaurantModel.timestamp = "";
      this.addRestaurantModel.address = "";
      this.addRestaurantModel.longitude = "";
      this.addRestaurantModel.latitude = "";
      this.addRestaurantModel.branch = 1;
      this.addRestaurantModel.summary = "";
      this.select = "";
      this.sites = [];
    },
  }
}
</script>

<style scoped>

</style>