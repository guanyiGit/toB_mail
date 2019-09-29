<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">订单详情</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 ovh bor-d">
        <div class="fs14 cor0 h36 lh36 bgf1 pdl30 bor-b-d">审核信息</div>
        <div class="ovh bor-b-d">
          <div class="fl ww33 bor-r-d pdt48 pdb48 t-c">
            <p class="fs18 cor0 fb">订单号：{{orderInfo&&orderInfo.orderNo?orderInfo.orderNo:''}}</p>
            <p class="fs16 corFF8001 mgt10">{{orderInfo&&orderInfo.status==0?'待审核':orderInfo&&orderInfo.status==2?'已驳回':orderInfo&&orderInfo.status==3?'待审批':orderInfo&&orderInfo.status==4?'待接单':orderInfo&&orderInfo.status==5?'已拒单':orderInfo&&orderInfo.status==6?'待发货':orderInfo&&orderInfo.status==7?'已发货':orderInfo&&orderInfo.status==8?'待评价':orderInfo&&orderInfo.status==9?'已评价':''}}</p>
            <div class="mgt10 t-c">
              <a style="cursor:pointer;" class="dinb pdt5 pdb5 pdl15 pdr15 ras4 corw bgED145B">查看发票信息</a>
            </div>
          </div>
          <div class="fl ww67 pdt48">
            <!--**********************

                      说明：进度只需要在.k1-1上添加类---curr

                      **********************-->

            <div class="k1 h30 pdt12 pdb12">
              <div class="k1-1 fl rel ww20 curr"><span>1</span></div>
              <div class="k1-1 fl rel ww20 curr"><span>2</span></div>
              <div class="k1-1 fl rel ww20 curr" v-if="orderInfo.status>=6"><span>3</span></div>
              <div class="k1-1 fl rel ww20" v-else><span>3</span></div>
              <div class="k1-1 fl rel ww20 curr" v-if="orderInfo.status>=7"><span>4</span></div>
              <div class="k1-1 fl rel ww20" v-else><span>4</span></div>
              <div class="k1-1 fl rel ww20 curr" v-if="orderInfo.status>=8"><span>4</span></div>
              <div class="k1-1 fl rel ww20" v-else><span>5</span></div>
            </div>
            <div class="mgt10 ovh t-c k2">
              <div class="fl ww20">
                <p class="cor0">提交订单</p>
                <p class="cor9 mgt10">{{orderInfo.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              </div>
              <div class="fl ww20">
                <p class="cor0">订单审核/审批</p>
                <p class="cor9 mgt10" v-if="approvalReview&&approvalReview.auditTime&&approvalReview.approvaTime==null">{{approvalReview.auditTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
                <p class="cor9 mgt10" v-if="approvalReview&&approvalReview.approvaTime">{{approvalReview.approvaTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              </div>
              <div class="fl ww20">
                <p class="cor0">已接单</p>
                <p class="cor9 mgt10"></p>
                <!--<p class="cor9 mgt10">2018-09-21 <br>12:34:45</p>-->
              </div>
              <div class="fl ww20">
                <p class="cor0">已发货</p>
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

      <!--提交订单---完成-->
      <div class="mgt30 pdt30" v-if="orderInfo.status>=1">
        <p class="h50 lh50 bgf1 pdl30 cor0">确认与审批信息</p>
        <div class="pdl30 pdt20 rel">
          <p class="xian"></p>
          <ul class="ul-2">
            <!--<li class="active">2018-09-22  12:45  李四审批通过审批意见：超额采购金额累加到下月</li>-->
            <li v-if="approvalReview&&approvalReview.reviewerId">{{approvalReview.auditTime | formatDate('yyyy-MM-dd HH:mm:ss')}} {{approvalReview.reviewerName}}{{orderInfo.status==2?'驳回订单':orderInfo.status>2?'审核通过':''}} 审核意见：{{approvalReview.auditOpinion}}</li>
            <li v-if="approvalReview&&approvalReview.approverId">{{approvalReview.approvaTime | formatDate('yyyy-MM-dd HH:mm:ss')}} {{approvalReview.approverName}}{{orderInfo.status==2?'驳回订单':orderInfo.status>3?'审批通过':''}} 审批意见：{{approvalReview.approvalOpinion}}</li>
          </ul>
        </div>
      </div>
      <!--提交订单---完成  END-->

      <div class="mgt30">
        <table class="ww100 t-c bor-d">
          <thead>
          <tr>
            <td class="bgf1 h36 lh36 ww55" colspan="2">商品</td>
            <td class="bgf1 h36 lh36">单价</td>
            <td class="bgf1 h36 lh36">数量</td>
            <td class="bgf1 h36 lh36">金额</td>
          </tr>
          </thead>
          <tbody v-for="(item,index) in goodss">
          <tr>
            <td colspan="2" class="t-l">
              <div class="ovh pdl30 pdt20 pdb20 fl">
                <div class="fl w80"><img :src="item.imgurl" style="width: 90px;height: 90px"></div>
                <div class="mgl99">
                  <p class="h16 lh16 elp1">{{item.goodsName}}</p>
                  <!--<p class="h16 lh16 mgt5 elp1">商用笔记本i5-7200U 4G 256GSSD FHD 三年保修 银色</p>-->
                  <p class="h16 lh16 cor9 mgt5">商品编号：{{item.goodsNo}}</p>
                </div>
              </div>
            </td>
            <td>￥{{item.price}}</td>
            <td>{{item.goodsNum}}</td>
            <td>￥{{item.price*item.goodsNum}}</td>
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
                    <td v-if="orderInfo.status>6 && orderInfo.status<10"></td>
                  </tr>
                  <tr v-for="(e,index) in item.consignees" :key="index">
                    <td>{{e.name}}</td>
                    <td>{{e.phone}}</td>
                    <td>{{e.address}}</td>
                    <td>{{e.num}}</td>
                    <td>{{e.deliveryTime | formatDate('yyyy-MM-dd')}}</td>
                    <td>{{e.installTime | formatDate('yyyy-MM-dd')}}</td>
                    <td v-if="orderInfo.status>6 && orderInfo.status<10">
                      <router-link v-if="orderInfo.status==7" style="cursor:pointer;" class="btn w55 btn-xs btn-success" :to="{name:'suoghuo',params:{'orderId':orderInfo.orderId}}">送货信息</router-link>
                      <router-link v-if="orderInfo.status>7 && orderInfo.status<10" style="cursor:pointer;" class="btn w55 btn-xs btn-primary" :to="{name:'anzhuang',params:{'orderId':orderInfo.orderId}}">安装信息</router-link>
                      <router-link v-if="orderInfo.status>7 && orderInfo.status<10" style="cursor:pointer;" class="btn w55 btn-xs btn-warning" :to="{name:'feiyong',params:{'orderId':orderInfo.orderId}}">附加</router-link>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </td>
          </tr>
          </tbody>
          <tfoot>
          <tr>
            <td colspan="6" class="pdl20 pdr20 pdt20 pdb20 fs16 cor0 fb t-r bor-t-d2">
              <p>商品总额：￥{{orderInfo.totalAmount}}</p>
              <p v-if="orderInfo.status==8 || orderInfo.status==9">附加费用：￥{{money}}</p>
              <p v-if="orderInfo.status==8 || orderInfo.status==9">总金额：￥{{orderInfo.totalAmount+money}}</p>
            </td>
          </tr>
          </tfoot>
        </table>
      </div>


    </div>
  </div>
</template>

<script>
    export default {
        name: "orderDetail",
      data(){
          return{
            orderInfo:null,//订单信息
            goodss:[],//商品集合
            approvalReview:null,//审核审批信息
            money:0.00,//附加费用
            params:{
              pageNum:1,
              limit:100,
              totalCount:0
            },
            namee:null,//返回时判断跳哪个页面
            tabl:null//返回后是哪个标签页
          }
      },
      created(){
          // this.namee=this.$route.params.namee
          // this.tabl=this.$route.params.tabl
          // this.orderInfo=JSON.parse(this.$route.params.orderInfo);
        this.namee=sessionStorage.getItem("namee")
        this.tabl=sessionStorage.getItem("tabl")
        this.orderInfo=JSON.parse(sessionStorage.getItem("orderInfo"))
        console.log(this.namee)
        console.log(this.tabl)
        console.log(this.orderInfo)
        selectApprovalReview(this);
        // selectAdditionalFee(this);
          if (this.orderInfo.goodss!=null&&this.orderInfo.goodss.length>0){
            this.goodss=this.orderInfo.goodss
          }
          if (this.orderInfo.childOrders!=null&&this.orderInfo.childOrders.length>0) {
            for (var i=0;i<this.orderInfo.childOrders.length;i++) {
              if (this.orderInfo.childOrders[i].goodss!=null&&this.orderInfo.childOrders[i].goodss.length>0){
                for (var j=0;j<this.orderInfo.childOrders[i].goodss.length;j++) {
                  this.goodss.push(this.orderInfo.childOrders[i].goodss[j]);
                }
              }
            }
          }
          for (var i=0;i<this.goodss.length;i++){
            selectReceivingRecords(this,this.goodss[i].goId);
          }
        this.$axios.get("/biz/tob-order/order/details/fy/"+this.orderInfo.orderId,this.params)
          .then(res=>{
            if(res.data.success){
              var list = res.data.obj.list;
              for (var i=0;i<list.length;i++) {
                this.money+=list[i].price
              }
            }
          })
      },
      methods:{
          returnn(){
            // this.$router.back(-1);
            this.$router.push({name:this.namee,params:{tabl:this.tabl}});
          }
      }
    }
    //查询送货信息
  function selectReceivingRecords(_this,ogid) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-order/biz/order/selectReceivingRecords',
      params:  {ogId:ogid}
    }).then((res) => {
      if (res.data.code == 200 && res.data.list!=null) {
        for (var i=0;i<_this.goodss.length;i++) {
          if (_this.goodss[i].goId==ogid) {
            _this.goodss[i].consignees=res.data.list
          }
        }
        _this.consignelist=res.data.list
      }
    }).catch((err)=>{
      console.log(err);
    });
  }

    //查询订单审核审批信息
    function selectApprovalReview(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectApprovalReview',
        params:  {orderId:_this.orderInfo.orderId}
      }).then((res) => {
        if (res.data.code == 200 && res.data.obj!=null) {
          _this.approvalReview=res.data.obj
        }
      }).catch((err)=>{
        console.log(err);
      });
    }

    //查询附加费用
    // function selectAdditionalFee(_this) {
    //   _this.$axios({
    //     method: 'get',
    //     url: '/biz/tob-order/biz/order/selectAdditionalFee',
    //     params:  {orderId:_this.orderInfo.orderId}
    //   }).then((res) => {
    //     if (res.data.code == 200 && res.data.money!=null) {
    //       _this.money=res.data.money
    //     }
    //   }).catch((err)=>{
    //     console.log(err);
    //   });
    // }
</script>

<style scoped>

</style>
