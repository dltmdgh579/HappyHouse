<template>
  <b-container class="bv-example-row mt-3" style="margin-left: 200px">
    <br />
    <br />
    <h3 align="left">비밀번호 찾기</h3>
    <br />
    <br />
    <b-row>
      <b-col cols="12">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="userid"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="userpwd">
              <b-form-input
                type="text"
                id="userpwd"
                v-model="email"
                required
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="sendmail"
              >비밀번호 찾기</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";
export default {
  name: "findPwd",
  data() {
    return {
      userid: "",
      email: "",
    };
  },
  methods: {
    sendmail() {
      var cnt;
      console.log(this.userid);
      console.log(this.email);
      axios
        .get(
          "http://localhost:9999/vue/user/mailcheck/" +
            this.userid +
            "/" +
            this.email,
        )
        .then((response) => {
          cnt = response.data;
          console.log(cnt);
        })
        .then(() => {
          if (cnt == 1) {
            axios
              .get("http://localhost:9999/vue/user/findpwd/" + this.userid)
              .then(alert("가입시 등록된 메일로 비밀번호를 전송했습니다."))
              .then(this.$router.push({ name: "home" }));
          } else {
            alert("아이디 또는 메일을 확인해주세요");
          }
        });
    },
  },
};
</script>

<style></style>
