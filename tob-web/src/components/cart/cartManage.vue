<template>
  <div class="wrap2 ww100 pdt191" style="padding-top: 10px">
    <!--&lt;!&ndash;头部&ndash;&gt;-->
    <!--<div class="ww100 header">-->
      <!--<div class="bgf2 pdl15 pdr15">-->
        <!--<div class="w1200 c-b mm100 cor9 ovh">-->
          <!--<div class="fl h40 lh40">欢迎进入xx商城后台管理系统！      客服热线：400-888-888</div>-->
          <!--<div class="fr ovh">-->
            <!--<a href="###" class="db h40 lh40 cor9 mgl40 fl pdl25 ios13">我的收藏</a>-->
            <!--<a href="###" class="db h40 lh40 cor9 mgl40 fl">我的订单</a>-->
            <!--<a href="###" class="db h40 lh40 cor9 mgl40 fl pdl25 ios14">个人中心</a>-->
            <!--<a href="###" class="db h40 lh40 cor9 mgl40 fl">退出登陆</a>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
      <!--<div class="pdl15 pdr15 bor-b-ED145B">-->
        <!--<div class="w1200 c-b mm100 ovh pdt40 pdb30">-->
          <!--<div class="fl w187">-->
            <!--<a href="###" class="db"><img src="/static/images/21.png" alt=""></a>-->
          <!--</div>-->
          <!--<div class="fr w187 pdt18 pdb18">-->
            <!--<img src="images/22.png" alt="" class="db fl w60">-->
            <!--<a href="###" class="db fl w127 borb hov7">返回商城</a>-->
          <!--</div>-->
          <!--<div class="h-m pdt18 pdb18">-->
            <!--<form action="#">-->
              <!--<div class="w80 fr"><input type="text" class="ww100 h40 lh40 bgED145B bort corw t-c hov7" value="搜索"></div>-->
              <!--<div class="mgr80"><input type="text" class="ww100 h40 lh40 bor-2 cor9 pdl15" placeholder="请输入产品名称"></div>-->
            <!--</form>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <!--&lt;!&ndash;头部END&ndash;&gt;-->

    <div class="ww100 pdt30 pdb80 pdl15 pdr15">
      <div class="w1200 c-b mm100">
        <div class="ovh">
          <div class="fl fs20 corED145B ios15 pdl48">我的购物车（<span>{{totalAmount}}</span>）</div>
          <!--<div class="fr fs24 cor3">本月可用预算：<span>¥83500.00</span></div>-->
        </div>
        <div class="mgt30">
          <div class="mgt30 ovh t-c bgf2">
            <p class="fl ww60 pdt20 pdb20 cor0">商品</p>
            <p class="fl ww10 pdt20 pdb20 cor0">单价</p>
            <p class="fl ww10 pdt20 pdb20 cor0">数量</p>
            <p class="fl ww10 pdt20 pdb20 cor0">小计</p>
            <p class="fl ww10 pdt20 pdb20 cor0">操作</p>
          </div>

          <div v-for="item in cartRecords">
            <div class="pdt20 pdb20 pdl20 pdr20"><!--<label class="lab2">&lt;!&ndash;<input class="supplier" @click="supplier($event)" type="checkbox" checked>&ndash;&gt;<i></i></label>-->{{item.supplierDO.supName}}</div>
            <div>
              <table class="tab2">
                <tr v-for="e in item.cardDOList">
                  <td class="t-l pdl20 ww5"><label class="lab2">
                    <input type="checkbox" v-model="e.checked" @click="select($event,e.cardDO.cradId)"><i></i></label></td>
                  <td class="ww55">
                    <div class="t-l pdl15 ovh" @click.prvent="$router.push({'name':'product_info',params:{'skuId':e.cardDO.skuId}})">
                      <div class="fl w80"><img :src="e.thumbnailUrl"  alt=""></div>
                      <div class="mgl90 pdt10">
                        <a class="cor0">{{e.attrName}}</a>
                        <p class="fs12 cor9 mgt10">采购量：<span>210</span>  |  <span>浏览量：345</span></p>
                      </div>
                    </div>
                  </td>
                  <td class="ww10">￥{{e.cardDO.price}}</td>
                  <td class="ww10">
                    <div class="i_box">
                      <a href="javascript:"  @click="updOrderCount(e.cardDO.cradId,-1,e.cardDO.count)" class="J_minus hov7"><img src="/static/images/25-2.png" alt=""></a>
                      <input type="text"oninput = "value=value.replace(/[^\d]/g,'')" @change="changeNum(e.cardDO.cradId,e.cardDO.count)" v-model="e.cardDO.count" class="J_input" />
                      <a href="javascript:" @click="updOrderCount(e.cardDO.cradId,1,e.cardDO.count)" class="J_add hov7"><img src="/static/images/25-1.png" alt=""></a>
                    </div>
                  </td>
                  <td class="ww10">￥{{(e.cardDO.price*e.cardDO.count).toFixed(2)}}</td>
                  <td class="ww10"><a href="javascript:"@click="delCard(e.cardDO.cradId)" class="btn btn-sm btn-danger">删除</a></td>
                </tr>
              </table>
            </div>
          </div>
          <!--<div class="ovh  bgf2 oDiv1">-->
            <!--<div style="color:#dd0000;font-size: 16px">-->
              <!--<div v-for="e in title" style="float: left">{{e.title}}</div>-->
              <!--&lt;!&ndash;<p v-for="e in title">{{e.title}}</p>&ndash;&gt;-->
            <!--</div>-->
          <!--</div>-->

          <!--<div class="ovh  bgf2 oDiv1">-->
            <!--<div class="pdt20 pdb20 ovh pdl20">-->
              <!--<div class="fl h32 lh32 mgr10">请选择预算科目</div>-->
              <!--<div class="fl mgr20">-->
                <!--<select type="text" class="db w130 h32 lh32 bor-d pdl10 ios0 fs13">-->
                  <!--<option value="1">科目一</option>-->
                  <!--<option value="1">科目二</option>-->
                <!--</select>-->
              <!--</div>-->
              <!--<div class="fl h32 lh32 corED145B mgr20">本次扣除预算：<span>￥2325143.00</span></div>-->
              <!--<div class="fl h32 lh32 corED145B">扣除后本月/季度可用预算：<span>￥5325143.00</span></div>-->
            <!--</div>-->
            <!--<div class="fl h70 lh70 pdl20">-->
              <!--<label class="lab2"><input type="checkbox" v-model="isCheckAll" @click="qb($event)"><i></i>全选</label>-->
            <!--</div>-->
            <!--<div class="fr">-->
              <!--<div class="ovh rel h70 lh70">-->
                <!--&lt;!&ndash;<div class="fl pdl90" style="margin-right: 30px">删除选中的商品</div>&ndash;&gt;-->
                <!--<div class="fr ovh pdr120">-->
                  <!--<p class="fl mgr30">已选择 {{count}} 件商品</p>-->
                  <!--<p class="fl mgr30">总价：￥{{moeny}}</p>-->
                <!--</div>-->
                <!--<a href="javascript:" @click="add" class="db fl span-a">去结算</a>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->

          <div class="bor-d mgt20">
            <div class="pdt20 pdb20 ovh pdl20">
              <div class="fl h32 lh32 mgr10">请选择预算科目</div>
            <div class="fl mgr20">
              <select id="myselect" type="text" class="db w130 h32 lh32 bor-d pdl10 ios0 fs13" v-model="selSubjectId">
                <option value="">--请选择--</option>
                <option v-for="subItem in subjList" :value="subItem.id">{{subItem.subName}}</option>
              </select>
            </div>
            <!--<div class="fl h32 lh32 corED145B">{{budgetMsg}}</div>-->
              <div v-if="selSubjectId != ''">
                <div class="fl h32 lh32 corED145B mgr20">本次扣除预算：<span>￥{{moeny.toFixed(2)}}</span></div>
                <div class="fl h32 lh32 corED145B">
                  <span v-if="isYear">扣除后本年可用预算：</span>
                  <span v-else>扣除后本月/本季度可用预算：</span>
                  <span>￥{{leftMoney.toFixed(2)}}</span>
                </div>
              </div>
          </div>
            <div class="ovh  bgf2">
              <div class="fl h70 lh70 pdl20">
                <label class="lab2"><input type="checkbox" v-model="isCheckAll" @click="qb($event)"><i></i>全选</label>
              </div>
              <div class="fl lh70 pdl90 corED145B " @click="batchDel" style="cursor:pointer;">删除选中的商品</div>
              <div class="fr">
                <div class="ovh rel h70 lh70">

                  <div class="fr ovh pdr120 corED145B">
                    <p class="fl mgr30">已选择 {{count}} 件商品</p>
                    <p class="fl mgr30">总价：￥{{moeny.toFixed(2)}}</p>
                  </div>

                  <a href="javascript:" @click="add" class="db fl span-a">去结算</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

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
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-1.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-2.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-3.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-4.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-5.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-2.png" alt=""></a>
          <a href="###" class="db fl mgr10 hov7"><img src="images/24-3.png" alt=""></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

    export default {
        name: "cartManage",
        data() {
            return {
              params : {
                userId : this.configJS.userInfo.userId
              },cartRecords: null//购物车商品集合
              ,moeny:0//商品总价
              ,count:0//商品数量
              ,title:[]//预算结果
              ,cardId:[]//商品id集合
              ,noCard:[]//超过预算的商品集合
              ,budgetPublic:''//用户预算信息集合
              ,totalAmount: 0   //购物车商品总数量
              ,subObjList : []  //已选商品所有的科目及金额
              ,isCheckAll : false  //是否全选
              ,subjList : []  //该组织所有的科目
              ,selSubjectId : ''    //选择的科目
              ,budgetMsg : ''   //预算的使用情况
              ,leftMoney : 0     //剩余可用金额（年度或月度季度）
              ,isYear : true     //true ： 显示文字（年度可用） false： 显示文字（月度或季度可用）
            }
        },
      created() {
        //初始化科目下拉框
        initSubjList(this);
        let ids = this.$route.params.ids;
        let selSubjectId = this.$route.params.selSubjectId;
        //勾选上已选商品
        if(ids != '' && ids != undefined) {
          this.cardId = ids;
        }
        //选中原来的科目
        if(selSubjectId != '' && selSubjectId != undefined) {
          this.selSubjectId = selSubjectId;
        }
        //初始化
        initCartList(this);

      },
      mounted() {
        let that = this;
        $("#myselect").change(function(){
          reLoad(that);
        });
      }
      ,methods : {
        //增加减少购物车数量
        updOrderCount(id,count,counts){
          updOrderCount(this,id,count,counts)
        }
        //从购物车中移除商品
        ,delCard(id){
          let ids=[id];
          delCard(this,ids)
        },
        //批量删除商品
        batchDel() {
          //判断是否选择了商品
         if(checkIsSel(this)){
           batchDel(this);
         }else {
           this.$message({
             showClose: true,
             message: '请选择要删除的商品',
             type: 'warning'
           });
           return;
         }
        }
        //选中商品重新计算总价格和科目
        ,select(iii,cardId){
            let flag = 0;
            for(var i=0;i<this.cartRecords.length;i++){
              for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                if(this.cartRecords[i].cardDOList[ii].cardDO.cradId==cardId){
                  this.cartRecords[i].cardDOList[ii].checked=iii.target.checked
                }
                //只要一个为false，全选按钮就不选中
                if(flag == 0 && this.cartRecords[i].cardDOList[ii].checked == false) {
                  this.isCheckAll = false
                  flag = 1
                }
              }
              if(flag == 0) {
                this.isCheckAll = true
              }
          }
          // subjectBudget(this)
          reLoad(this)
        },
        //点击去结算
        add(){
          //检查是否选择了结算科目
          if(this.selSubjectId == '') {
            this.$message({
              showClose: true,
              message: '请选择商品的结算科目',
              type: 'warning'
            });
            return;
          }
          //检查是否选了商品
          let flag = checkIsSel(this);
          if(flag) {
            //检查所有的科目是否超过预算
            checkSubBudget(this);
          }else {
            this.$message({
              showClose: true,
              message: '请选择商品再去结算',
              type: 'warning'
            });
            return;
          }
          //全选
        },qb(i){
          if(i.target.checked==true){
            for(var i=0;i<this.cartRecords.length;i++){
              for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                this.cartRecords[i].cardDOList[ii].checked=true
              }
            }
          }else {
            for(var i=0;i<this.cartRecords.length;i++){
              for (var ii=0;ii<this.cartRecords[i].cardDOList.length;ii++) {
                this.cartRecords[i].cardDOList[ii].checked=false
              }
            }
          }
          updArrayList(this)
          // subjectBudget(this)
          debugger
          reLoad(this)
        }
        ,
        //直接点击修改商品数量
        changeNum(cradId,num) {
          changNumFun(this,cradId,num);
        }
      }
    }

    //批量删除购物车数据
    function batchDel(_this) {
        _this.$confirm("确定删除选中的商品？").then(function () {
          _this.$axios({
            method: 'get',
            url: '/biz/tob-shopping-cart/biz/card/batch',
            params: {
              cradIds : _this.cardId
            }
          }).then((res) => {
            if(res.data.code == 200) {
              //调整集合的元素
              chgCardRecordWithDel(_this,_this.cardId);
               reLoad(_this)
            }
          }).catch((err)=>{
            console.log(err);
          })
        })
    }

    //检查是否选择了商品,并将选择的商品添加到cradId集合
    function checkIsSel(_this) {
      //清空数组
      debugger
      _this.cardId.splice(0,_this.cardId.length);
      let flag = false;   //没有选择商品
      for(var i=0;i<_this.cartRecords.length;i++){
        for (var ii=0;ii<_this.cartRecords[i].cardDOList.length;ii++) {
          if(_this.cartRecords[i].cardDOList[ii].checked) {
            flag = true;
            _this.cardId.push(_this.cartRecords[i].cardDOList[ii].cardDO.cradId);
          }
        }
      }
      return flag;
    }

    //初始化科目下拉框
    function initSubjList(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-budget/subject/getButtomSubs',
        params: {
          orgId : _this.configJS.userInfo.orgId
        }
      }).then((res) => {
        if(res.data.code == 200) {
            // console.log(JSON.stringify(res.data.obj))
          debugger
          _this.subjList = res.data.obj
        }
      }).catch((err)=>{
        console.log(err);
      })
    }

    //检查全选按钮状态
    function checkAllSelect(_this) {
      //检查全选按钮的状态
        let flag = 0;
        for(var i=0;i<_this.cartRecords.length;i++){
          for (var ii=0;ii<_this.cartRecords[i].cardDOList.length;ii++) {
            //只要一个为false，全选按钮就不选中
            if(flag == 0 && _this.cartRecords[i].cardDOList[ii].checked == false) {
              _this.isCheckAll = false
              flag = 1
              return;
            }
          }
          if(flag == 0) {
            _this.isCheckAll = true
          }
        }
    }

    //得到购物车商品信息
    function initCartList(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-shopping-cart/biz/card/list',
        params: _this.params
      }).then((res) => {
        if(res.data.code == 200) {
          _this.cartRecords = res.data.list
          let totalAmount = 0;
          if(_this.cardId != null && _this.cardId != undefined && _this.cardId.length > 0){
            //如果是从核算页跳转回来，需勾选上原来的商品
            for(var i=0;i<_this.cartRecords.length;i++){
              for (var ii=0;ii<_this.cartRecords[i].cardDOList.length;ii++) {
                _this.cartRecords[i].cardDOList[ii].checked=false;    //默认不选中
                totalAmount++;
                for (let j = 0; j < _this.cardId.length; j++) {
                  if(_this.cartRecords[i].cardDOList[ii].cardDO.cradId == _this.cardId[j]) {
                    _this.cartRecords[i].cardDOList[ii].checked=true;
                    break;
                  }
                }
              }
            }
          }else {
            for(var i=0;i<_this.cartRecords.length;i++){
              for (var ii=0;ii<_this.cartRecords[i].cardDOList.length;ii++) {
                totalAmount++;
                _this.cartRecords[i].cardDOList[ii].checked=false;    //默认不选中
              }
            }
          }
          _this.totalAmount = totalAmount;
          reLoad(_this);
        }
      }).catch((err)=>{
        console.log(err);
      });
    }

    //刷新页面数据，不请求接口
    function reLoad(_this) {
      debugger
      let money = 0;
      let total = 0;
       //刷新商品件数及总价
      for (let i = 0; i < _this.cartRecords.length; i++) {
        let cartRecord = _this.cartRecords[i];
        for (let j = 0; j < cartRecord.cardDOList.length; j++) {
          let cardVO = cartRecord.cardDOList[j];
          if(cardVO.checked) {
            money += cardVO.cardDO.count * cardVO.cardDO.price;
            total++;
          }
        }
      }
      _this.count = total;
      _this.moeny = money;
      //检查全选按钮状态
      checkAllSelect(_this);
       //刷新预算信息
      if(_this.selSubjectId != '') {   //选择了科目
        _this.$axios({
          url:'/biz/tob-budget/biz/budget/budgetPublic'
          ,params:{orgId:_this.configJS.userInfo.orgId}
        }).then((ref => {
            if(ref.data.code == 200) {
               _this.budgetPublic = ref.data.obj;
              for (let i = 0; i < _this.budgetPublic.length; i++) {
                if(_this.budgetPublic[i].subjectId == _this.selSubjectId) {
                  debugger
                   if(_this.budgetPublic[i].leftMonthOrSeasonAmount == null || _this.budgetPublic[i].leftMonthOrSeasonAmount == '') {
                      //统计年的预算情况
                      _this.leftMoney = _this.budgetPublic[i].leftYearAmount - _this.moeny
                      _this.isYear = true;
                   }else {
                      //按月或季度统计预算
                     _this.leftMoney = _this.budgetPublic[i].leftMonthOrSeasonAmount - _this.moeny
                     _this.isYear = false;
                   }
                }
              }
            }
        }))
      }else {
        _this.budgetMsg = '';
      }
    }

    //直接点击修改商品数量
    function changNumFun(_this,id,count) {
      _this.$axios({
        url:'/biz/tob-shopping-cart/biz/card/updateCount'
        ,params:{'id':id,'count':count}
      }).then((ref=>{
        //更新预算使用情况
        // subjectBudget(_this)
        reLoad(_this)
      }))
    }

    //点击增加，减少购物车数量
    function updOrderCount(_this,id,count,counts){
      if(counts<=1&&count==-1){
        _this.$message({
          showClose: true,
          message: '数量为1不能再减了噢~',
          type: 'warning'
        });
        return;
      }
      _this.$axios({
        url:'/biz/tob-shopping-cart/biz/card/updOrderCount'
        ,params:{'id':id,'count':count}
      }).then((ref=>{
        if(ref.data.code){
          // initCartList(_this)
          //更新调整后的值
          chgCardRecord(_this,count+counts,id);
        }
      }))
    }

    //点击增加减少数量后，调整集合里的元素
    function chgCardRecord(_this,resCount,id) {
      for(let i=0;i<_this.cartRecords.length;i++){
        for (let j=0;j<_this.cartRecords[i].cardDOList.length;j++) {
          console.log(id+":"+_this.cartRecords[i].cardDOList[j].cardDO.cradId)
          if(id == _this.cartRecords[i].cardDOList[j].cardDO.cradId) {
            _this.cartRecords[i].cardDOList[j].cardDO.count = resCount;
            // subjectBudget(_this);
            reLoad(_this)
            return;
          }
        }
      }
    }

    //删除商品后，调整集合里的元素
    function chgCardRecordWithDel(_this,ids) {
      debugger
      let totalAmount = _this.totalAmount
      for (let k = 0; k < ids.length; k++) {
        for(let i=0;i<_this.cartRecords.length;i++){
          let flag = 1;
          for (let j=0;j<_this.cartRecords[i].cardDOList.length;j++) {
            if(ids[k] == _this.cartRecords[i].cardDOList[j].cardDO.cradId) {
              //删除集合中的元素
              if(_this.cartRecords[i].cardDOList.length > 1) {
                //该供应商有多个商品，删除指定的商品即可
                _this.cartRecords[i].cardDOList.splice(j,1);
              }else {
                //该供应商只有一个商品，删除供应商的信息
                _this.cartRecords.splice(i,1);
              }
              flag = 0;
              break;
            }
          }
          if(flag == 0) {
            break
          }
        }
        //商品总数减一
        totalAmount--;
      }
      _this.totalAmount = totalAmount;
      _this.$emit("changCartCount")
    }

    //点击删除购物车商品
    function delCard(_this,id){
      _this.$confirm("确定删除该商品？").then(function () {
        _this.$axios({
          url:'/biz/tob-shopping-cart/biz/card/batch'
          ,params:{'cradIds':id}
        }).then((ref=>{
          if(ref.data.code==200){
            // initCartList(_this)
            chgCardRecordWithDel(_this,id);
            checkAllSelect(_this);
            // subjectBudget(_this);
            reLoad(_this)
          }
        }))
      })
    }



    function checkSubBudget(_this) {
      let judgBudgetVoList = [];
      let judgVo = {};
      judgVo.orgId = _this.configJS.userInfo.orgId;
      judgVo.subjectId = _this.selSubjectId;
      judgVo.useAmount = _this.moeny;
      judgBudgetVoList.push(judgVo)
      //判断是否可以提交
      _this.$axios({
        method: 'post',
        headers:{
          'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
        },
        url: '/biz/tob-budget/biz/budget/judgeBudget',
        data : JSON.stringify(judgBudgetVoList)
      }).then((res) => {
        let code = res.data.code;
        if(code == 200) {
          _this.$router.push({name:'accountDetailInfo',params:{'id':_this.cardId,'subId':_this.selSubjectId,'leftMoney':_this.leftMoney,'isYear':_this.isYear,'money':_this.moeny,'total':_this.total}})
        }else if(code == 101 || code == 104) {
          _this.$confirm("月度计划超额,是否去结算？").then(function () {
            _this.$router.push({name:'accountDetailInfo',params:{'id':_this.cardId,'subId':_this.selSubjectId,'leftMoney':_this.leftMoney,'isYear':_this.isYear,'money':_this.moeny,'total':_this.total}})
          })
        }else if(code == 102 || code == 105) {
          _this.$confirm("季度计划超额,是否去结算？").then(function () {
            _this.$router.push({name:'accountDetailInfo',params:{'id':_this.cardId,'subId':_this.selSubjectId,'leftMoney':_this.leftMoney,'isYear':_this.isYear,'money':_this.moeny,'total':_this.total}})
          })
        }else if(code == 103) {
          _this.$alert("科目已用预算超过年度预算额，不能下单!");
        }else {
          _this.$alert("发生未知的错误，请联系管理员");
        }

      }).catch((err)=>{
        console.log(err);
      });
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

    function isEmpty(obj){
      if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
      }else{
        return false;
      }
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
