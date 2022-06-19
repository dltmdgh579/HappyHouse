<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">회원가입</h3>
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
              @change="idcheck"
            >
            </b-form-input>
            <label
              v-if="idproblem == 1"
              for="input-default"
              class="ps-5 text-danger"
              >이미 존재하는 아이디입니다.</label
            ><label
              v-else-if="idproblem == 0"
              for="input-default"
              class="ps-5 text-primary"
              >사용가능한 아이디입니다.</label
            >
            <label
              v-else-if="idproblem == 'short'"
              for="input-default"
              class="ps-5 text-primary"
              >아이디가 너무 짧습니다.</label
            >
            <label
              v-else-if="idproblem == 'long'"
              for="input-default"
              class="ps-5 text-danger"
              >아이디가 너무 깁니다.</label
            >
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
        @click="registMember"
        class="btn px-4 btn-block btn-primary lift text-uppercase"
        >회원가입</a
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
import axios from "axios";

export default {
  name: "MemberRegisterPage",
  data() {
    return {
      userid: "",
      username: "",
      userpwd: "",
      userpwd2: "",
      email: "",
      idproblem: ".",
      pwproblem: ".",
    };
  },
  methods: {
    registMember() {
      axios
        .post("http://localhost:9999/vue/user/register/", {
          userid: this.userid,
          username: this.username,
          userpwd: this.userpwd,
          email: this.email,
        })
        .then(alert("회원가입 되었습니다"))
        .then(this.goback());
    },
    goback() {
      this.$router.push("/");
    },
    idcheck() {
      if (this.userid.length <= 5) {
        this.idproblem = "short";
      } else if (this.userid.length >= 15) {
        this.idproblem = "long";
      } else {
        console.log("HI");
        axios
          .get("http://localhost:9999/vue/user/register/" + this.userid)
          .then((response) => {
            this.idproblem = response.data;
          });
      }
      console.log(this.idproblem);
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
