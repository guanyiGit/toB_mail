<template>
    <!--审核审批-->
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">{{type==0?'审核':'审批'}}订单</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 ovh bor-d">
        <div class="fs14 cor0 h36 lh36 bgf1 pdl30 bor-b-d">{{type==0?'审核':'审批'}}信息</div>
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
                <!--<p class="cor9 mgt10" v-if="approvalReview&&approvalReview.auditTime&&approvalReview.approvaTime==null">{{approvalReview.auditTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>-->
                <!--<p class="cor9 mgt10" v-if="approvalReview&&approvalReview.approvaTime">{{approvalReview.approvaTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>-->
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

        <div class="pdt30 pdb50 pdl30 pdr30">
          <div class="ovh">
            <p class="fl mgr30"><label class="lab3"><input name="asdf" type="radio" v-model="check" value="0"><i></i>{{type==0?'审核':'审批'}}通过</label></p>
            <p class="fl"><label class="lab3"><input name="asdf" type="radio" v-model="check" value="1"><i></i>{{type==0?'审核':'审批'}}不通过</label></p>
          </div>
          <div class="mgt30">
            <textarea class="db ww100 h140 pdt20 pdb20 pdl20 pdr20 bor-d" placeholder="审核意见" v-model="opinion"></textarea>
          </div>
          <div class="mgt40 t-c">
            <button type="submit" class="btn dinb w120 btn-primary" @click="auditOrder">提交</button>
            <button type="button" class="btn dinb w120 btn-danger" @click="returnn">取消</button>
          </div>
        </div>
      </div>


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
                    <!--<td></td>-->
                  </tr>
                  <tr v-for="(e,index) in item.consignees" :key="index">
                    <td>{{e.name}}</td>
                    <td>{{e.phone}}</td>
                    <td>{{e.address}}</td>
                    <td>{{e.num}}</td>
                    <td>{{e.deliveryTime | formatDate('yyyy-MM-dd')}}</td>
                    <td>{{e.installTime | formatDate('yyyy-MM-dd')}}</td>
                    <!--<td>-->
                    <!--<a href="#" class="btn w55 btn-xs btn-success">送货信息</a>-->
                    <!--<a href="#" class="btn w55 btn-xs btn-primary">安装信息</a>-->
                    <!--<a href="#" class="btn w55 btn-xs btn-warning">附加</a>-->
                    <!--</td>-->
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
              <!--<p>附加费用：￥16695.00</p>-->
              <!--<p>总金额：￥16695.00</p>-->
            </td>
          </tr>
          </tfoot>
        </table>
      </div>


    </div>
  </div>
</template>

<script>
  import  conf from '../../common/config'
    export default {
        name: "auditApproval",
      data(){
        return{
          orderInfo:null,//订单信息
          goodss:[],//商品集合
          type:null,//判断是审核还是审批
          isExcess:0,//是否超预算0未超 1超额
          opinion:null,//意见
          check:null,//0通过 1驳回
          parm:{//审核审批参数
            orderId:null,//订单id
            status:null,//订单状态
            reviewerId:1,//审核人id
            approverId:null,//审批人id
            auditOpinion:null,//审核意见
            approvalOpinion:null,//审批意见
            type:null//审核审批类型0审核1审批
          },
          budgetList:[],//预算列表
          parms2:{orgId:0, orderDate:'',subIdList:[]},
        }
      },
      created(){
        this.parms2.orgId=conf.userInfo.orgId
        this.orderInfo=JSON.parse(this.$route.params.orderInfo);
        this.type=JSON.parse(this.$route.params.type);
        this.parm.type=JSON.parse(this.$route.params.type);
        // this.isExcess=JSON.parse(this.$route.params.excess);
        this.parm.orderId=this.orderInfo.orderId
        if (this.type==0) {
          this.parm.reviewerId=conf.userInfo.userId
        }else {
          this.parm.approverId=conf.userInfo.userId
        }
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
          //------------------------------------------------------------------------
        var e=this.orderInfo
          this.parms2.orderDate=e.createTime;
          if(e.isUseful==0 && e.childOrders!=null){
            var childOrders=e.childOrders;
            for(var i=0;i<childOrders.length;i++){
              var goodss=childOrders[i].goodss;
              if(goodss!=null){
                for(var k=0;k<goodss.length;k++){
                  var index1=  $.inArray(goodss[k].subjects,this.parms2.subIdList);
                  if(index1<0){
                    this.parms2.subIdList.push(goodss[k].subjects);
                  }
                }
              }
            }
          }else{
            var goodss=e.goodss;
            if(goodss!=null){
              for(var j=0;j<goodss.length;j++){
                var index2=  $.inArray(goodss[j].subjects,this.parms2.subIdList);
                if(index2<0){
                  this.parms2.subIdList.push(goodss[j].subjects);
                }
              }
            }
          }
          this.$axios({
            method: 'post',
            url: '/biz/tob-budget/biz/budget/budgetOrder',
            headers:{
              'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
            },
            data: JSON.stringify(this.parms2)
          }).then((res) => {
            if(res.data.success){
              this.budgetList= res.data.obj;
              for (var i=0;i<this.budgetList.length;i++) {
                if (this.budgetList[i].actualMonthOrSeasonRate-this.budgetList[i].planMonthOrSeasonRate>0) {
                  this.isExcess=1
                }
              }
            }
          }).catch((err)=>{
            console.log(err);
          });
          //----------------------------------------------------------------------
        for (var i=0;i<this.goodss.length;i++){
          selectReceivingRecords(this,this.goodss[i].goId);
        }
      },
      methods:{
        returnn(){
          this.$router.back(-1);
        },
        //审核审批
        auditOrder(){
          auditOrderr(this);
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
    //审核审批
    function auditOrderr(_this) {
    if (_this.check==null) {
      _this.$alert("请选择通过或不通过！")
      return
    }
      if (_this.check==1){
        _this.parm.status=2
      }
      if (_this.isExcess==1) {
        if (_this.check==0) {
          if (_this.type==0) {
            _this.parm.status=3
          }else {
            _this.parm.status=4
          }
        }
      }
      if (_this.isExcess==0) {
        if (_this.check==0) {
          _this.parm.status=4
        }
      }
      if (_this.type==0) {
        _this.parm.auditOpinion=_this.opinion
      }
      if (_this.type==1) {
        _this.parm.approvalOpinion=_this.opinion
      }
      console.log(111)
      console.log(_this.parm)
      _this.$axios({
        method: 'post',
        headers:{
          'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
        },
        url: '/biz/tob-order/biz/order/auditOrder',
        data:  JSON.stringify(_this.parm)
      }).then((res) => {
        if (res.data.code == 200 && res.data.string=="提交审核成功") {
          _this.$alert("审核提交成功！").then(_ => {
            _this.$router.back(-1);
          }).catch(_ => {
          });
        }else {
          _this.$alert("审核提交失败！")
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
</script>

<style scoped>

</style>
