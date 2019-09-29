<template>
  <div class="wrap2 ww100 pdt191" style="margin-top: -150px">


    <div class="ww100 pdl15 pdr15 pdb130">
      <form action="#">
        <div class="w1200 c-b mm100 mgt30">
          <div class="ovh">
            <div class="fl w230"><p class="fs24 corED145B pdl45 ios17">订单结算</p></div>
            <!--<div class="fr w320 fs24 corED145B t-r">本月可用预算：¥83500.00</div>-->
            <div class="h-m2">
              <div class="k1 h30 pdt12 pdb12">
                <div class="k1-1 fl rel ww33 curr"><span>1</span></div>
                <div class="k1-1 fl rel ww33 curr"><span>2</span></div>
                <div class="k1-1 fl rel ww33"><span>3</span></div>
              </div>
              <div class="mgt10 ovh t-c k2">
                <div class="fl ww33">
                  <p class="cor0">我的购物车</p>
                </div>
                <div class="fl ww33">
                  <p class="cor0">核对结算信息</p>
                </div>
                <div class="fl ww33">
                  <p class="cor0">结算成功</p>
                </div>
              </div>
            </div>
          </div>

          <div class="mgt30">
            <div class="bgf1 h56 lh56 pdl20 pdr20 ovh">
              <p class="fl cor0">商品信息</p>
              <router-link class="fr cor00C2FF" :to="{name:'cartManage',params : {ids : ids,selSubjectId : selSubjectId}}">返回购物车</router-link>
            </div>
            <div v-for="item in cartRecords">
              <div class="h56 lh56 pdl20 pdr20 t-l td-l">
                <p class="corED145B">供应商：{{item.supplierDO.supName}}</p>
              </div>

              <div>
                <div  v-for="e in item.cardDOList" class="bor-d pdb20 pdl20 pdr20">
                  <div>
                    <table class="ww100 t-c">
                      <tbody>
                      <tr>
                        <td colspan="3" class="t-l">
                          <div class="ovh pdl30 pdt20 pdb20 fl">
                            <div class="fl w80"><img :src="e.thumbnailUrl" alt=""></div>
                            <div class="mgl90">
                              <p class="h16 lh16">{{e.attrName}}</p>
                              <!--<p class="h16 lh16 mgt5">商用笔记本i5-7200U 4G 256GSSD FHD 三年保修 银色</p>-->
                              <!--<p class="h16 lh16 cor9 mgt5">商品编号：ASDF123</p>-->
                            </div>
                          </div>
                        </td>
                        <td><span style="color: #DC143C;font-size:14px" v-if="e.isMsgShow">送货数量有误</span></td>
                        <td>￥{{e.cardDO.price}}</td>
                        <td>{{e.cardDO.count}}</td>
                        <td>￥{{e.cardDO.price*e.cardDO.count}}</td>
                        <!--<td></td>-->
                      </tr>
                      </tbody>
                    </table>
                    <div class="hr-das-d"></div>
                  </div>

                  <div>
                    <a href="javascript:" @click="orderRecipienShowClose(e.cardDO.cradId)" class="cor00C2FF t-l pdt20">添加商品配送/安装信息</a>
                    <!--<p class="cor00C2FF t-l pdt20" @click="orderRecipienShowClose(e.cardDO.cradId)">添加商品配送/安装信息</p>-->
                    <table class="tab4">
                      <tbody>
                      <tr>
                        <td class="ww16">收货人</td>
                        <td class="ww16">联系电话</td>
                        <td class="ww20">收货地址</td>
                        <td class="ww16" style="width: 10%">送货数量</td>
                        <td class="ww16">期望送货时间</td>
                        <td class="ww16">期望安装时间</td>
                        <td class="ww16" style="width: 6%">操作</td>
                      </tr>
                      <tr v-for="e2 in e.recevieInfos">
                        <td>{{e2.name}}</td>
                        <td>{{e2.phone}}</td>
                        <td>{{e2.address}}</td>
                        <td>{{e2.recNum}}</td>
                        <td>{{e2.expectRecTime}}</td>
                        <td>{{e2.expectInstTime}}</td>
                        <td><a href="javascript:" @click="delOrderRecipien(e2.id)">删除</a></td>
                      </tr>
                      <tr v-if="e.isShow">
                        <td colspan="3" class="t-l rel">
                          <div class="pdl30 ovh">
                            <div class="fl dizhi cor9 h59 lh59" style="width: 450px">
                              <select class="db ww100 hh pdl30 cor9 h59 lh59 ios18 bort" v-model="e.addRecipiens.id">
                                <option value="0">请选择</option>
                                <option v-for="recipientLists in recipientList" :value="recipientLists.id">
                                  {{recipientLists.name}}&nbsp;
                                  {{recipientLists.phone}}&nbsp;
                                  {{recipientLists.address}}&nbsp;
                                </option>
                              </select>
                            </div>
                            <a href="javascript:" @click="addRecipientShow()" style="margin-top: 20px;padding-top: 20px;margin-left: 20px;padding-bottom: 20px;">新增收货地址</a>
                            <!--<a href="javascript:void(0)" @click="dianji" class="db fr dianJi"><img class="db" src="/static/images/35.png" alt=""/></a>-->
                          </div>

                          <div class="tanchuang abs z100 dn" >
                            <select value="">
                              <option v-for="recipientLists in recipientList ">
                                {{recipientLists.name}}&nbsp;
                                {{recipientLists.phone}}&nbsp;
                                {{recipientLists.address}}&nbsp;
                              </option>
                            </select>

                          </div>
                        </td>
                        <td class="rel">
                          <div class="i_box" style="width: 100%;height: 59px; border: none;position: relative; padding-right: 43px;">
                            <a href="javascript:void (0);" class="J_add hov7" style="width: 43px;height: 29px;top: 0;right: 0;">
                              <img src="/static/images/36-1.png" alt="">
                            </a>
                            <input type="text"oninput = "value=value.replace(/[^\d]/g,'')" v-model="e.addRecipiens.count" value="0" class="J_input" style="width: 90%; height: 58px;line-height: 58px;">

                            <a href="javascript:void (0);" class="J_minus hov7" style="width: 43px;height: 30px;right: 0;bottom: 0;">
                              <img src="/static/images/36-2.png" alt="">
                            </a>

                          </div>
                        </td>
                        <td>
                          <!--<input type="text" placeholder="2018/02/01" class="db ww100 h58 lh58 t-c bort ECalendar pdr43 ios19" name="date" id="ECalendar_case1">-->
                          <div class="block">
                            <input type="Date" v-model="e.addRecipiens.data1"/>
                          </div>
                        </td>
                        <td>
                          <!--<input type="text" placeholder="2018/02/01" class="db ww100 h58 lh58 t-c bort ECalendar pdr43 ios19" name="date" id="ECalendar_case2">-->
                          <input type="Date" v-model="e.addRecipiens.data2"/>
                        </td>
                        <td><a href="javascript:"@click="addOrderRecipien(e.cardDO.cradId)" style="border: #00a0e9 1px solid">确定</a></td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="bgf1 h56 lh56 pdl20 pdr20 ovh mgt30 cor0">结算方式</div>
          <div class="h56 lh56 pdl20 pdr20 mgt20">
            <div class="cor9">
              <label class="lab3"><input name="ccs" type="checkbox" checked> <i></i>扣除预算</label>
              <p class="dinb mgl50 corED145B">本次扣除预算：￥{{money.toFixed(2)}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    扣除后<span v-if="isYear">本年</span><span v-else>本月/本季度</span>可用预算：￥{{leftMoney.toFixed(2)}}</p></div>
          </div>

          <div class="bgf1 h56 lh56 pdl20 pdr20 ovh mgt20 cor0">发票信息</div>
          <div class="h56 lh56 pdl20 pdr20 mgt20">
            <div class="cor9">普通发票 深圳市顺恒利科技工程有限公司商品明细  <a href="###" class="dinb mgl50 cor00C2FF hov7"> 编辑</a></div>
          </div>
          <div class="bgf1 h56 lh56 ovh mgt20 cor0">
            <div class="fr ovh">
              <span class="mgr30"> {{amount}} 件商品</span><span class="mgr30">总金额：￥{{money.toFixed(2)}}</span><a @click="addOrder" href="javascript:" class="dinb w120 h56 lh56 t-c corw bg707070 btn-a2">提交订单</a>
            </div>
          </div>
        </div>
      </form>
    </div>
    <!--底部-->
    <div class="bg333 fs14 corw">
      <div class="w1200 c-b mm100 ovh foot1">
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">关于我们</a>
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">购物流程</a>
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">配送方式</a>
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">支付方式</a>
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">售后流程</a>
        <a href="###" class="db h70 lh70 fl corw mgr30 hov7">特色服务</a>
      </div>
    </div>
    <div class="bg3d pdt30 pdb65 fs14 corw">
      <div class="w1200 c-b mm100 ovh foot2">
        <p class="fs12 corw">Copyright © 2010-2020 XXXXXXXXX公司  XXXXXX.com  保留一切权利。客服热线：400-888-8888</p>
        <p class="fs12 corw mgt5">京公网安备 1101010200111111 号 | 京ICP证111111号 | 食品流通许可证 SP110105111111111（1-1） | 营业执照 | 公示制度</p>
        <div class="mgt20 ovh">
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-1.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-2.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-3.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-4.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-5.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-2.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="/static/images/24-3.png" alt=""></a>
        </div>
      </div>
    </div>
    <!--添加收货地址-->
    <div id="recipient" style="display: none;position: fixed;z-index: 11; width: 100%;height: 100%;left: 0;bottom: 0;background-color: rgba(0,0,0,.5);">
      <form style="background-color: #E9EFF6;margin-left: 30px;padding-top: 30px;margin-right: 30px;margin-bottom: 30px;width: 500px;height: 300px;position: absolute;left: 38%;top: 30%">
        <div style="margin: 10px 20px 10px 20px">
          <div class="form-group">
            <label for="receiveName">收货人</label>
            <input type="text" class="form-control" id="receiveName" v-model="recipient.name" >
          </div>
          <div class="form-group">
            <label for="contactPhone">联系电话</label>
            <input type="text" class="form-control" id="contactPhone" v-model="recipient.phone" >
          </div>
          <div class="form-group">
            <label for="address">送货地址</label>
            <input type="text" class="form-control" id="address" v-model="recipient.address">
          </div>
          <a @click="queding" style="margin-left: 30px" class="btn btn-default queDing">确定</a>
          <a style="float: right;margin-right: 50px" @click="addRecipientClose" class="btn btn-default queDing">取消</a>
        </div>
      </form>
      <!--<input type="button" value="取消" @click="addRecipientClose" style="margin-left: 130px"/>-->
    </div>
  </div>
</template>

<script>
    import $ from 'jquery'
    import config from '../../common/config'
    export default {
        name: "accountDetailInfo",
        data() {
          return {
            params : {
              userId : 1
            },
            cartRecords: null,
            amount : 0,
            money : 0,
            receiverRecord : {
              receiverId : '',
              receiveName : '',
              contactPhone : '',
              address : '',
              recNum : 0,
              expectRecTime : '',
              expectInstTime : ''
            },
            recipient : {
              id : '',
              name : '',
              phone : '',
              address : '',
              orgId : config.userInfo.orgId,
              createBy : config.userInfo.userId
            },
            recevieInfos : [],
            ids : []
            //数据库地址集合txy
            ,recipientList:{},
            //本次购买各科目要使用的金额
            subObjList : [],
            //扣除预算后科目及可用预算集合
            budgetCountList : [],
            title:[]//预算结果
           , selSubjectId : ''   //选择结算的科目Id
            ,leftMoney : 0    //该科目剩余可使用钱
            ,isYear : false
          }
        },
        created () {
          this.configJS.getUserInfo(this);
          this.ids = this.$route.params.id;
          this.selSubjectId = this.$route.params.subId;
          this.leftMoney = this.$route.params.leftMoney;
          this.isYear = this.$route.params.isYear;
          this.money = this.$route.params.money;
          this.amount = this.$route.params.total;
          this.initGoodsList();
          getRecipientList(this)//得到地址txy
        },
        mounted () {
        },
        methods : {
          dianji() {
            $('.tanchuang').slideToggle();
          },
          xinzeng : function() {
            $('#shu').slideDown();
          },
          queding : function() {
            //增加收货人信息
            this.addReciver();
          },
          //加载商品列表
          initGoodsList() {
            this.$axios({
              method: 'get',
              url: '/biz/tob-shopping-cart/biz/card/checkRecords',
              params: {
                'ids' : this.ids
              }
            }).then((res) => {
              if(res.data.code == 200) {
                // var money = 0;
                // var amount = 0;
                this.cartRecords = res.data.list
                for(var i=0;i<this.cartRecords.length;i++){
                  for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                    this.cartRecords[i].cardDOList[ii].isShow=false;
                    this.cartRecords[i].cardDOList[ii].isMsgShow=false; //是否显示收货人错误信息
                    this.cartRecords[i].cardDOList[ii].recevieInfos=[]//动态创建地址集合
                    this. cartRecords[i].cardDOList[ii].addRecipiens={};//动态创建配送/安装对象
                    this.cartRecords[i].cardDOList[ii].addRecipiens.id=0
                    this.cartRecords[i].cardDOList[ii].addRecipiens.count=0
                    this.cartRecords[i].cardDOList[ii].addRecipiens.data1=''
                    this.cartRecords[i].cardDOList[ii].addRecipiens.data2=''
                  }
                }

                console.log("订单商品集合"+JSON.stringify(this.cartRecords))
              }
            }).catch((err)=>{
              console.log(err);
            });
          }
          //点击显示/隐藏添加商品安装信息
          ,orderRecipienShowClose(id){
            for(var i=0;i<this.cartRecords.length;i++){
              for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                if(this.cartRecords[i].cardDOList[ii].cardDO.cradId==id){
                  if(this.cartRecords[i].cardDOList[ii].isShow){
                    this.cartRecords[i].cardDOList[ii].isShow=false
                  }else{
                    this.cartRecords[i].cardDOList[ii].isShow=true
                  }
                }
              }
            }
            //重新加载集合
            updArrayList(this)
          },
          //添加订单的收货人
          addOrderRecipien(id){
            for(var i=0;i<this.cartRecords.length;i++){
              for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                if(this.cartRecords[i].cardDOList[ii].cardDO.cradId==id){
                  var count=0;
                  for (var iii=0;iii<this.cartRecords[i].cardDOList[ii].recevieInfos.length;iii++){
                    var s=parseInt(this.cartRecords[i].cardDOList[ii].recevieInfos[iii].recNum)
                    count=s+count;
                  }
                  //已经添加到收货信息的商品数量
                  count=count+parseInt(this.cartRecords[i].cardDOList[ii].addRecipiens.count)
                  //还可以加入到新收货信息的数量
                  count=this.cartRecords[i].cardDOList[ii].cardDO.count-count;
                  if(this.cartRecords[i].cardDOList[ii].addRecipiens.id==0){
                    this.$alert("请选择收货地址")
                  }else if(this.cartRecords[i].cardDOList[ii].addRecipiens.count==0){
                    this.$alert("收货数量不能小于1")
                    return;
                  }else if(count<0){
                    this.$alert("购物车数量不足")
                  }else {
                    getRecipientById(this,this.cartRecords[i].cardDOList[ii].addRecipiens.id
                      ,this.cartRecords[i].cardDOList[ii])//得到地址对象
                    /*this.cartRecords[i].cardDOList[ii].addRecipiens={}
                    this.cartRecords[i].cardDOList[ii].addRecipiens.id=0;
                    this.cartRecords[i].cardDOList[ii].addRecipiens.count=0;
                    this.cartRecords[i].cardDOList[ii].addRecipiens.data1='';
                    this.cartRecords[i].cardDOList[ii].addRecipiens.data2='';*/
                    this.orderRecipienShowClose(id)
                  }
                }
              }
            }
          },
          //删除订单的收货人
          delOrderRecipien(id){
            alert(JSON.stringify(id))
            //if(this.cartRecords[i].cardDOList[ii].cardDO.cradId==id){
              //this.cartRecords[i].cardDOList[ii].recevieInfos
            //}
            //this.orderRecipienShowClose(id)
          },
          //增加收货人信息
          addReciver() {
            if(this.recipient.name==null||this.recipient.name==''){
              this.$message("收货人不能为空")
              return;
            }
            if(this.recipient.phone==null||this.recipient.phone==''){
              this.$message("联系电话不能为空")
              return;
            }
            if(this.recipient.address==null||this.recipient.address==''){
              this.$message("地址不能为空")
              return;
            }
            this.$axios({
              method: 'post',
              headers:{
                'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
              },
              url: '/biz/tob-shopping-cart/biz/recipient/save',
              // url: '/biz/biz/recipient/save',
              data : JSON.stringify(this.recipient)
            }).then((res) => {
              console.log(JSON.stringify(res))
              if(res.data.code == 200) {
                this.$alert("新增地址成功");
                getRecipientList(this)
                this.addRecipientClose()

                this.recipient.id=''
                this.recipient.name=''
                this.recipient.phone=''
                this.recipient.address=''
                this.recipient.orgId=config.userInfo.orgId,
                this.recipient.createBy=config.userInfo.userId
              }
            }).catch((err)=>{
              console.log(err);
            });
          },
          //提交订单
          addOrder(){
              // let that = this;
              // debugger
              // //检查收货人信息是否合法
              // let code = checkRecivier(that);
              // if(code == 100) {
              //   checkOrder(that);
              // }else if(code == 101){
              //   this.$message({
              //     showClose: true,
              //     message: '请添加收货人',
              //     type: 'warning'
              //   });
              // }else {
              //   //送货数量有误
              // }

              let that = this;
              //检查是否添加收货人
              let flag = checkRecivier(that);
              if(flag) {
                checkOrder(that);
              }else {
                this.$message({
                  showClose: true,
                  message: '请添加收货人',
                  type: 'warning'
                });
              }

            },
          updDate(){
            alert(1)
            updArrayList(this)
          }
          //点击显示新增地址窗口
          ,addRecipientShow(){
            $("#recipient").show()
          }
          //点击关闭新增地址窗口
          ,addRecipientClose(){
            $("#recipient").hide()
            this.recipient.id=''
            this.recipient.name=''
            this.recipient.phone=''
            this.recipient.address=''
            this.recipient.orgId=config.userInfo.orgId,
              this.recipient.createBy=config.userInfo.userId
          }

        }

    }

    //检查收货人记录是否合法
    // function checkRecivier(_this) {
    //   for(let i=0 ; i<_this.cartRecords.length ; i++){
    //     let cartRecord = _this.cartRecords[i];
    //     for (let j=0;j < cartRecord.cardDOList.length ; j++) {
    //       let cardVO = cartRecord.cardDOList[j];
    //       if(cardVO.recevieInfos.length < 1){
    //         return 101;   //未添加收货人
    //       }else {
    //         let count = cardVO.cardDO.count;
    //         let sum = 0;
    //         for (let k = 0; k < cardVO.recevieInfos.length; k++) {
    //           sum += cardVO.recevieInfos[k].recNum;
    //           if(count != sum) {
    //             _this.cartRecords[i].cardDOList[j].isMsgShow = true;
    //             return 102;   //送货数量有误
    //           }
    //         }
    //       }
    //     }
    //   }
    //   return 100; //正常
    //
    // }
    function checkRecivier(_this) {
      for(let i=0 ; i<_this.cartRecords.length ; i++){
        let cartRecord = _this.cartRecords[i];
        for (let j=0;j < cartRecord.cardDOList.length ; j++) {
          let cardVO = cartRecord.cardDOList[j];
          if(cardVO.recevieInfos.length < 1){
            return false;
          }
        }
      }
      return true;

    }

    //检查订单是否超预算
    function checkOrder(that) {
      let judgBudgetVoList = [];
      let judgVO = {};
      judgVO.orgId = that.configJS.userInfo.orgId;
      judgVO.subjectId = that.selSubjectId;
      judgVO.useAmount = that.money;
      judgBudgetVoList.push(judgVO)

      //判断是否可以提交
      that.$axios({
        method: 'post',
        headers:{
          'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
        },
        url: '/biz/tob-budget/biz/budget/judgeBudget',
        // data : JSON.stringify(this.cartRecords)
        data : JSON.stringify(judgBudgetVoList)
      }).then((res) => {
        let code = res.data.code;
        if(code == 200) {
          that.$confirm("确定提交订单？").then(function () {
            generateOrder(that);
          })

        }else if(code == 101 || code == 104) {
          that.$confirm("月度计划超额,是否提交订单？").then(function () {
            generateOrder(that);
          })
        }else if(code == 102 || code == 105) {
            that.$confirm("季度计划超额,是否提交订单？").then(function () {
              generateOrder(that);
          })
        }else if(code == 103) {
          that.$alert("科目已用预算超过年度预算额，不能下单!");
        }else {
          that.$alert("发生未知的错误，请联系管理员");
        }
      }

      ).catch((err)=>{
        console.log(err);
      });
    }

    function isEmpty(obj){
      if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
      }else{
        return false;
      }
    }

    //生成订单
    function generateOrder(_this) {
      let param={};
      param.recordVOList=_this.cartRecords;
      param.orgId = _this.configJS.userInfo.orgId;
      param.userId = _this.configJS.userInfo.userId;
      param.subId = _this.selSubjectId;
      _this.$axios({
        method: 'post',
        headers:{
          'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
        },
        url: '/biz/tob-shopping-cart/biz/card/generateOrder',
        // data : JSON.stringify(this.cartRecords)
        data : param
      }).then((res) => {
        debugger
        let code = res.data.code;
        if(code == 200) {
          _this.$message({
            showClose: true,
            message: '下单成功',
            type: 'success'
          });
          _this.$router.push({name:'myOrders'});
          _this.$emit("changCartCount")
        }else {
          _this.$alert(res.data.msg);
        }
      }).catch((err)=>{
        console.log(err);
      });
    }

    //根据用户id得到地址集合
    function getRecipientList(_this){
      _this.$axios({
        url:'/biz/tob-shopping-cart/biz/card/getRecipient'
        ,params:{create_by:_this.configJS.userInfo.userId}
      }).then((ref)=>{

        if(ref.data.code==200){
          _this.recipientList=ref.data.list;
          console.log("地址集合"+JSON.stringify(_this.recipientList))
        }
      })
    }
  //根据地址id得到地址对象
  function getRecipientById(_this,id,list){
      _this.$axios({
        async: false,
        url:'/biz/tob-shopping-cart/biz/card/getRecipientById'
        ,params:{id:id}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.recipientById=ref.data.map;
          _this.recipientById.receiverId=ref.data.map.id
          _this.recipientById.recNum=list.addRecipiens.count
          _this.recipientById.expectRecTime=list.addRecipiens.data1
          _this.recipientById.expectInstTime=list.addRecipiens.data2
          list.recevieInfos.push(_this.recipientById)
          console.log("用户输入的地址对象----"+JSON.stringify(list.addRecipiens))

          let arrays = [];
          for(let temp of _this.cartRecords){
            arrays.push(temp);
          }
          _this.cartRecords = [];
          _this.cartRecords= arrays;
          console.log("添加地址集合后的订单商品集合----"+JSON.stringify(_this.cartRecords))
        }
      })

    }

  //计算各科目要使用的金额
  function countMoneyBySub(_this) {
    let cartRecords = _this.cartRecords;
    // let subObj = null;
    let subObjList = [];
    // subObj.subjectId = '';
    // subObj.useMoney = '';
    for(let i = 0;i < cartRecords.length; i++) {
      let cardDOList = cartRecords[i].cardDOList;
      for (let j = 0; j < cardDOList.length; j++) {
         let cd = cardDOList[j];
        let inx =  checkSubId(subObjList,cd.subId);
        if(inx == -1) {
          //subObjList不存在该科目,添加该科目
          let subObj = {};
          let skuIdList = [];
          subObj.subjectId = cd.subId;
          subObj.useMoney = cd.cardDO.count * cd.cardDO.price;
          skuIdList.push(cd.cardDO.skuId)
          subObj.skuIdList = skuIdList;
          subObjList.push(subObj);
        }else {
          //存在，直接把金额加上
          subObjList[inx].useMoney += cd.cardDO.count * cd .cardDO.price;
          subObjList[inx].skuIdList.push(cd.cardDO.skuId);
        }
      }
    }
    _this.subObjList = subObjList;  //保存到全局变量
    console.log(JSON.stringify(subObjList))
  }




  //查询该科目是否已经存在，存在返回index,否则返回-1
  function checkSubId(subObjList,subId) {
    for (let i = 0; i < subObjList.length; i++) {
      if(subObjList[i].subjectId == subId) {
        return i;
      }
    }
    return -1;
  }
  //重新加载集合
  function updArrayList(_this){
    let list=_this.cartRecords
    _this.cartRecords=[]
    _this.cartRecords=list;
  }
</script>

<style scoped>

</style>
