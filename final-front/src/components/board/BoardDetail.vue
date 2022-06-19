<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">자유게시판</h3>
    <br />
    <br />

    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h4>
          ${article.subject} </h4></br><div><h6 align='right'>작성자: ${article.userid}</div><div align='right'>작성시간: ${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
          <b-card-footer
            v-for="reply in replies"
            :key="reply.userid"
            variant="light"
          >
            <board-reply-item v-bind="reply"></board-reply-item>
          </b-card-footer>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col sm="10" row="2" align="left">
        <b-form-input v-model="content" />
      </b-col>
      <b-col sm="2" align="right">
        <b-button variant="outline-primary" @click="Reply">댓글달기</b-button>
      </b-col>
    </b-row>
    <br />
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle"
          >&nbsp;목록&nbsp;</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          v-if="checkUser()"
          variant="outline-info"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button
          v-if="checkUser()"
          variant="outline-danger"
          @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle, getReply, newReply } from "@/api/board";
import BoardReplyItem from "@/components/board/item/BoardReplyItem.vue";
import { mapState } from "vuex";

export default {
  components: { BoardReplyItem },
  name: "BoardDetail",
  data() {
    return {
      article: {},
      content: "",
      replies: [],
      isWriter: false,
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    ),
      getReply(
        this.$route.params.articleno,
        (response) => {
          this.replies = response.data;
          console.log(this.replies);
        },
        (error) => {
          console.log(error);
        },
      );
  },
  mounted() {
    this.checkUser();
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
    Reply() {
      newReply(
        {
          userid: this.userInfo.userid,
          articleno: this.$route.params.articleno,
          content: this.content,
        },
        () => {
          this.replies = [];
          getReply(this.$route.params.articleno, (response) => {
            this.replies = response.data;
            this.content = "";
          });
        },
      );
    },
    checkUser() {
      if (
        this.article.userid == this.userInfo.userid ||
        this.userInfo.userid == "admin"
      ) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style></style>
