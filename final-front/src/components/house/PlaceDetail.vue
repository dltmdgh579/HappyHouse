<template>
  <div v-if="place" class="bv-example-row">
    <h2>{{ place[0].place_name }}</h2>
    <h6>{{ place[0].category_group_name }}</h6>
    <br />
    <b-table stacked :items="place" :fields="fields"> </b-table>
    <hr />
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "PlaceDetail",
  computed: {
    ...mapState(houseStore, ["place"]),
  },
  data() {
    return {
      fields: [
        {
          key: "category_name",
          label: "분류",
        },
        {
          key: "road_address_name",
          label: "주소",
        },
        {
          key: "address_name",
          label: "지번",
        },
        {
          key: "phone",
          label: "전화번호",
        },
      ],
    };
  },
};
</script>

<style scoped>
.bv-example-row {
  background-color: white;
  height: 92.5vh;
  width: 450px;
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
</style>
