<template>
  <!--wrap-->
  <div class="container-my">

    <!--头部-->
    <main-top></main-top>
    <!--头部END-->

    <!--主体内容-->
    <router-view></router-view>
    <!--主体内容END-->

    <!--分割线-->
    <div class="mgt440 mgt80"><img class="db c-b" src="/static/images/6.png" alt="分割线"></div>
    <!--分割线END-->

    <!--友情链接-->
    <link-exchange></link-exchange>
    <!--友情链接-->

    <!--分割线-->
    <div class="mgt44"><img class="db c-b" src="/static/images/6.png" alt="分割线"></div>
    <!--分割线END-->

    <!--底部-->
    <main-bottom></main-bottom>
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
