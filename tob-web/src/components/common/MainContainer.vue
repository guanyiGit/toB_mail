<template>
  <!--wrap-->
  <div class="container-my">

    <!--头部-->
    <main-top></main-top>
    <!--头部END-->

    <!--主体内容-->
    <router-view></router-view>
    <!--主体内容END-->

    <!--&lt;!&ndash;分割线&ndash;&gt;-->
    <!--<div class="mgt440 mgt80"><img class="db c-b" src="/static/images/6.png" alt="分割线"></div>-->
    <!--&lt;!&ndash;分割线END&ndash;&gt;-->

    <!--&lt;!&ndash;友情链接&ndash;&gt;-->
    <!--<link-exchange></link-exchange>-->
    <!--&lt;!&ndash;友情链接&ndash;&gt;-->

    <!--&lt;!&ndash;分割线&ndash;&gt;-->
    <!--<div class="mgt44"><img class="db c-b" src="/static/images/6.png" alt="分割线"></div>-->
    <!--&lt;!&ndash;分割线END&ndash;&gt;-->

    <!--底部-->
    <!-- <main-bottom></main-bottom> -->
    <!--底部END-->

  </div>
  <!--wrap End-->
</template>

<script>
  export default {
    name: "MainContainer",
  }
</script>

<style scoped>

</style>
