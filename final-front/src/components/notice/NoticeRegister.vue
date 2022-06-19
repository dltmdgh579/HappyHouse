<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">공지사항 - 글쓰기</h3>
    <br />
    <br />
    <notice-input-item type="register" />
  </b-container>
</template>

<script>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem.vue";

export default {
  name: "NoticeWrite",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
