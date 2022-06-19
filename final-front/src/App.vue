<template>
  <div id="app">
    <navi-bar class="shadow p-0 mb-1 rounded" />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
@font-face {
  font-family: "GongGothicMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "HallymGothic-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2204@1.0/HallymGothic-Regular.woff2")
    format("woff2");
  font-weight: 400;
  font-style: normal;
}

a {
  text-decoration: none;
}

router-view {
  overflow: hidden;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
}

h3 {
  font-family: "GongGothicMedium";
  font-weight: normal;
}
</style>
