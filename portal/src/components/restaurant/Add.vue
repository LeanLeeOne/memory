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

</style>