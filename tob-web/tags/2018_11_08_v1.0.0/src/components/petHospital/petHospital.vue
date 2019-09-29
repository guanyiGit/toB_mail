<template>
  <!--主体内容-->
  <div class="w1200 ovh c-b mgt35">
    <!--侧边栏-->
    <main-memu></main-memu>
    <!--侧边栏END-->

    <!--内容区-->
    <router-view></router-view>
    <!--内容区END-->
  </div>
  <!--主体内容END-->
</template>

<script>
  import mainMemu from '../common/mainMemu'

export default {
  name: "petHospital",
  components: {
    mainMemu
  },
}
</script>

<style scoped>

</style>
