<template>
    <!--订单审批列表-->
  <!--内容区域-->
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">
    <p class="fs18 h28 lh28 cor0">订单审批</p>
    <ul class="ul003 mgt20">
      <li class="active" @click.prevent="checkTab = 0"><a style="cursor:pointer;">待审批</a></li>
      <li id="yi" @click.prevent="checkTab = 1"><a style="cursor:pointer;">已审批</a></li>
    </ul>

    <div class="mgt30">
      <ul class="ul0004">
        <!--待审核2-->
        <li class="db">
          <div class="ovh mgt20">
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms.input" @keyup.enter="selectMyOrderss">
                  <span class="input-group-btn" @click.prevent="selectMyOrderss">
											<button type="submit" class="btn bged145b">
												<i class="glyphicon glyphicon-search corw"></i>
											</button>
										</span>
                </div>
              </form>
            </div>
          </div>


          <div class="mgt30 ovh t-c bgf1">
            <p class="fl ww60 h50 lh50 cor0">商品</p>
            <p class="fl ww10 h50 lh50 cor0">单价</p>
            <p class="fl ww10 h50 lh50 cor0">数量</p>
            <p class="fl ww10 h50 lh50 cor0">金额</p>
            <!--<p class="fl ww10 h50 lh50 cor0">状态</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>

          <div v-for="(item,index) in list1" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <span class="fr fs12 cor6 aside3"><a href="javascript:void(0)" class="dinb cor0289E8 mgl20" @click="getDetail(item)">预算执行明细</a></span>
            </div>
            <table class="tab30" v-if="item.childOrders && item.childOrders.length>0">
              <tbody>
              <tr>
                <td class="ww80">
                  <table class="tab30-1" v-for="(e,index) in item.childOrders" :key="index">
                    <tbody>
                    <tr style="border-bottom: 1px dashed #ddd;">
                      <td class="pdt20 pdb20 pdl20 pdr20" colspan="3">
                        <div class="fs12 cor6 pdl20 pdr20 t-l">子订单号： {{e.orderNo}} <span class="cor0 mgl30">{{e.company}}</span>
                        </div>
                      </td>
                    </tr>
                    <tr v-for="(ee,index) in e.goodss" :key="index">
                      <td class="ww60 pdt20 pdb20 pdl20 pdr20">
                        <div class="ovh">
                          <div class="fl w80"><img :src="ee.imgurl" style="width: 90px;height: 90px"></div>
                          <div class="mgl99 t-l pdt15">
                            {{ee.goodsName}}
                          </div>
                          <p class="h16 lh16 cor9 mgt5" style="margin-top: 90px;margin-left: -400px">采购量：{{ee.purchaseNum}}</p>
                        </div>
                      </td>
                      <td class="ww10 pdt20 pdb20">￥{{ee.price}}</td>
                      <td class="ww10 pdt20 pdb20">{{ee.goodsNum}}</td>
                    </tr>
                    </tbody>
                  </table>
                </td>

                <td class="ww10 fb">￥{{item.totalAmount}}</td>
                <td class="ww10">
                  <router-link style="cursor:pointer;" class="db w60 btn btn-xs btn-success mgt10" :to="{name:'bm_auditApproval',params:{orderInfo:JSON.stringify(item),type:1,excess:isExcess}}">审批</router-link>
                </td>
              </tr>
              </tbody>
            </table>
            <!---->
            <table class="tab30" v-if="item.goodss && item.goodss.length>0">
              <tbody>
              <tr>
                <td class="ww80">
                  <table class="tab30-1">
                    <tbody>
                    <tr v-for="(itt,index) in item.goodss" :key="index">
                      <td class="ww60 pdt20 pdb20 pdl20 pdr20">
                        <div class="ovh">
                          <div class="fl w80"><img :src="itt.imgurl" style="width: 90px;height: 90px"></div>
                          <div class="mgl99 t-l pdt15">
                            {{itt.goodsName}}
                          </div>
                          <p class="h16 lh16 cor9 mgt5" style="margin-top: 90px;margin-left: -400px">采购量：{{itt.purchaseNum}}</p>
                        </div>
                      </td>
                      <td class="ww10 pdt20 pdb20">￥{{itt.price}}</td>
                      <td class="ww10 pdt20 pdb20">{{itt.goodsNum}}</td>
                    </tr>
                    </tbody>
                  </table>
                </td>

                <td class="ww10 fb">￥{{item.totalAmount}}</td>
                <td class="ww10">
                  <router-link style="cursor:pointer;" class="db w60 btn btn-xs btn-success mgt10" :to="{name:'bm_auditApproval',params:{orderInfo:JSON.stringify(item),type:1,excess:isExcess}}">审批</router-link>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
          </div>
          <!--分页END-->
        </li>

        <!--已审核-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms1.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms1.input" @keyup.enter="selectMyOrderss">
                  <span class="input-group-btn" @click.prevent="selectMyOrderss">
											<button type="submit" class="btn bged145b">
												<i class="glyphicon glyphicon-search corw"></i>
											</button>
										</span>
                </div>
              </form>
            </div>
          </div>


          <div class="mgt30 ovh t-c bgf1">
            <p class="fl ww50 h50 lh50 cor0">商品</p>
            <p class="fl ww10 h50 lh50 cor0">单价</p>
            <p class="fl ww10 h50 lh50 cor0">数量</p>
            <p class="fl ww10 h50 lh50 cor0">金额</p>
            <p class="fl ww10 h50 lh50 cor0">状态</p>
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list2" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
            </div>
            <table class="tab30" v-if="item.childOrders&&item.childOrders.length>0">
              <tbody>
              <tr>
                <td class="ww70">
                  <table class="tab30-1" v-for="(e,index) in item.childOrders" :key="index">
                    <tbody>
                    <tr style="border-bottom: 1px dashed #ddd;">
                      <td class="pdt20 pdb20 pdl20 pdr20" colspan="3">
                        <div class="fs12 cor6 pdl20 pdr20 t-l">子订单号： {{e.orderNo}} <span class="cor0 mgl30">{{e.company}}</span>
                        </div>
                      </td>
                    </tr>
                    <tr v-for="(ee,index) in e.goodss" :key="index">
                      <td class="ww50 pdt20 pdb20 pdl20 pdr20">
                        <div class="ovh">
                          <div class="fl w80"><img :src="ee.imgurl" style="width: 90px;height: 90px"></div>
                          <div class="mgl99 t-l pdt15">
                            {{ee.goodsName}}
                          </div>
                          <p class="h16 lh16 cor9 mgt5" style="margin-top: 90px;margin-left: -300px">采购量：{{ee.purchaseNum}}</p>
                        </div>
                      </td>
                      <td class="ww10 pdt20 pdb20">￥{{ee.price}}</td>
                      <td class="ww10 pdt20 pdb20">{{ee.goodsNum}}</td>
                    </tr>
                    </tbody>
                  </table>
                </td>

                <td class="ww10 fb">￥{{item.totalAmount}}</td>
                <td class="ww10 fb">{{item.status==1?'审核通过':item.status==2?'审核不通过':'审核通过'}}</td>
                <td class="ww10">
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,1)">查看详情</a><br>
                </td>
              </tr>
              </tbody>
            </table>
            <!---->
            <table class="tab30" v-if="item.goodss && item.goodss.length>0">
              <tbody>
              <tr>
                <td class="ww70">
                  <table class="tab30-1">
                    <tbody>
                    <tr v-for="(itt,index) in item.goodss">
                      <td class="ww50 pdt20 pdb20 pdl20 pdr20">
                        <div class="ovh">
                          <div class="fl w80"><img :src="itt.imgurl" style="width: 90px;height: 90px"></div>
                          <div class="mgl99 t-l pdt15">
                            {{itt.goodsName}}
                          </div>
                          <p class="h16 lh16 cor9 mgt5" style="margin-top: 90px;margin-left: -300px">采购量：{{itt.purchaseNum}}</p>
                        </div>
                      </td>
                      <td class="ww10 pdt20 pdb20">￥{{itt.price}}</td>
                      <td class="ww10 pdt20 pdb20">{{itt.goodsNum}}</td>
                    </tr>
                    </tbody>
                  </table>
                </td>

                <td class="ww10 fb">￥{{item.totalAmount}}</td>
                <td class="ww10 fb">{{item.status==1?'审核通过':item.status==2?'审核不通过':'审核通过'}}</td>
                <td class="ww10">
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,1)">查看详情</a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
          </div>
          <!--分页END-->
        </li>
      </ul>
    </div>

    <div class="zhezhao dn"></div>
    <div class="yschuang dn">
      <div class="ovh">
        <div class="fl mgr30">
          <p class="fl cor0 ios29 pdl33">预算执行情况总览</p>
        </div>
        <a href="javascript:void(0)" class="db fr cor0 hiddd">隐藏</a>
      </div>
      <div class="mgt10 table-responsive">
        <table class="tab80">
          <tbody>
          <tr >
            <td>科目</td>
            <td>本年度预算总额</td>
            <td>已用预算</td>
            <td>本月/季度可用预算</td>
            <!--<td>本年度已用预算</td>-->
            <td>本年度可用预算</td>
            <td>本月/季度计划进度</td>
            <td>实际执行进度</td>
            <td>状态</td>
          </tr>
          <tr v-for="(item,index) in budgetList">
            <td>{{item.subName}}</td>
            <td>{{item.yearAmount}}</td>
            <td>{{item.actualUse}}</td>
            <td>{{item.leftMonthOrSeasonAmount}}</td>
            <!--<td>{{item.actualUse}}</td>-->
            <td>{{item.leftYearAmount}}</td>
            <td>{{item.planMonthOrSeasonRate}}%</td>
            <td>{{item.actualMonthOrSeasonRate}}%</td>
            <td>{{(item.actualMonthOrSeasonRate-item.planMonthOrSeasonRate)>0?
              '超额'+(item.actualMonthOrSeasonRate-item.planMonthOrSeasonRate).toFixed(2)+'%':'未超额'}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>   <!--内容区域END-->
  </div>
  <!--内容区域END-->
</template>

<script>
  import  conf from '../../common/config'
  var _this = '';
    export default {
        name: "orderApproval",
      data(){
        return{
          checkTab:0,
          parms:{userId:null, orgId:null, status:3, input:null, month:null, num:1, size:2},
          parms1:{userId:null, orgId:null, status:44, input:null, month:36, num:1, size:2},
          parms2:{orgId:0, orderDate:'',subIdList:[]},
          list1:[],//待审核
          list2:[],//已审核
          budgetList:[],//预算列表
          pageData:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData1:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          isExcess:0,//是否超预算0未超 1超额
          namee:null
        }
      },
      mounted(){
        var $ul003= $('.ul003>li');
        $ul003.click(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $ul003.index(this);
          $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
        });
        $('.aside3').click(function(){
          $('.zhezhao').show();
          $('.yschuang').show();
        });
        $('.hiddd').click(function(){
          $('.zhezhao').hide();
          $('.yschuang').hide();
        });
        if (this.$route.params.tabl==1) {
          $("#yi").click();
        }
      },
      created(){
        // this.parms.userId=conf.userInfo.userId
        this.parms.orgId=conf.userInfo.orgId
        // this.parms1.userId=conf.userInfo.userId
        this.parms1.orgId=conf.userInfo.orgId
        this.parms2.orgId=conf.userInfo.orgId
        let roleIds=conf.userInfo.roles
        if (roleIds && roleIds.length == 1){
          if (roleIds[0].roleId==10) {
            this.namee="pal_orderApproval"
          }else {
            this.namee="bm_orderApproval"
          }
        }
        selectMyOrders(this);
      },beforeCreate(){
        _this = this;
      },
      methods:{
        setOrderInfo(item,tabl){
          sessionStorage.setItem('orderInfo',JSON.stringify(item));
          sessionStorage.setItem('namee','pal_orderApproval');
          sessionStorage.setItem('tabl',tabl);
          this.$router.push({name:'pal_orderDetail'});
        },
        selectMyOrderss(){
          this.parms.num=1
          this.parms1.num=1
          selectMyOrders(this);
        },
        chekdpage(currentPage) {
          this.parms.num = currentPage//页码
          selectMyOrders(this)
        },
        chekdpage1(currentPage) {
          this.parms1.num = currentPage//页码
          selectMyOrders(this)
        },
        getDetail:function (item) {
          $('.zhezhao').show();
          $('.yschuang').show();
          getDetail(item);
        },
        dateFormat:function(time) {
          var date=new Date(time);
          var year=date.getFullYear();
          /* 在日期格式中，月份是从0开始的，因此要加0
           * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
           * */
          var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
          var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
          var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
          var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
          var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
          // 拼接
          return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        }
      },
      watch:{
        checkTab(newVal,oldVal){
          if(newVal != oldVal) {
            this.parms.num=1
            this.parms1.num=1
            if (newVal==0) {
              selectMyOrders(this);
            }
            if (newVal==1) {
              selectMyOrders(this);
            }
          }
        }
      }
    }

    //列表
    function selectMyOrders(_this) {
      var parmm=_this.parms
      if (_this.checkTab==0) {
        parmm=_this.parms
      }
      if (_this.checkTab==1) {
        parmm=_this.parms1
      }
      console.log(7878)
      console.log(_this.parms1)
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectAuditApprovalOrders',
        params:  parmm
      }).then((res) => {
        console.log(res)
        if (res.data.code == 200 && res.data.list!=null) {
          if (_this.checkTab==0){
            _this.list1=res.data.list
            _this.pageData.curPage=_this.parms.num
            _this.pageData.totalCount=res.data.count
          }
          if (_this.checkTab==1){
            _this.list2=res.data.list
            _this.pageData1.curPage=_this.parms1.num
            _this.pageData1.totalCount=res.data.count
          }
        }
      }).catch((err)=>{
        console.log(err);
      });
    }

    //预算明细
    function getDetail(e) {
      _this.parms2.subIdList=[]
      _this.parms2.orderDate=e.createTime;
      _this.parms2.subIdList.push(e.subId)
      // if(e.isUseful==0 && e.childOrders!=null){
      //   var childOrders=e.childOrders;
      //   for(var i=0;i<childOrders.length;i++){
      //     var goodss=childOrders[i].goodss;
      //     if(goodss!=null){
      //       for(var k=0;k<goodss.length;k++){
      //         var index1=  $.inArray(goodss[k].subjects,_this.parms2.subIdList);
      //         if(index1<0){
      //           _this.parms2.subIdList.push(goodss[k].subjects);
      //         }
      //       }
      //     }
      //   }
      // }else{
      //   var goodss=e.goodss;
      //   if(goodss!=null){
      //     for(var j=0;j<goodss.length;j++){
      //       var index2=  $.inArray(goodss[j].subjects,_this.parms2.subIdList);
      //       if(index2<0){
      //         _this.parms2.subIdList.push(goodss[j].subjects);
      //       }
      //     }
      //   }
      // }
      _this.$axios({
        method: 'post',
        url: '/biz/tob-budget/biz/budget/budgetOrder',
        headers:{
          'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
        },
        data: JSON.stringify(_this.parms2)
      }).then((res) => {
        if(res.data.success){
          _this.budgetList= res.data.obj;
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
</script>

<style scoped>

</style>
