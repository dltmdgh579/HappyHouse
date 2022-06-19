<template>
  <div v-if="detailHouses" class="container">
    <h2>{{ detailHouses[0].apartmentName }}</h2>
    <h6>{{ detailHouses[0].buildYear }}년 건설</h6>
    <br />
    <b-table stacked :items="detailHouse" :fields="fields">
      <!-- <template #head()="head">
        <div class="text-left">{{ head.label }}</div>
      </template> -->
      <template #cell(minArea)="info">
        {{ info.item.minArea }}m<sup>2</sup> ~ {{ info.item.maxArea }}m<sup
          >2</sup
        >
      </template>
      <template #cell(minFloor)="info">
        {{ info.item.minFloor }} ~ {{ info.item.maxFloor }}
      </template>
      <template #cell(sidoName)="info">
        {{ info.item.sidoName }} {{ info.item.gugunName }}
        {{ info.item.roadName }} {{ info.item.jibun }}
      </template>
    </b-table>
    <hr />
    <b-card
      border-variant="info"
      header-bg-variant="info"
      header-text-variant="white"
      header="최근 매매 실거래가"
      class="text-center"
    >
      <b-card-text class="recent-price">{{
        detailHouses[0].recentPrice | price
      }}</b-card-text>
    </b-card>
    <br />
    <b-nav tabs fill>
      <b-nav-item to="/deal" exact exact-active-class="active"
        >거래 내역</b-nav-item
      >
      <b-nav-item to="/places" exact exact-active-class="active"
        >주변 인프라</b-nav-item
      >
    </b-nav>
    <br />
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  mounted() {
    this.detailHouse.push(this.detailHouses[0]);
  },
  watch: {
    detailHouses: function (val) {
      this.detailHouse = [];
      this.detailHouse.push(val[0]);
    },
  },
  computed: {
    ...mapState(houseStore, ["detailHouses", "houses", "places"]),
  },
  data() {
    return {
      detailHouse: [],
      fields: [
        {
          key: "buildYear",
          label: "건설년도",
        },
        {
          key: "minFloor",
          label: "층수",
        },
        {
          key: "minArea",
          label: "타입",
        },
        {
          key: "sidoName",
          label: "주소",
        },
      ],
    };
  },
  filters: {
    price(value) {
      if (!value) return value;
      let str = value.split(",")[0].split("");
      let str2 = value.split(",")[1];
      let result = "";
      if (str[str.length - 2]) {
        if (str[str.length - 3]) {
          if (str[str.length - 1] == "0" && str2[0] == "0") {
            result = str[str.length - 3] + str[str.length - 2] + "억";
          } else if (str[str.length - 1] == "0" && str2[0] != "0") {
            result =
              str[str.length - 3] + str[str.length - 2] + "억" + str2 + "만원";
          } else {
            result =
              str[str.length - 3] +
              str[str.length - 2] +
              "억" +
              str[str.length - 1] +
              str2 +
              "만원";
          }
        } else {
          if (str[str.length - 1] == "0" && str2[0] == "0") {
            result = str[str.length - 2] + "억";
          } else if (str[str.length - 1] == "0" && str2[0] != "0") {
            result = str[str.length - 2] + "억" + str2 + "만원";
          } else {
            result =
              str[str.length - 2] + "억" + str[str.length - 1] + str2 + "만원";
          }
        }
      } else {
        result = str[str.length - 1] + str2 + "만원";
      }
      return result;
    },
  },
};
</script>

<style scoped>
.aptimg {
  width: 100px;
  height: 300px;
}
.container {
  background-color: white;
  width: 450px;
  height: 92.5vh;
  padding: auto;
  overflow: auto;
}
h2 {
  margin-top: 30px;
  text-align: center;
  font-weight: bold;
}

h6 {
  text-align: center;
  color: rgb(105, 109, 114);
}

.recent-price {
  font-size: 20px;
  font-weight: bold;
}
</style>
