<template>
  <b-container class="bv-example-row mt-3">
    <br />
    <br />
    <h3 align="left">자유게시판 - 글쓰기</h3>
    <br />
    <br />
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
