<template>
  <!--送货管理-->
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">送货管理</p>
      <ul class="ul003 mgt20">
        <li class="active" @click.prevent="checkTab = 0"><a>待派单</a></li>
        <li id="yi" @click.prevent="checkTab = 1"><a>已派单</a></li>
      </ul>

      <div class="mgt20">
        <ul class="ul004">
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl">
                <select id="sorg" class="form-control" @change="deliveryManage">
                  <option value="-1">选择下单公司</option>
                  <option v-for="(item,index)  in orgs" :key="index" v-bind:value="item.orgId">{{item.orgName}}</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms.input" @keyup.enter="deliveryManage">
                    <span class="input-group-btn" @click.prevent="deliveryManage">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <div class="mgt20">
              <a style="cursor:pointer;" class="db cor0 pdl29 ios6" @click="chooseGoods">新建送货单</a>
            </div>
            <div class="mgt30">
              <table class="t-c ww100 table1">
                <thead>
                <tr>
                  <td class="bgf1 pdt15 pdb15">
                    <!--<p class="h16 w16 c-b"><label class="lab1"><input type="checkbox"><i></i></label></p>-->
                  </td>
                  <td class="bgf1 pdt15 pdb15">商品编号</td>
                  <td class="bgf1 pdt15 pdb15">商品名称</td>
                  <!--<td class="bgf1 pdt15 pdb15">单位</td>-->
                  <td class="bgf1 pdt15 pdb15">订单量</td>
                  <td class="bgf1 pdt15 pdb15">已发量</td>
                  <td class="bgf1 pdt15 pdb15">未发量</td>
                </tr>
                </thead>
                <tbody v-for="(item,index) in list" :key="index">
                <tr>
                  <td colspan="7" class="fs12 cor6 t-l pdt20 pdb10 pdl20 td-l td-r">
                    <span class="mgr30">{{item.createTime | formatDate('yyyy-MM-dd')}}</span>
                    订单号<span class="mgr30">{{item.orderNo}}</span>
                    收货人：<span class="mgr30">{{item.recipientName}}</span>
                    <span>{{item.recipientAddress}}</span>
                  </td>
                </tr>
                <tr v-for="e in item.goodsInfos">
                  <td class="pdt20 pdb20">
                    <p class="h16 w16 c-b"><label class="lab1"><input type="checkbox" v-model="e.isCheckd"><i></i></label></p>
                  </td>
                  <td class="pdt20 pdb20 td-l">{{e.goodsNo}}</td>
                  <td class="pdt20 pdb20 td-l">{{e.goodsName}}</td>
                  <!--<td class="pdt20 pdb20 td-l">台</td>-->
                  <td class="pdt20 pdb20 td-l">{{e.goodsNum}}</td>
                  <td class="pdt20 pdb20 td-l">{{e.receiveNum}}</td>
                  <td class="pdt20 pdb20 td-l">{{e.goodsNum-e.receiveNum}}</td>
                </tr>
                </tbody>
              </table>
            </div>
            <!--分页-->
            <div class="fen c-b ovh mgt30 t-c w410">
              <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
            </div>
            <!--分页END-->
          </li>
          <li class="dn">
            <div class="ovh mgt20">
              <!--<div class="fl">
                            <select class="form-control">
                                <option>下单公司</option>
                                <option>下单公司2</option>
                            </select>
                        </div>-->
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" id="invoiceNo" class="form-control" placeholder="送货单号" v-model.trim="parms1.input" @keyup.enter="havesentSingles">
                    <span class="input-group-btn" @click.prevent="havesentSingles">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>

            <div class="mgt30">
              <table class="t-c ww100 table1">
                <tbody>
                <tr>
                  <td class="bgf1 pdt15 pdb15">送货单号</td>
                  <td class="bgf1 pdt15 pdb15">派单时间</td>
                  <td class="bgf1 pdt15 pdb15">送货员</td>
                  <td class="bgf1 pdt15 pdb15">收货人</td>
                  <td class="bgf1 pdt15 pdb15">联系电话</td>
                  <td class="bgf1 pdt15 pdb15">收货地址</td>
                  <td class="bgf1 pdt15 pdb15">状态</td>
                  <td class="bgf1 pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="item in list1">
                  <td class="pdt20 pdb20 td-l">{{item.sendNo}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.sendSingleTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.sendName}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.closedName}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.sendPhone}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.address}}</td>
                  <td class="pdt20 pdb20 td-l">{{item.status==0?'派单':item.status==1?'商品出库':item.status==2?'运送中':item.status==3?'已签收':''}}</td>
                  <td class="pdt20 pdb20 td-l"><router-link style="cursor:pointer;" class="btn btn-info btn-xs" :to="{name:'invoiceDetails',params:{havesentSingle:JSON.stringify(item),supId:parms1.supId}}">查看详情</router-link></td>
                </tr>
                </tbody>
              </table>
            </div>
            <!--分页-->
            <div class="fen c-b ovh mgt30 t-c w410">
              <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
            </div>
            <!--分页END-->
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  import  conf from '../../common/config'
  import $ from 'jquery'
    export default {
        name: "deliveryManage",
      data(){
        return {
          checkTab:0,
          pageData:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 10//页面大小
          },
          pageData1:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 10//页面大小
          },
          orgs: [],//公司们
          list: [],//待派单列表
          list1:[],//已派单列表
          parms:{
            orgId:null,
            supId:null,
            input:null,
            num:1,
            size:10
          },
          parms1:{
            supId:1,
            input:null,
            num:1,
            size:10
          },
          goodss:[]//选中的商品信息集合
        }
      },
      created(){
        this.$axios({
          method: 'get',
          url: '/biz/tob-supplier/com/supplier/getSupplierById',
          params: {id:conf.userInfo.orgId}
        }).then((res) => {
          if (res.data.code == 200 && res.data.map!=null) {
            this.parms.supId=res.data.map.sup_id
            this.parms1.supId=res.data.map.sup_id
            selectDeliveryManage(this);
            selectHavesentSingles(this);
          }
        }).catch((err)=>{
          console.log(err);
        });
        this.$axios({
          method: 'get',
          url: '/biz/tob-order/biz/order/selectOrgs',
          params: {supId:conf.userInfo.orgId}
        }).then((res) => {
          console.log(111)
          console.log(res)
          if (res.data.code == 200 && res.data.list!=null) {
            this.orgs=res.data.list
          }
        }).catch((err)=>{
          console.log(err);
        });
      },
      mounted(){
        var $ul003= $('.ul003>li');
        $ul003.click(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $ul003.index(this);
          $('.ul004>li').eq(i).show().siblings('.ul004>li').hide();
        });
        if (this.$route.params.tabl==1) {
          $("#yi").click();
        }
      },
      methods:{
        deliveryManage(){
          this.parms.orgId=$("#sorg").val()
          if (this.parms.orgId==-1) {
            this.parms.orgId=null
          }
          this.parms.num=1
          selectDeliveryManage(this);
        },
        havesentSingles(){
          this.parms1.num=1
          selectHavesentSingles(this);
        },
        //分页
        chekdpage(currentPage) {
          this.parms.num = currentPage//页码
          selectDeliveryManage(this);
        },
        //分页
        chekdpage1(currentPage) {
          this.parms1.num = currentPage//页码
          selectHavesentSingles(this);
        },
        chooseGoods(){//获取选中的
          let goodss=[]//选中的商品信息集合
          for (var i=0;i<this.list.length;i++) {
            var listt=this.list[i].goodsInfos;
            for (var j=0;j<listt.length;j++) {
              if (listt[j] && listt[j].isCheckd) {
                var obj={
                  orderId:this.list[i].orderId,//订单id
                  orderNo:this.list[i].orderNo,//订单编号
                  createTime:this.list[i].createTime,//订单创建时间
                  recipientId:this.list[i].recipientId,//收货人id
                  recipientName:this.list[i].recipientName,//收货人姓名
                  recipientPhone:this.list[i].recipientPhone,
                  recipientAddress:this.list[i].recipientAddress,
                  goId:listt[j].goId,//订单详情表主键id
                  skuId:listt[j].skuId,//商品的skuid
                  goodsNo:listt[j].goodsNo,//商品编号
                  goodsName:listt[j].goodsName,//商品名称
                  goodsNum:listt[j].goodsNum,//订单量
                  receiveNum:listt[j].receiveNum,//已发量
                  isCheckd:listt[j].isCheckd
                }
                goodss.push(obj);
                this.goodss.push(obj);
              }else if (listt[j] && !listt[j].isCheckd) {
                for (var g=0;g<this.goodss.length;g++) {
                  if (this.goodss[g].orderId==this.list[i].orderId && this.goodss[g].recipientId==this.list[i].recipientId && this.goodss[g].skuId==listt[j].skuId && !listt[j].isCheckd) {
                    this.goodss.splice(g,1);
                  }
                }
              }
            }
          }
          if (this.goodss.length>0) {
            var idd=this.goodss[0].recipientId;
            for (var i=0;i<this.goodss.length;i++) {
              if (idd!=this.goodss[i].recipientId) {
                this.$alert("需选择相同送货人的订单!")
                return;
              }
            }
            this.$router.push({name: 'addDeliveryNote', params:{goodss: JSON.stringify(this.goodss),supId:this.parms.supId}})
          }else {
            this.$alert("请选择商品信息")
          }
        }
      },
      watch:{
        checkTab(newVal,oldVal){
          if(newVal != oldVal) {
            this.parms.num=1
            this.parms1.num=1
            if (newVal==0) {
              selectDeliveryManage(this);
            }
            if (newVal==1) {
              selectHavesentSingles(this);
            }
          }
        }
      }
    }
    //查询送货管理列表
  function selectDeliveryManage(_this) {
    console.log(666)
    console.log(_this.parms)
    _this.$axios({
      method: 'get',
      url: '/biz/tob-order/biz/order/deliveryManage',
      params:  _this.parms
    }).then((res) => {
      console.log(res)
      if (res.data.code == 200 && res.data.list!=null) {
        _this.list=res.data.list
        _this.pageData.curPage=_this.parms.num
        _this.pageData.totalCount=res.data.count
        for (var i=0;i<_this.list.length;i++) {
          var listt=_this.list[i].goodsInfos;
          for (var j=0;j<listt.length;j++) {
            if (_this.goodss.length>0) {
              for (var g=0;g<_this.goodss.length;g++) {
                if (this.goodss[g].orderId==this.list[i].orderId && this.goodss[g].recipientId==this.list[i].recipientId && this.goodss[g].skuId==listt[j].skuId) {
                  if (_this.goodss[g].isCheckd) {
                    listt[j].isCheckd = true;
                  }else {
                    listt[j].isCheckd = false;
                  }
                }else {
                  listt[j].isCheckd = false;
                }
              }
            }else {
              listt[j].isCheckd = false;//默认未选中
            }
          }
        }
      }
    }).catch((err)=>{
      console.log(err);
    });
  }

  //查询送货管理列表已派单
  function selectHavesentSingles(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-order/biz/order/selectHavesentSingleeee',
      params:  _this.parms1
    }).then((res) => {
      console.log(res)
      if (res.data.code == 200 && res.data.list!=null) {
        _this.list1=res.data.list
        _this.pageData1.curPage=_this.parms.num
        _this.pageData1.totalCount=res.data.count
      }
    }).catch((err)=>{
      console.log(err);
    });
  }
</script>

<style scoped>

</style>
