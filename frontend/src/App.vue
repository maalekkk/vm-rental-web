<template>
  <div id="app">
    <main-template/>
  </div>
</template>

<script>
import MainTemplate from './components/MainTemplate.vue';
export default {
  name: "App",
  components: {
    MainTemplate
  },
};
</script>

<style>
  #app {
      height: 100%;
      width: 100%;
      overflow: hidden;
  }
</style>