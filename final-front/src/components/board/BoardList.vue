<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <br />
        <br />
        <h3 class="text-left">자유게시판</h3>
        <br />
        <br />
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <board-list-item
              v-for="article in pageSlice"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <b-row class="mb-2">
      <b-col sm="4" />
      <b-col>
        <b-input-group>
          <b-input-group-prepend>
            <select v-model="type">
              <option value="subject">&nbsp;제목</option>
              <option value="id">글쓴이</option>
              <option value="content">&nbsp;내용</option>
            </select></b-input-group-prepend
          >
          <b-form-input v-model="item"></b-form-input>
          <b-input-group-append>
            <b-button variant="outline-info" @click="search">검색</b-button>
          </b-input-group-append>
        </b-input-group>
      </b-col>
      <b-col sm="2" align="left" class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기
        </b-button>
      </b-col>
      <b-col sm="1" />
    </b-row>
    <br />
    <b-row>
      <b-col sm="5" />
      <b-button v-if="pageNo == 0" variant="outline-dark" disabled="disabled">
        이전 페이지</b-button
      >
      <b-button v-else variant="outline-primary" @click="prevPage()">
        이전 페이지
      </b-button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ this.pageNo + 1 }}/{{
        this.pageCount
      }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <b-button
        v-if="pageNo == pageCount - 1"
        variant="outline-dark"
        disabled="disabled"
      >
        다음 페이지
      </b-button>
      <b-button v-else variant="outline-primary" @click="nextPage()"
        >다음 페이지</b-button
      >
    </b-row>
  </b-container>
</template>

<script>
import { listArticle, searchArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      type: "subject",
      item: "",
      pageNo: 0,
    };
  },
  computed: {
    pageCount() {
      let listLeng = this.articles.length,
        page = Math.floor((listLeng - 1) / 10) + 1;
      return page;
    },
    pageSlice() {
      const start = this.pageNo * 10,
        end = start + 10;
      return this.articles.slice(start, end);
    },
  },
  created() {
    let param = {};
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    search() {
      searchArticle(
        this.type,
        this.item,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    nextPage() {
      this.pageNo = Math.min(this.pageNo + 1, this.pageCount - 1);
    },
    prevPage() {
      this.pageNo = Math.max(0, this.pageNo - 1);
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
