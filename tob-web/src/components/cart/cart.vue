<template>
  <!--主体内容-->
  <div >
    <!--&lt;!&ndash;侧边栏&ndash;&gt;-->
    <!--<main-memu></main-memu>-->
    <!--&lt;!&ndash;侧边栏END&ndash;&gt;-->

    <!--内容区-->
    <div>
      <router-view></router-view>
    </div>
    <!--内容区END-->
  </div>
  <!--主体内容END-->
</template>

<script>
  import mainMemu from '../common/mainMemu'
  export default {
    name: "order",
    components: {
      mainMemu
    },
  }
</script>

<style scoped>

</style>
