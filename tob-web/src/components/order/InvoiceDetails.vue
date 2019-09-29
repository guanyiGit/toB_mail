<template>
    <!--发货单详情-->
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">货单详情</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 ovh bor-d">
        <div class="fl ww33 bor-r-d pdt48 pdb48 t-c">
          <p class="fs18 cor0 fb">货单号：{{havesentSingle&&havesentSingle.sendNo?havesentSingle.sendNo:''}}</p>
          <p class="fs16 cor0 mgt10">送货人：{{havesentSingle&&havesentSingle.sendName?havesentSingle.sendName:''}}</p>
          <p class="fs16 corFF8001 mgt10">{{havesentSingle&&havesentSingle.status==0?'派单':havesentSingle&&havesentSingle.status==1?'商品出库':havesentSingle&&havesentSingle.status==2?'运送中':havesentSingle&&havesentSingle.status==3?'已签收':''}}</p>
          <div class="mgt10 t-c">
            <a style="cursor:pointer;" class="dinb pdt5 pdb5 pdl15 pdr15 ras4 corw bgED145B">查看发票信息</a>
            <a style="cursor:pointer;" class="dinb pdt5 pdb5 pdl15 pdr15 ras4 cor9 bor9">打印送货单</a>
          </div>
        </div>
        <div class="fl ww67 pdt48">
          <div class="k1 h30 pdt12 pdb12">
            <div class="k1-1 fl rel ww25 curr"><span>1</span></div>
            <div class="k1-1 fl rel ww25 curr"><span>2</span></div>
            <div class="k1-1 fl rel ww25 curr"><span>3</span></div>
            <div class="k1-1 fl rel ww25"><span>4</span></div>
          </div>
          <div class="mgt10 ovh t-c k2">
            <div class="fl ww25">
              <p class="cor0">派单</p>
              <p class="cor9 mgt10" v-if="havesentSingle&&havesentSingle.sendSingleTime">{{havesentSingle.sendSingleTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
            </div>
            <div class="fl ww25">
              <p class="cor0">商品出库</p>
              <p class="cor9 mgt10" v-if="havesentSingle&&havesentSingle.outboundTime">{{havesentSingle.outboundTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
            </div>
            <div class="fl ww25">
              <p class="cor0">运送中</p>
              <p class="cor9 mgt10"></p>
            </div>
            <div class="fl ww25">
              <p class="cor0">签收</p>
              <p class="cor9 mgt10" v-if="havesentSingle&&havesentSingle.signClosedTime">{{havesentSingle.signClosedTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
            </div>
          </div>
        </div>
      </div>

      <div class="mgt30 bor-d">
        <p class="pdt15 pdb15 pdl30 cor0 bgf1">收货人信息</p>
        <div class="pdl30 pdr30">
          <p class="mgt20 cor0">收货人：<span class="cor9">{{havesentSingle&&havesentSingle.closedName?havesentSingle.closedName:''}}</span></p>
          <p class="mgt20 cor0">联系电话：<span class="cor9">{{havesentSingle&&havesentSingle.closedPhone?havesentSingle.closedPhone:''}}</span></p>
          <p class="mgt20 cor0">收货地址：<span class="cor9">{{havesentSingle&&havesentSingle.address?havesentSingle.address:''}}</span></p>
        </div>
      </div>

      <div class="mgt30">
        <div class="pdt15 pdb15 pdl30 cor0 bgf1" style="border: 1px solid #ddd;border-bottom: none;">商品信息</div>
        <div>
          <table class="table2 ww100 t-c">
            <tbody>
            <tr>
              <td class="cor0 pdt20 pdb20 w60"></td>
              <td class="cor0 pdt20 pdb20">订单号</td>
              <td class="cor0 pdt20 pdb20">商品编号</td>
              <td class="cor0 pdt20 pdb20">商品名称</td>
              <!--<td class="cor0 pdt20 pdb20">单位</td>-->
              <td class="cor0 pdt20 pdb20">订单量</td>
              <td class="cor0 pdt20 pdb20">实发量</td>
              <td class="cor0 pdt20 pdb20">未发量</td>
            </tr>
            <tr v-for="(item,index) in list1" :key="index">
              <td class="cor6 pdt20 pdb20">{{index+1}}</td>
              <td class="cor6 pdt20 pdb20">{{item.orderNo}}</td>
              <td class="cor6 pdt20 pdb20">{{item.goodsNo}}</td>
              <td class="cor6 pdt20 pdb20">{{item.goodsName}}</td>
              <!--<td class="cor6 pdt20 pdb20">台</td>-->
              <td class="cor6 pdt20 pdb20">{{item.goodsNum}}</td>
              <td class="cor6 pdt20 pdb20">{{item.receiveNum}}</td>
              <td class="cor6 pdt20 pdb20">{{item.goodsNum-item.receiveNum}}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="mgt30 bor-d">
        <p class="pdt15 pdb15 pdl30 cor0 bgf1">附件信息</p>
        <div class="pdl30 pdr30 pdl80">
          <ul class="ovh" v-if="imgs&&imgs.length>0">
            <li class="fl mgr20" v-for="item in imgs"><img :src="item.thumbnailUrl" alt=""></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import  conf from '../../common/config'
    export default {
        name: "InvoiceDetails",
      data(){
          return{
            havesentSingle:null,
            list1:[],//商品列表
            imgs:[],
            param:{
              supId:null,
              deliveryId:null,//发货记录表主键id
              sendId:null
            }
          }
      },
      created(){
        this.param.supId=this.$route.params.supId
        this.havesentSingle=JSON.parse(this.$route.params.havesentSingle)
        this.param.deliveryId=this.havesentSingle.id
        this.param.sendId=this.havesentSingle.closedId
        selectDeliveryGoods(this);
        if (this.havesentSingle.status==3) {
          selectOrderPhotos(this);
        }
      },
      methods:{
        returnn(){
          this.$router.push({name:'deliveryManage',params:{tabl:1}});
        }
      }
    }
    //查询送货管理列表已派单商品信息
    function selectDeliveryGoods(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectDeliveryGoods',
        params:  _this.param
      }).then((res) => {
        if (res.data.code == 200 && res.data.list!=null) {
          _this.list1=res.data.list
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
    //查询送货单的照片
    function selectOrderPhotos(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectOrderPhotos',
        params:  {deliveryId:_this.havesentSingle.id}
      }).then((res) => {
        if (res.data.code == 200 && res.data.list!=null) {
          _this.imgs=res.data.list
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
</script>

<style scoped>

</style>
