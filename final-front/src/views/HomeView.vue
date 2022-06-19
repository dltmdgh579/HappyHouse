<template>
  <b-container fluid style="padding: 0px">
    <div class="kakaomap">
      <kakao-map></kakao-map>
      <div class="search">
        <home-search-bar></home-search-bar>
      </div>
      <div v-if="detailFlag" class="detail">
        <div class="detailapt">
          <home-detail></home-detail>
        </div>
      </div>
      <div v-if="detailPlaceFlag" class="detail">
        <div class="detailapt">
          <place-detail></place-detail>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import KakaoMap from "../components/kakaomap/KakaoMap.vue";
import HomeSearchBar from "../components/house/HouseSearchBar.vue";
import HomeDetail from "../components/house/HouseDetail.vue";
import PlaceDetail from "../components/house/PlaceDetail.vue";
import vClickOutside from "v-click-outside";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HomeView",
  directives: {
    clickOutside: vClickOutside.directive,
  },
  computed: {
    ...mapState(houseStore, ["detailFlag", "detailPlaceFlag"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  components: {
    KakaoMap,
    HomeSearchBar,
    HomeDetail,
    PlaceDetail,
  },
  props: {
    msg: String,
  },
  methods: {
    ...mapActions(houseStore, ["setFlagFalse", "getHouseListAll"]),
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.kakaomap {
  position: relative;
  height: 92.5vh;
  overflow: hidden;
}

.search {
  position: absolute;
  top: 20px;
  left: 50%;
  transform: translate(-50%);
  z-index: 1;
}

.detail {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
}

.detailapt {
  position: relative;
  overflow: auto;
}
</style>
