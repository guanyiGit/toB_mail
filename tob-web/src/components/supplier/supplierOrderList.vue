<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">基本信息</p>
      <ul class="ul003 mgt20">
        <li class="active"><a href="javaascript:">待接单</a></li>
        <li><a href="javaascript:">已接单</a></li>
        <li><a href="javaascript:">已拒单</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--内容1-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0">
                  <option>下单公司</option>
                  <option>下单公司2</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0">
                  <option>近三个月订单</option>
                  <option>近三个月订单2</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="订单号">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <!--订单列表-->
            <div>
              <div class="mgt30 ovh t-c bgf1">
                <p class="fl ww50 h50 lh50 cor0">商品</p>
                <p class="fl ww10 h50 lh50 cor0">单价</p>
                <p class="fl ww10 h50 lh50 cor0">数量</p>
                <p class="fl ww10 h50 lh50 cor0">金额</p>
                <p class="fl ww10 h50 lh50 cor0">总金额</p>
                <p class="fl ww10 h50 lh50 cor0">操作</p>
              </div>

              <div v-for="item in order.map">
                <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
                  <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm')}}</p>
                  <p class="fl mgr20">订单号： {{item.orderNo}}</p>
                  <p class="fl">{{item.orgName}}-{{item.name}}</p>
                </div>
                <table class="tab30">
                  <tbody>
                  <tr>
                    <td class="ww80">
                      <table class="tab30-1" v-for="e in item.orderList">
                        <tbody>
                        <tr>
                          <td class="ww61 pdt20 pdb20 pdl20 pdr20">
                            <div class="fl w80"><img v-bind:src="e.thumbnailUrl" alt=""></div>
                            <div class="mgl90 t-l pdt15">
                              {{e.goodsAttr}}
                            </div>
                          </td>
                          <td class="ww13 pdt20 pdb20">￥{{e.price}}</td>
                          <td class="ww13 pdt20 pdb20">{{e.totalNum}}</td>
                          <td class="ww13 pdt20 pdb20">￥{{e.price*e.totalNum}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </td>

                    <td class="ww10 fb">￥{{item.totalMoney}}</td>
                    <td class="ww10">
                      <router-link :to="{name:'supplierOrderDetail',params:{'id':item.orderId,'type':4}}"class="btn btn-sm btn-xs btn-success">
                        订单详情</router-link>
                      <a href="javascript:" class="btn btn-sm btn-xs btn-primary mgt10"@click="updOrderState(item.orderId,6)">接受订单</a><br>
                      <a href="javascript:" class="btn btn-sm btn-xs btn-danger mgt10" @click="openRefuseOrder(item.orderId)">拒绝订单</a>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <!--分页-->
            <div class="fen c-b ovh mgt30 t-c w410">
              <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
            </div>
            <!--分页END-->
            <!--订单列表END-->
          </li>
          <!--内容2-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0">
                  <option>下单公司</option>
                  <option>下单公司2</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="订单号">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <!--订单列表-->
            <div>
              <div class="mgt30 ovh t-c bgf1">
                <p class="fl ww50 h50 lh50 cor0">商品</p>
                <p class="fl ww10 h50 lh50 cor0">单价</p>
                <p class="fl ww10 h50 lh50 cor0">数量</p>
                <p class="fl ww10 h50 lh50 cor0">总金额</p>
                <p class="fl ww10 h50 lh50 cor0">状态</p>
                <p class="fl ww10 h50 lh50 cor0">操作</p>
              </div>
              <div v-for="item in order1.map">
                <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
                  <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm')}}</p>
                  <p class="fl mgr20">订单号： {{item.orderNo}}</p>
                  <p class="fl">{{item.orgName}}-{{item.name}}</p>
                </div>
                <table class="tab30">
                  <tbody>
                  <tr>
                    <td class="ww80" style="width: 70%">
                      <table class="tab30-1" v-for="e in item.orderList">
                        <tbody>
                        <tr>
                          <td class="ww61 pdt20 pdb20 pdl20 pdr20">
                            <div class="fl w80"><img v-bind:src="e.thumbnailUrl" alt=""></div>
                            <div class="mgl90 t-l pdt15">
                              {{e.goodsAttr}}
                            </div>
                          </td>
                          <td class="ww13 pdt20 pdb20">￥{{e.price}}</td>
                          <td class="ww13 pdt20 pdb20">{{e.totalNum}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </td>
                    <td class="ww10 pdt20 pdb20 corFF2027" v-if="item.orderList[0].oStatus==6">待发货</td><!--#3BC83B-->
                    <td class="ww10 pdt20 pdb20 corFF2027" v-if="item.orderList[0].oStatus==7">待收货</td><!--#3BC83B-->
                    <td class="ww10 pdt20 pdb20 corFF2027" v-if="item.orderList[0].oStatus==8">已收货</td><!--#3BC83B-->
                    <td class="ww10 pdt20 pdb20 corFF2027" v-if="item.orderList[0].oStatus==9">已评价</td><!--#3BC83B-->
                    <td class="ww10">
                      <router-link :to="{name:'supplierOrderDetail',params:{'id':item.orderId,'type':6}}"class="btn btn-sm btn-xs btn-success">
                        订单详情</router-link>
                      <a href="javascript:" class="btn btn-sm btn-xs btn-info mgt10" v-if="item.orderList[0].oStatus!=6">送货信息</a>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <!--分页-->
            <div class="fen c-b ovh mgt30 t-c w410">
              <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
            </div>
            <!--分页END-->
            <!--订单列表END-->
          </li>
          <!--内容3-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0">
                  <option>下单公司</option>
                  <option>下单公司2</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0">
                  <option>近三个月订单</option>
                  <option>近三个月订单2</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="订单号">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <!--订单列表-->
            <div v-for="item in order2.map">
              <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
                <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm')}}</p>
                <p class="fl mgr20">订单号： {{item.orderNo}}</p>
                <p class="fl">{{item.orgName}}-{{item.name}}</p>
              </div>
              <table class="tab30">
                <tbody>
                <tr>
                  <td class="ww80">
                    <table class="tab30-1" v-for="e in item.orderList">
                      <tbody>
                      <tr>
                        <td class="ww61 pdt20 pdb20 pdl20 pdr20">
                          <div class="fl w80"><img v-bind:src="e.thumbnailUrl" alt=""></div>
                          <div class="mgl90 t-l pdt15">
                            {{e.goodsAttr}}
                          </div>
                        </td>
                        <td class="ww13 pdt20 pdb20">￥{{e.price}}</td>
                        <td class="ww13 pdt20 pdb20">{{e.totalNum}}</td>
                        <td class="ww13 pdt20 pdb20">￥{{e.price*e.totalNum}}</td>
                      </tr>
                      </tbody>
                    </table>
                  </td>

                  <td class="ww10 fb">￥{{item.totalMoney}}</td>
                  <td class="ww10">
                    <router-link :to="{name:'supplierOrderDetail',params:{'id':item.orderId,'type':5}}"class="btn btn-sm btn-xs btn-success">
                      订单详情</router-link>
                    <!--<a href="#" class="btn btn-sm btn-xs btn-success">订单详情</a><br>-->
                    <a href="javascript:" class="btn btn-sm btn-xs btn-danger mgt10" @click="selectRefuse(item.orderId)">拒单信息</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
            <!--分页-->
            <div class="fen c-b ovh mgt30 t-c w410">
              <page-vue :pageData="pageData2" @chekdpageCallcak="chekdpage2"></page-vue>
            </div>
            <!--分页END-->
            <!--订单列表END-->
          </li>

        </ul>
      </div>
    </div>
    <!--添加拒绝理由-->
    <div style="display: none" id="refuse">
      <div style="position:absolute;left: 0px;top:100px;width: 100%;height: 100%
      ;z-index: 2;display: flex;justify-content:center;align-items: center">
        <div style="width: 300px;height: 200px;border: #080808 solid 1px;background-color: aliceblue">
          <p style="margin-left: 20px;padding-top: 10px">请输入拒单原因:</p>
          <textarea style="margin-left: 20px;width: 260px;height: 100px;resize:none" v-model="refuse"></textarea>
          <br/>
          <input type="button" value="取消" @click="closeRefuseOrder" style="margin-left: 100px"/>
          <input type="button" value="确认" @click="refuseOrder" style="margin-left: 30px"/>
        </div>
      </div>
    </div>
    <!--查看拒绝理由-->
    <div style="display: none" id="selectRefuse">
      <div style="position:absolute;left: 0px;top:100px;width: 100%;height: 100%
      ;z-index: 2;display: flex;justify-content:center;align-items: center">
        <div style="width: 300px;height: 200px;border: #080808 solid 1px;background-color: aliceblue">
          <p style="margin-left: 20px;padding-top: 10px">拒单原因:</p>
          <textarea style="margin-left: 20px;width: 260px;height: 100px;resize:none" v-model="refuse"></textarea>
          <br/>
          <input type="button" value="取消" @click="closeRefuseOrders" style="margin-left: 130px"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import config from '../../common/config'
    export default {
        name: "supplierOrderList"
      ,data(){
        return {
          orgId:config.userInfo.orgId,
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
          pageData2:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 10//页面大小
          },order:{//待接单
            map:null
            ,params:{
              pageNum: 1,
              pageSize:10,
              supplierId : config.userInfo.orgId,
              status : 4
            }
          },order1:{//已接单
            map:null
            ,params:{
              pageNum: 1,
              pageSize:10,
              supplierId : config.userInfo.orgId,
              status : 6
            }
          },order2:{//已拒单
            map:null
            ,params:{
              pageNum: 1,
              pageSize:10,
              supplierId : config.userInfo.orgId,
              status : 5
            }
          },refuse:''//拒绝理由
          ,id:0//拒绝订单id
        }
      },created(){
        getOrderList4(this)
        getOrderList6(this)
        getOrderList5(this)
      },mounted(){
        var $ul003= $('.ul003>li');
        $ul003.click(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $ul003.index(this);
          $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
        });
      },
      methods:{
        //分页
        chekdpage(currentPage) {
          this.params.pageNum = currentPage//页码
          this.initmet();
        },
        //分页
        chekdpage1(currentPage) {
          this.parms1.num = currentPage//页码
          selectHavesentSingles(this);
        },
        //分页
        chekdpage2(currentPage) {
          this.parms1.num = currentPage//页码
          selectHavesentSingles(this);
        },updOrderState(id,state){
          updOrderState(this,id,state)
        },openRefuseOrder(id){//点击打开拒绝订单
          this.id=id;
          this.refuse='';
          $("#refuse").show();
        },closeRefuseOrder(){//点击关闭拒绝订单
          $("#refuse").hide();
        },refuseOrder() {//确认拒绝订单
          //alert(this.id+"**"+this.refuse)
          refuseOrder(this,this.id,this.refuse)
          $("#refuse").hide();
        },selectRefuse(id){//查看拒单理由
          getRefuseOrder(this,id)
          $("#selectRefuse").show();
        },closeRefuseOrders(){//关闭查看拒单理由
          $("#selectRefuse").hide();
        }
      }
    }
    //查看拒绝订单
    function getRefuseOrder(_this,id){
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/getOrder'
        ,params:{"id":id}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.refuse=ref.data.map.refuse;
        }
      })
    }
    //拒绝订单
    function refuseOrder(_this,id,refuse){
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/refuseOrder'
        ,params:{"refuse":refuse,"id":id}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.$alert("拒绝成功")
          getOrderList4(_this)
          getOrderList6(_this)
          getOrderList5(_this)
        }
      })
    }
    //修改订单状态
    function updOrderState(_this,id,state){
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/updOrderState'
        ,params:{"id":id,"state":state}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.$alert("接受成功")
          getOrderList4(_this)
          getOrderList6(_this)
          getOrderList5(_this)
        }
      })
    }
    //得到待接单订单集合
    function getOrderList4(_this){
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/list',
        params: _this.order.params
      }).then((res) => {
        console.log("待接单"+JSON.stringify(res.data))
        if(res.data.code == 200) {
          _this.order.map = res.data.orders
          //this.pageData.totalCount=res.data.total
          //this.pageData.curPage=this.params.pageNum
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
    //得到已接单订单集合
    function getOrderList6(_this){
      //查询资讯列表
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/list',
        params: _this.order1.params
      }).then((res) => {
        console.log("已接单"+JSON.stringify(res.data))
        if(res.data.code == 200) {
          _this.order1.map = res.data.orders
          //this.pageData.totalCount=res.data.total
          //this.pageData.curPage=this.params.pageNum
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
    //得到已拒单订单集合
    function getOrderList5(_this){
      //查询资讯列表
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/list',
        params: _this.order2.params
      }).then((res) => {
        console.log("已拒单"+JSON.stringify(res.data))
        if(res.data.code == 200) {
          _this.order2.map = res.data.orders
          //this.pageData.totalCount=res.data.total
          //this.pageData.curPage=this.params.pageNum
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
</script>

<style scoped>

</style>
