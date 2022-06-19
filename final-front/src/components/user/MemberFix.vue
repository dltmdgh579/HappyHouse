<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">정보 수정</h3>
    <br />
    <br />
    <div>
      <b-container fluid>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-default" class="mb-4 form-label"
              >아이디<span class="text-danger">&nbsp;*</span></label
            >
          </b-col>
          <b-col sm="10">
            <b-form-input
              id="input-default"
              type="text"
              v-model="userid"
              disabled="disabled"
            >
            </b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-default2" class="mb-4 form-label"
              >비밀번호<span class="text-danger">&nbsp;*</span></label
            >
          </b-col>
          <b-col sm="10">
            <b-form-input
              id="input-default2"
              type="password"
              v-model="userpwd"
            ></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-default5" class="mb-4 form-label"
              >비밀번호 확인<span class="text-danger">&nbsp;*</span></label
            >
          </b-col>
          <b-col sm="10">
            <b-form-input
              id="input-default5"
              type="password"
              v-model="userpwd2"
              @keyup="pwcheck"
            ></b-form-input>
            <label
              v-if="pwproblem == 2"
              for="input-default5"
              class="ps-5 text-danger"
              >비밀번호를 다시 확인해주세요</label
            >
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-default3" class="mb-4 form-label"
              >이름<span class="text-danger">&nbsp;*</span></label
            >
          </b-col>
          <b-col sm="10">
            <b-form-input
              id="input-default3"
              type="text"
              v-model="username"
            ></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-default4" class="mb-4 form-label"
              >이메일<span class="text-danger">&nbsp;*</span></label
            >
          </b-col>
          <b-col sm="10">
            <b-form-input
              id="input-default4"
              type="text"
              v-model="email"
            ></b-form-input>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div class="col-12 text-center mt-4">
      <a
        @click="fixMember"
        class="btn px-4 btn-block btn-primary lift text-uppercase"
        >수정하기</a
      >
      <a
        @click="goback"
        class="btn px-4 btn-block btn-secondary lift text-uppercase"
        >취소</a
      >
    </div>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import axios from "axios";
export default {
  name: "MemberfixPage",
  data() {
    return {
      userid: "",
      username: "",
      userpwd: "",
      userpwd2: "",
      email: "",
      pwproblem: "",
    };
  },
  components: {},
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    this.userid = this.userInfo.userid;
    this.username = this.userInfo.username;
    this.userpwd = this.userInfo.userpwd;
    this.email = this.userInfo.email;
  },
  methods: {
    ...mapMutations("memberStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    fixMember() {
      if (this.pwproblem != 1) {
        alert("비밀번호를 확인해주세요");
      } else {
        axios
          .put("http://localhost:9999/vue/user/info", {
            userid: this.userid,
            username: this.username,
            userpwd: this.userpwd,
            email: this.email,
          })
          .then(alert("변경되었습니다. 다시 로그인해주세요"))
          .then(this.onClickLogout());
      }
    },
    goback() {
      this.$router.push("/");
    },
    pwcheck() {
      if (this.userpwd == this.userpwd2) {
        this.pwproblem = 1;
      } else {
        this.pwproblem = 2;
      }
    },
  },
};
</script>

<style></style>
