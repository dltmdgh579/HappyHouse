<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 class="text-left">FAQ</h3>
    <br />
    <br />
    <VueFaqAccordion :items="myItems" :activeColor="color" />
  </b-container>
</template>

<script>
import VueFaqAccordion from "vue-faq-accordion";

export default {
  components: {
    VueFaqAccordion,
  },
  data() {
    return {
      color: "#0275d8",
      myItems: [
        {
          title: "HappyHouse에 대해서 알려주세요",
          value:
            "HappyHouse는 주택 거래 내역과 그 주변 정보검색 기능을 제공하는 서비스입니다.",
          category: "사이트 이용관련",
        },
        {
          title: "HappyHouse는 무료인가요?",
          value: "네 HappyHouse는 무료로 이용 가능합니다.",
          category: "사이트 이용관련",
        },
        {
          title: "자유게시판 이용이 안됩니다",
          value:
            "자유게시판은 로그인한 사용자만 이용할 수 있습니다. 이용 전 로그인 해주세요.",
          category: "사이트 이용관련",
        },
        {
          title: "회원가입은 무료인가요?",
          value: "HappyHouse는 무료로 가입 후 이용가능합니다.",
          category: "회원관련",
        },
        {
          title: "뉴스는 HappyHouse에서 제공하는 뉴스인가요?",
          value: "뉴스탭에서 검색되는 뉴스는 네이버에서 제공하는 뉴스입니다.",
          category: "사이트 이용관련",
        },
        {
          title: "회원 정보는 모두 필수기재해야하나요?",
          value:
            "회원 정보 미 기재시 비밀번호 찾기 등의 기능이 제한될 수 있으므로 모두 입력해주시기 바랍니다.",
          category: "회원관련",
        },
        {
          title: "비밀번호를 잊어버렸습니다.",
          value:
            "비밀번호 찾기를 통하여 가입시 등록된 이메일로 비밀번호를 확인할 수 있습니다.",
          category: "회원관련",
        },
      ],
    };
  },
};
</script>

<style scoped>
div {
  font-family: "HallymGothic-Regular";
  font-weight: normal;
}
</style>
