<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">내 정보</h3>
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
              disabled="disabled"
            ></b-form-input>
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
              disabled="disabled"
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
              disabled="disabled"
            ></b-form-input>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div class="col-12 text-center mt-4">
      <b-row>
        <b-col sm="6">
          <a
            class="btn px-4 btn-block btn-primary lift text-uppercase"
            @click="toMemberfix"
            >회원정보 수정</a
          >
        </b-col>
        <b-col sm="6">
          <a
            @click="dropconfirm"
            class="btn px-4 btn-block btn-danger lift text-uppercase"
            >회원탈퇴</a
          >
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      userid: "",
      userpwd: "",
      username: "",
      email: "",
      pwproblem: ".",
    };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.userid = this.userInfo.userid;
    this.userpwd = this.userInfo.userpwd;
    this.username = this.userInfo.username;
    this.email = this.userInfo.email;
  },
  methods: {
    ...mapActions(memberStore, ["dropMember"]),

    dropconfirm(id) {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        console.log(id);
        this.dropMember(id);
        this.$router.push("signin");
      } else {
        console.log("취소");
      }
    },
    toMemberfix() {
      this.$router.push("fix");
    },
  },
};
</script>

<style></style>
