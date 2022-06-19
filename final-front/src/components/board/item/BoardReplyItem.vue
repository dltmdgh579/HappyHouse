<template>
  <b-row>
    <b-col align="left">
      <b>{{ userid }}</b>
    </b-col>
    <b-col cols="8" align="left">
      {{ content }}
    </b-col>
    <b-col>
      {{ regtime }}
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "BoardReplyItem",
  props: {
    userid: String,
    content: String,
    regtime: String,
  },
};
</script>

<style></style>
