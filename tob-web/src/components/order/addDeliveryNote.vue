<template>
    <!--新增送货单-->
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新建送货单</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w">
          <form action="#">
            <div class="mgt30 bor-d">
              <p class="h50 lh50 bgf1 pdl30 pdr30 cor0">送货人信息</p>
              <div class="pdt20 pdb20 pdl30 pdr30">
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">送货单号：</span>{{params.sendNo}}</p>
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">送货员：</span>
                  <select id="consignee" class="h40 lh40 bor-d w160 ios0" @change="selectName">
                  <option>选择送货人</option>
                  <option v-for="item in list1" v-bind:value="item.id">{{item.name}}</option>
                </select>
                </p>
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">联系电话：</span>{{phone}}</p>
              </div>
            </div>
            <div class="mgt30 bor-d">
              <p class="h50 lh50 bgf1 pdl30 pdr30 cor0">收货人信息</p>
              <div class="pdt20 pdb20 pdl30 pdr30">
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">收货人：</span>{{googss[0].recipientName}}</p>
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">联系电话：</span>{{googss[0].recipientPhone}}</p>
                <p class="cor9 h40 lh40"><span class="dinb w70 cor0">地址：</span>{{googss[0].recipientAddress}}</p>
              </div>
            </div>
            <div class="mgt30 bor-d">
              <p class="h50 lh50 bgf1 pdl30 pdr30 cor0">商品信息</p>
              <div class="pdt20 pdb20 pdl30 pdr30">
                <table class="tab3">
                  <tbody>
                  <tr>
                    <td class="pdt20 pdb20 w50"></td>
                    <td class="pdt20 pdb20">订单号</td>
                    <td class="pdt20 pdb20">商品编号</td>
                    <td class="pdt20 pdb20">商品名称</td>
                    <!--<td class="pdt20 pdb20">单位</td>-->
                    <td class="pdt20 pdb20">订单量</td>
                    <td class="pdt20 pdb20">本次发货量</td>
                    <td class="pdt20 pdb20">未发量</td>
                  </tr>
                  <tr v-for="(item,index) in googss" :key="index">
                    <td class="pdt20 pdb20">{{index+1}}</td>
                    <td class="pdt20 pdb20">{{item.orderNo}}</td>
                    <td class="pdt20 pdb20">{{item.goodsNo}}</td>
                    <td class="pdt20 pdb20">{{item.goodsName}}</td>
                    <!--<td class="pdt20 pdb20">台</td>-->
                    <td class="pdt20 pdb20">{{item.goodsNum}}</td>
                    <td class="pdt20 pdb20" style="width: 200px">
                      <div class="i_box fl" style="width: 90px;height: 40px;border: 1px solid #ddd;margin-left: 50px">
                        <a href="javascript:;" class="J_add hov7" style="width: 10px;height: 13px;top: -4px;right: 13px;">
                          <img src="/static/images/25-1.png" class="imi" alt="" @click="addd(item.deliveryNum,item.goodsNum-item.receiveNum,item.skuId,item.goId)">
                        </a>
                        <input type="text" value="0" v-model="item.deliveryNum" class="J_input t-l" style="width: 58px; height: 36px;line-height: 36px;text-align: left; border: none;" @change="changee(item.deliveryNum,item.goodsNum-item.receiveNum,item.skuId,item.goId)">
                        <a href="javascript:;" class="J_minus hov7" style="width: 10px;height: 13px;right: 13px;bottom: 3px;">
                          <img src="/static/images/25-2.png" class="imi" alt="" @click="redd(item.deliveryNum,item.skuId,item.goId)">
                        </a>
                      </div>
                      <!--<input type="text" style="width: 80px" v-model="item.deliveryNum" @change="judge(item.deliveryNum,item.goodsNum-item.receiveNum)">-->
                    </td>
                    <td class="pdt20 pdb20">{{item.goodsNum-item.receiveNum}}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="mgt40 t-c">
              <input type="button" class="w100 btn btn-primary" value="提交" @click="submitt">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="returnn">
            </div>
          </form>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import  conf from '../../common/config'
  import $ from 'jquery'
    export default {
        name: "addDeliveryNote",
      data(){
          return {
            list1:[],//送货人
            phone:null,
            params:{
              sendNo:null,//送货单号
              sendId:null,//送货人id
              userId:null,//订单创建人id
              supId:null,//创建人所属供应商id
              recipientId:null,//收货人id
              deliverysDetails:[]
            },
            googss:[]
          }
      },
      created(){
        this.params.userId=conf.userInfo.userId
        this.params.supId=this.$route.params.supId
        this.googss=JSON.parse(this.$route.params.goodss);
        this.params.recipientId=this.googss[0].recipientId
        for (var i=0;i<this.googss.length;i++) {
          this.googss[i].deliveryNum=0
        }
        generateDeliveryNo(this);
        selectDeliveryMans(this);
      },
      methods:{
        selectName(){//选择送货人
          this.params.sendId=$("#consignee").val();
          for (var i=0;i<this.list1.length;i++) {
            if (this.list1[i].id==$("#consignee").val()) {
              this.phone=this.list1[i].phone
            }
          }
        },
        returnn(){
          this.$router.back(-1);
        },
        submitt(){
          if (!this.params.sendId) {
            this.$alert("请选择送货人！")
            return
          }
          this.params.deliverysDetails=[]
          for (var i=0;i<this.googss.length;i++) {
            if (/^[1-9]\d*$/g.test(this.googss[i].deliveryNum)){
              if (this.googss[i].deliveryNum>this.googss[i].goodsNum-this.googss[i].receiveNum) {
                this.googss[i].deliveryNum=this.googss[i].goodsNum-this.googss[i].receiveNum
              }
            }else {
              this.$alert("本次发货量请填入正整数！")
              return
            }
            var obj={
            orderId:this.googss[i].orderId,//订单id
            ogId:this.googss[i].goId,
            realNum:this.googss[i].deliveryNum//发货数量
            }
            this.params.deliverysDetails.push(obj)
          }
          console.log(this.params)
          this.$axios({
            method: 'post',
            headers:{
              'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
            },
            url: '/biz/tob-order/biz/order/addInvoice',
            data: JSON.stringify(this.params)
          }).then((res) => {
            if (res.data.code == 200 && res.data.string=="新增发货单成功") {
              this.$alert("新增发货单成功！").then(_ => {
                this.$router.back(-1);
              }).catch(_ => {
              });
            }
          }).catch((err)=>{
            console.log(err);
          });
        },
        addd(real,not,skuId,goId){//加数量
          if (real<not) {
          let googs=[]
          for (var i=0;i<this.googss.length;i++) {
            googs.push(this.googss[i])
          }
          this.googss=[]
            for (var i=0;i<googs.length;i++) {
              if (skuId==googs[i].skuId && goId==googs[i].goId) {
                googs[i].deliveryNum++
              }
            }
          this.googss=googs
          }
        },
        redd(real,skuId,goId){//减数量
          if (real>0) {
            let googs=[]
            for (var i=0;i<this.googss.length;i++) {
              googs.push(this.googss[i])
            }
            this.googss=[]
            for (var i=0;i<googs.length;i++) {
              if (skuId==googs[i].skuId && goId==googs[i].goId) {
                googs[i].deliveryNum--
              }
            }
            this.googss=googs
          }
        },
        changee(real,not,skuId,goId){
          if (/^[1-9]\d*$/g.test(real)){
            if (real>not) {
              let googs=[]
              for (var i=0;i<this.googss.length;i++) {
                googs.push(this.googss[i])
              }
              this.googss=[]
              for (var i=0;i<googs.length;i++) {
                if (skuId==googs[i].skuId && goId==googs[i].goId) {
                  googs[i].deliveryNum=not
                }
              }
              this.googss=googs
            }
          }else {
            let googs=[]
            for (var i=0;i<this.googss.length;i++) {
              googs.push(this.googss[i])
            }
            this.googss=[]
            for (var i=0;i<googs.length;i++) {
              if (skuId==googs[i].skuId && goId==googs[i].goId) {
                googs[i].deliveryNum=0
              }
            }
            this.googss=googs
          }
        }
      }
    }
    //查询送货人信息
    function selectDeliveryMans(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectDeliveryMans',
        params: {supId:conf.userInfo.orgId}
      }).then((res) => {
        console.log(res)
        if (res.data.code == 200 && res.data.list!=null) {
          _this.list1=res.data.list
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
  //生成送货单号
  function generateDeliveryNo(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-order/biz/order/generateDeliveryNo'
    }).then((res) => {
      if (res.data!=null) {
        _this.params.sendNo=res.data
      }
    }).catch((err)=>{
      console.log(err);
    });
  }
</script>

<style scoped>
  select{
    width: 150px;
    padding: 0 2%;
    margin: 0;
  }
  option{
    text-align:center;
  }
</style>
