<template>
  <b-container class="bv-row mt-3">
    <br />
    <br />
    <h3 class="text-left">뉴스</h3>
    <br />
    <news-search-bar id="bar" @selected="selected"></news-search-bar>
    <b-card header="내 관심지역" style="padding: 1px">
      <div class="card-body" style="padding: 1px">
        <span v-for="(interest, index) in interests" :key="index">
          <b-button variant="info" class="mb-1" @click="selected(interest)"
            >{{ interest }}
          </b-button>
          <b-button variant="outline" class="mr-1" @click="remove(interest)"
            >X</b-button
          >
        </span>
      </div>
    </b-card>
    <b-row>
      <b-col>
        <br />
        <b-table-simple hover stripe responsive>
          <b-thead>
            <b-tr>
              <b-th v-if="dongName == null">
                <h4>뉴스를 검색할 지역을 선택해주세요.</h4></b-th
              >
              <b-th v-else
                ><b-col>
                  <h4 sm="4">{{ dongName }} 관련 뉴스입니다.</h4>
                  <b-button variant="outline-info" @click="add(dongName)">
                    관심지역 추가하기</b-button
                  >
                </b-col>
              </b-th>
            </b-tr>
            <br />
          </b-thead>
          <b-tbody>
            <b-tr v-for="(item, index) in news" :key="index">
              <b-row>
                <b-col
                  sm="10"
                  v-html="
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.title
                  "
                  v-b-toggle="'collapse' + index"
                >
                  <b-card class="mt-3">
                    <p v-html="item.title"></p>
                  </b-card>
                </b-col>
              </b-row>
              <b-collapse :id="'collapse' + index.toString()" class="mt-2">
                <b-card>
                  <p v-html="item.description" />
                  <b-button
                    size="sm"
                    variant="outline-primary"
                    @click="viewNews(item.link)"
                    >자세히 보기</b-button
                  >
                </b-card>
              </b-collapse>
              <hr />
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <!-- <div v-for="(item, index) in news" :key="index">
      <b-row class="mt-3">
        <b-col align="left" v-html="item.title" />
        <b-col>
          <b-button
            variant="outline-primary"
            @click="viewNews(item.link)"
            align="right"
            >자세히 보기</b-button
          ></b-col
        >
      </b-row>
      <a :href="item.originallink">자세히 보기</a> -->
    <!-- </div> -->
    <!-- </div> -->
  </b-container>
</template>

<script>
import {
  getNews,
  getInterest,
  removeInterest,
  addInterest,
  checkInterest,
} from "@/api/news.js";
import { mapState } from "vuex";
// import NewsItem from "../components/news/NewsItem.vue";
import NewsSearchBar from "@/components/news/NewsSearchBar.vue";
export default {
  components: {
    NewsSearchBar,
  },
  data() {
    return {
      news: null,
      dongName: null,
      interests: [],
    };
  },
  watch: {
    interests: [],
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    if (this.userInfo == null) return;
    getInterest(
      this.userInfo.userid,
      (response) => {
        this.interests = response.data;
      },
      (error) => {
        console.log("관심지역 에러 발생", error);
      },
    );
  },
  methods: {
    viewNews(value) {
      window.open(value);
    },
    selected(value) {
      this.news = null;
      this.dongName = value;
      getNews(value, (response) => {
        this.news = response.data.items;
      });
    },
    remove(value) {
      removeInterest(this.userInfo.userid, value, () => {
        getInterest(
          this.userInfo.userid,
          (response) => {
            this.interests = response.data;
          },
          (error) => {
            console.log("관심지역 에러 발생", error);
          },
        );
      });
    },
    add(value) {
      checkInterest(this.userInfo.userid, value, (response) => {
        if (response.data == 0) {
          addInterest(this.userInfo.userid, value, () => {
            getInterest(
              this.userInfo.userid,
              (response) => {
                this.interests = response.data;
              },
              (error) => {
                console.log("관심지역 에러 발생", error);
              },
            );
          });
        }
      });
    },
  },
};
</script>

<style scoped>
table {
  font-family: "HallymGothic-Regular";
  font-weight: normal;
}
.row {
  margin-right: 0px;
}

#bar {
  font-family: "HallymGothic-Regular";
  font-weight: normal;
}
</style>
