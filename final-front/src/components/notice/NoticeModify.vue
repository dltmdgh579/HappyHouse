<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">공지사항 - 수정하기</h3>
    <br />
    <br />
    <notice-input-item type="modify" />
  </b-container>
</template>

<script>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem.vue";

export default {
  name: "NoticeModify",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
