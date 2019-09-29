<template>
  <!--头部END-->
  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">审核订单</p>
        <a href="javascript:" @click="$router.back(-1)" class="db fr fs18 cor0">返回>></a>
      </div>
      <div class="mgt20 ovh bor-d">
        <div class="fs14 cor0 h36 lh36 bgf1 pdl30 bor-b-d">审核信息</div>
        <div class="ovh bor-b-d">
          <div class="fl ww33 bor-r-d pdt48 pdb48 t-c">
            <p class="fs18 cor0 fb">订单号：{{orderDetail&&orderDetail.order_no?orderDetail.order_no:''}}</p>
            <p class="fs16 corFF8001 mgt10" v-if="orderDetail&&orderDetail.status&&orderDetail.status==4">待接单</p>
            <p class="fs16 corFF8001 mgt10" v-if="orderDetail&&orderDetail.status&&orderDetail.status==5">已拒单</p>
            <p class="fs16 corFF8001 mgt10" v-if="orderDetail&&orderDetail.status&&orderDetail.status==6">已接单</p>
            <div class="mgt10 t-c">
              <a href="javascript:" class="dinb pdt5 pdb5 pdl15 pdr15 ras4 corw bgED145B">查看发票信息</a>
            </div>
          </div>
          <div class="fl ww67 pdt48">
            <!--**********************

                      说明：进度只需要在.k1-1上添加类---curr

                      **********************-->
            <div class="k1 h30 pdt12 pdb12">
              <div class="k1-1 fl rel ww20 curr"><span>1</span></div>
              <div class="k1-1 fl rel ww20 "><span>2</span></div>
              <div class="k1-1 fl rel ww20 "><span>3</span></div>
              <div class="k1-1 fl rel ww20"><span>4</span></div>
              <div class="k1-1 fl rel ww20"><span>5</span></div>
            </div>

            <div class="mgt10 ovh t-c k2">
              <div class="fl ww20">
                <p class="cor0">提交订单</p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
              <div class="fl ww20">
                <p class="cor0">订单审核/审批</p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
              <div class="fl ww20">
                <p class="cor0">商品出库</p>
                <p class="cor9 mgt10"></p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
              <div class="fl ww20">
                <p class="cor0">等待收货</p>
                <p class="cor9 mgt10"></p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
              <div class="fl ww20">
                <p class="cor0">完成</p>
                <p class="cor9 mgt10"></p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mgt30">
        <table class="ww100 t-c bor-d">
          <!--<thead>
            <tr>
            <td class="bgf1 h36 lh36 ww55" colspan="2">商品</td>
            <td class="bgf1 h36 lh36">单价</td>
            <td class="bgf1 h36 lh36">数量</td>
            <td class="bgf1 h36 lh36">金额</td>
            </tr>
          </thead>-->
          <tbody v-for="item in orderDetail.list">
          <tr>
            <td class="bgf1 h36 lh36 ww55" colspan="2">商品</td>
            <td class="bgf1 h36 lh36">单价</td>
            <td class="bgf1 h36 lh36">数量</td>
            <td class="bgf1 h36 lh36">金额</td>
          </tr>
          <tr>
            <td colspan="2" class="t-l">
              <div class="ovh pdl30 pdt20 pdb20 fl">
                <div class="fl w80"><img src="images/11.png" alt=""></div>
                <div class="mgl90">
                  <p class="h16 lh16">
                    <span v-for="item1 in item.skuList">{{item1.attr_value}}</span>
                  </p>
                  <!--<p class="h16 lh16 mgt5">商用笔记本i5-7200U 4G 256GSSD FHD 三年保修 银色</p>
                  <p class="h16 lh16 cor9 mgt5">商品编号：ASDF123</p>-->
                </div>
              </div>
            </td>
            <td>￥{{item.price}}</td>
            <td>{{item.total_num}}</td>
            <td>￥{{item.total_num*item.price}}</td>
            <td></td>
          </tr>
          <tr>
            <td colspan="6" class="pdl20 pdr20 bor-b-d">
              <div class=" bor-t-d pdt20 pdb20">
                <table class="table table-bordered">
                  <tbody>
                  <tr>
                    <td>收货人</td>
                    <td>联系电话</td>
                    <td>收货地址</td>
                    <td>送货数量</td>
                    <td>期望送货时间</td>
                    <td>期望安装时间</td>
                  </tr>
                  <tr v-for="item2 in item.receiptList">
                    <td>{{item2.name}}</td>
                    <td>{{item2.phone}}</td>
                    <td>{{item2.address}}</td>
                    <td>{{item2.num}}</td>
                    <td>{{item2.delivery_time|formatDate("yyyy-MM-dd")}}</td>
                    <td>{{item2.install_time| formatDate("yyyy-MM-dd")}}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="6" class="pdl20 pdr20 pdt20 pdb20 fs16 cor0 fb t-r bor-t-d2">商品总额：￥{{orderDetail.money}}</td>
          </tr>
          </tbody>
        </table>
      </div>


    </div>
    <!--分页-->
    <!--<div class="fen c-b ovh mgt30 t-c w410">
          <a href="###" class="db fl fs14 cor0">1</a>
          <a href="###" class="db fl fs14 cor0 active">2</a>
          <a href="###" class="db fl fs14 cor0">3</a>
          <a href="###" class="db fl fs14 cor0">4</a>
          <a href="###" class="db fl fs14 cor0">5</a>
          <a href="###" class="db fl fs14 cor0"><img src="images/7.png" alt=""></a>
          <a href="###" class="db fl fs14 cor0">99</a>
          <a href="###" class="db fl fs14 cor0 curr"></a>
          <a href="###" class="db fl fs14 cor0"></a>
      </div>-->
    <!--分页END-->
  </div>
</template>

<script>
  export default {
    name: "supplierOrderDetail",
    data(){
      return{
        orderState:0,//订单状态
        orderId:0//订单id
        ,orderDetail:''//订单详情
      }
    },
    created(){

      if(this.$route.params&&this.$route.params.type){
        this.orderState=this.$route.params.type
      }
      if(this.$route.params&&this.$route.params.id){
        this.orderId=this.$route.params.id
      }
      console.log("订单id"+this.orderId+"订单状态4待接单5已拒单6已接单"+this.orderState)
      getOrderDetail(this)
    },mounted(){
      var $ul003= $('.ul003>li');
      $ul003.mouseover(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var i= $ul003.index(this);
        $('.ul004>li').eq(i).show().siblings('.ul004>li').hide();
      });
    },
  }
  //得到订单详情
  function getOrderDetail(_this){
    _this.$axios({
      url:'/biz/tob-supplier/com/supplier/getOrderDetail'
      ,params:{'id':_this.orderId}
    }).then((ref)=>{
      if(ref.data.code==200){
        _this.orderDetail=ref.data.obj;
        console.log("订单详情"+JSON.stringify(_this.orderDetail))
      }
    })
  }
</script>

<style scoped>

</style>
