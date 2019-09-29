<template>
    <!--主体内容-->
    <div class="w1200 ovh c-b mgt35">

      <!--菜单-->
      <main-memu></main-memu>
      <!--菜单 end-->

      <!--主体内容-->
      <router-view class="w980"></router-view>
      <!--主体内容 end-->

    </div>
    <!--主体内容END-->
</template>

<script>
  import mainMemu from '../common/mainMemu'

export default {
  name: "operator",
  components: {
    mainMemu
  },
}
</script>

<style scoped>

</style>
