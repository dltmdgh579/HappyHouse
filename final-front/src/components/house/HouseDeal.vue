<template>
  <div>
    <div class="dealScope">
      매매 {{ detailHouses[0].minDeal | priceCast }} ~
      {{ detailHouses[0].maxDeal | priceCast }}
    </div>
    <div>
      <b-table
        sticky-header="500px"
        hover
        :items="detailHouses"
        :fields="fields"
        head-variant="light"
      >
        <template #head()="scope">
          <div class="text-center">{{ scope.label }}</div>
        </template>
        <template #cell(dealAmount)="deal">
          <b-badge variant="gray - 200">매매</b-badge>
          <b>
            {{ deal.item.dealAmount | price }}
          </b>
        </template>
        <template #cell(area)="deal">
          {{ deal.item.area }}m<sup>2</sup>
        </template>
        <template #cell(dealYear)="deal">
          {{ deal.item.dealYear }}.{{ deal.item.dealMonth }}.{{
            deal.item.dealDay
          }}
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDeal",
  data() {
    return {
      fields: [
        {
          key: "dealYear",
          sortable: true,
          label: "거래일자",
        },
        {
          key: "dealAmount",
          sortable: true,
          label: "거래가격",
        },
        {
          key: "area",
          sortable: true,
          label: "타입",
        },
        {
          key: "floor",
          sortable: true,
          label: "층",
        },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["detailHouses"]),
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
    priceCast(value) {
      if (!value) return value;
      let str = value.split("");
      let result = "";
      if (str[str.length - 5]) {
        if (str[str.length - 6]) {
          if (str[str.length - 4] == "0" && str[str.length - 3] == "0") {
            result = str[str.length - 6] + str[str.length - 5] + "억";
          } else if (str[str.length - 4] == "0" && str[str.length - 3] != "0") {
            result =
              str[str.length - 6] +
              str[str.length - 5] +
              "억" +
              str[str.length - 3] +
              "00";
          } else {
            result =
              str[str.length - 6] +
              str[str.length - 5] +
              "억" +
              str[str.length - 4] +
              str[str.length - 3] +
              "00";
          }
        } else {
          if (str[str.length - 4] == "0" && str[str.length - 3] == "0") {
            result = str[str.length - 5] + "억";
          } else if (str[str.length - 4] == "0" && str[str.length - 3] != "0") {
            result = str[str.length - 5] + "억" + str[str.length - 3] + "00";
          } else {
            result =
              str[str.length - 5] +
              "억" +
              str[str.length - 4] +
              str[str.length - 3] +
              "00";
          }
        }
      } else {
        result = str[str.length - 4] + str[str.length - 3] + "00";
      }
      return result;
    },
  },
};
</script>

<style>
.dealScope {
  font-size: 1.2em;
  font-weight: bold;
  margin-bottom: 10px;
  color: rgb(50, 123, 233);
}
</style>
