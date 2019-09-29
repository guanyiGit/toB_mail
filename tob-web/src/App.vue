<template>
  <!--wrap-->
  <div class="ww100 pdt241"><!--这个div去掉了类wrap2-->
    <router-view></router-view>
  </div>
</template>

<script>

  import '../static/css/swiper.css'
  import '../static/css/css.css'
  // import '../static/css/calendar.css'
  // import '../static/css/RL.css'
  import '../static/css/bootstrap.min.css'
  import '../static/css/style.css'

  export default {
    name: 'App'
  }
</script>
<style>
  .curpoint {
    cursor: pointer;
  }

  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
    height: 41px;
  }

  .w980 .mbx {
    margin-bottom: 15px;
    font-size: 15px;
  }

  .w980 .mbx a:hover {
    color: #3556CC;
    cursor: pointer;
  }
  .el-pagination__editor.el-input .el-input__inner{
    height: 21px;
    width: 40px;
  }
  .cursor{
    cursor: pointer;
  }
</style>
