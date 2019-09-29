<template>
    <!--服务网点预约-->
  <div>
    <!--头-->
    <MainTop></MainTop>
    <!--服务网点预约-->
    <div class="w1200 c-b mgt40">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <img class="mgr8 db fl" src="../../../static/images/19.png" alt="">
          <span class="fs18 cor6a4a22 fb mgr30 db fl">预约网点</span>
          <!--<a href="###" class="db fl tab1 active mgr35">服务指南</a>-->
          <!--<a href="###" class="db fl tab1">服务网点</a>-->
        </div>
      </div>
      <img class="db mgt10" src="../../../static/images/23.png" alt="">
      <div class="pdt20">
        <div class="db tab2">
          <div class="bord">
            <form action="#">
              <p class="fs16 cor0 fb h60 lh60 bgf1 pdl30">预约信息</p>
              <div class="pdt10 pdb60 pdl30 pdr30">
                <div class="ovh">
                  <div class="mgt10 w550 fl">
                    <span class="db w90 fl h40 lh40 cor0">预约时间</span>
                    <div class="mgl90">
                      <input id="inp1" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc6">
                    </div>
                  </div>
                  <!--<script>-->
                  <!--;(function(){-->
                  <!--calender('#inp1').init(function(date){-->
                  <!--this.value= date-->
                  <!--});-->
                  <!--})();-->
                  <!--</script>-->
                  <div class="mgt10 w550 fr">
                    <span class="db w90 fl h40 lh40 cor0">预约业务</span>
                    <div class="mgl90">
                      <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="办证">
                    </div>
                  </div>
                </div>

                <div class="ovh">
                  <div class="mgt10 w550 fl">
                    <span class="db w90 fl h40 lh40 cor0">预约人</span>
                    <div class="mgl90">
                      <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    </div>
                  </div>
                  <div class="mgt10 w550 fr">
                    <span class="db w90 fl h40 lh40 cor0">联系电话</span>
                    <div class="mgl90">
                      <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    </div>
                  </div>
                </div>

                <div class="ovh">
                  <div class="mgt10">
                    <span class="db w90 fl h40 lh40 cor0">预约网点</span>
                    <div class="mgl90">
                      <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="瑞鹏宠物医院">
                    </div>
                  </div>
                </div>

                <div class="t-c mgt40">
                  <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="提交">
                  <a href="###" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!--服务网点预约END-->

    <div class="clearfix"></div>

    <!--友情链接-->
    <div class="mgt110 ovh w1200 c-b">
      <div class="fl w160 h72 pdl20 pdt17 bor-r">
        <p class="fs20 cor6b4a22 h22 lh22">友情链接</p>
        <p class="cor9 h16 lh16">Brand introduction</p>
      </div>
      <div class="mgl160">
        <ul class="ul07">
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">瑞鹏宠物中心</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">丰原宠物网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">全进宠物食品网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">瑞鹏宠物中心</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">狗狗网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">全进宠物食品网</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
          </li>
          <li class="fl">
            <a href="###" class="db h36 lh36 cor9">狗狗网</a>
          </li>
        </ul>
      </div>
    </div>
    <!--友情链接-->

    <!--分割线-->
    <div class="mgt110"><img class="db c-b" src="../../../static/images/6.png" alt="分割线"></div>
    <!--分割线END-->

    <!--底部-->
    <MainBottom></MainBottom>
  </div>
</template>

<script>
    export default {
        name: "bookingOutlets"
    }
</script>

<style scoped>

</style>
