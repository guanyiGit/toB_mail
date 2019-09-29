<template>
  <!--内容区域-->
  <div class="mgl170 pdt30 pdb30 pdr15 set pdl20 pdr20" style="width: 100%;margin-left: 0px">
    <p class="fs18 h28 lh28 cor0">我的订单</p>
    <ul class="ul003 mgt20">
      <li id="y0" class="active" @click.prevent="checkTab = 0"><a style="cursor:pointer;">全部订单</a></li>
      <li id="y1" @click.prevent="checkTab = 1"><a style="cursor:pointer;">待审核</a></li>
      <li id="y2" @click.prevent="checkTab = 2"><a style="cursor:pointer;">待审批</a></li>
      <li id="y3" @click.prevent="checkTab = 3"><a style="cursor:pointer;">待接单</a></li>
      <li id="y4" @click.prevent="checkTab = 4"><a style="cursor:pointer;">待发货</a></li>
      <li id="y5" @click.prevent="checkTab = 5"><a style="cursor:pointer;">待收货</a></li>
      <li id="y6" @click.prevent="checkTab = 6"><a style="cursor:pointer;">待评价</a></li>
      <li id="y7" @click.prevent="checkTab = 7"><a style="cursor:pointer;">已评价</a></li>
    </ul>

    <div class="mgt30">
      <ul class="ul0004">
        <!--全部订单1-->
        <li class="db">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms.month" @change="selectAuditApprovalOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms.input" @keyup.enter="selectAuditApprovalOrderss">
                  <span class="input-group-btn" @click.prevent="selectAuditApprovalOrderss">
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

          <div v-for="(item,index) in list" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <span class="fr fs12 cor6 aside3" v-if="item.status==0 || item.status==3"><a href="javascript:void(0)" class="dinb cor0289E8 mgl20" @click="getDetail(item)">预算执行明细</a></span>
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
                <td class="ww10 fb">{{item.status==0?'待审核':item.status==2?'驳回':item.status==3?'待审批':item.status==4?'待接单':item.status==5?'已拒单':item.status==6?'待发货':item.status==7?'待收货':item.status==8?'待评价':item.status==9?'已评价':item.status==10?'已取消':''}}</td>
                <td class="ww10">
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,0)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-primary mgt10" v-if="item.status==7" @click="updateOrderStatus(item.orderId,8,item.status)">确认收货</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==8">填写评价</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==9">查看评价</a>
                  <!--<router-link style="cursor:pointer;" class="db w60 btn btn-xs btn-success mgt10">催单</router-link>-->
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
                <td class="ww10 fb">{{item.status==0?'待审核':item.status==2?'驳回':item.status==3?'待审批':item.status==4?'待接单':item.status==5?'已拒单':item.status==6?'待发货':item.status==7?'待收货':item.status==8?'待评价':item.status==9?'已评价':item.status==10?'已取消':''}}</td>
                <td class="ww10">
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,0)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-primary mgt10" v-if="item.status==7" @click="updateOrderStatus(item.orderId,8,item.status)">确认收货</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==8">填写评价</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==9">查看评价</a>
                  <!--<router-link style="cursor:pointer;" class="db w60 btn btn-xs btn-success mgt10">催单</router-link>-->
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
          <div class="zhezhao dn"></div>
          <div class="yschuang dn ttt">
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

        </li>
        <!--待审核2-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms1.month" @change="selectAuditApprovalOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms1.input" @keyup.enter="selectAuditApprovalOrderss">
                  <span class="input-group-btn" @click.prevent="selectAuditApprovalOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,1)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,1)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
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

          <div class="zhezhao dn"></div>
          <div class="yschuang dn ttt">
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



        </li>

        <!--待审批3-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms2.month" @change="selectAuditApprovalOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms2.input" @keyup.enter="selectAuditApprovalOrderss">
                  <span class="input-group-btn" @click.prevent="selectAuditApprovalOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list2" :key="index">
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,2)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,2)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData2" @chekdpageCallcak="chekdpage2"></page-vue>
          </div>
          <!--分页END-->
          <div class="zhezhao dn"></div>
          <div class="yschuang dn ttt">
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

        </li>

        <!--待接单4-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms3.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms3.input" @keyup.enter="selectMyOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list3" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <!--<span class="fr fs12 cor6">本月预算超额：￥200.00 <a href="#" class="dinb cor0289E8 mgl20">预算执行明细</a></span>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,3)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,3)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-danger mgt10" v-if="item.status==0 || item.status==1 || item.status==2 || item.status==3" @click="updateOrderStatus(item.orderId,10,item.status)">取消订单</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData3" @chekdpageCallcak="chekdpage3"></page-vue>
          </div>
          <!--分页END-->

        </li>

        <!--待发货5-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms4.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms4.input" @keyup.enter="selectMyOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list4" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <!--<span class="fr fs12 cor6">本月预算超额：￥200.00 <a href="#" class="dinb cor0289E8 mgl20">预算执行明细</a></span>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,4)">订单详情</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,4)">订单详情</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData4" @chekdpageCallcak="chekdpage4"></page-vue>
          </div>
          <!--分页END-->

        </li>

        <!--待收货6-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms5.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms5.input" @keyup.enter="selectMyOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list5" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <!--<span class="fr fs12 cor6">本月预算超额：￥200.00 <a href="#" class="dinb cor0289E8 mgl20">预算执行明细</a></span>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,5)">订单详情</a>
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-primary mgt10" v-if="item.status==7" @click="updateOrderStatus(item.orderId,8,item.status)">确认收货</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,5)">订单详情</a>
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-primary mgt10" v-if="item.status==7" @click="updateOrderStatus(item.orderId,8,item.status)">确认收货</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData5" @chekdpageCallcak="chekdpage5"></page-vue>
          </div>
          <!--分页END-->

        </li>

        <!--待评价7-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms6.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms6.input" @keyup.enter="selectMyOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list6" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <!--<span class="fr fs12 cor6">本月预算超额：￥200.00 <a href="#" class="dinb cor0289E8 mgl20">预算执行明细</a></span>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,6)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==8">填写评价</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,6)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==8">填写评价</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData6" @chekdpageCallcak="chekdpage6"></page-vue>
          </div>
          <!--分页END-->

        </li>


        <!--已评价8-->
        <li class="dn">
          <div class="ovh mgt20">
            <div class="fl">
              <select class="form-control mgr30 ios0" v-model="parms7.month" @change="selectMyOrderss">
                <option value="36">全部订单</option>
                <option value="3">近三个月订单</option>
              </select>
            </div>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="订单号" v-model.trim="parms7.input" @keyup.enter="selectMyOrderss">
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
            <!--<p class="fl ww10 h50 lh50 cor0">总金额</p>-->
            <p class="fl ww10 h50 lh50 cor0">操作</p>
          </div>
          <div v-for="(item,index) in list7" :key="index">
            <div class="pdt20 pdb10 fs12 cor6 ovh pdl20">
              <p class="fl mgr20">{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</p>
              <p class="fl mgr20">订单号： {{item.orderNo}}</p>
              <p class="fl mgr20">{{item.company}}</p>
              <p class="fl">订单科目： {{item.subName}}</p>
              <!--<span class="fr fs12 cor6">本月预算超额：￥200.00 <a href="#" class="dinb cor0289E8 mgl20">预算执行明细</a></span>-->
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,7)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==9">查看评价</a>
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
                  <a style="cursor:pointer;" class="db w60 btn btn-xs btn-warning" @click="setOrderInfo(item,7)">订单详情</a>
                  <a style="cursor:pointer;" class="btn btn-sm btn-xs btn-primary mgt10" v-if="item.status==9">查看评价</a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w710">
            <page-vue :pageData="pageData7" @chekdpageCallcak="chekdpage7"></page-vue>
          </div>
          <!--分页END-->

        </li>
      </ul>
    </div>
  </div>
  <!--内容区域END-->
</template>

<script>
  import  conf from '../../common/config'
    var _this = '';
    export default {
        name: "myOrders",
      data(){
        return{
          checkTab:0,
          parms:{userId:null, orgId:null, status:null, input:null, month:36, num:1, size:2},
          parms1:{userId:null, orgId:null, status:0, input:null, month:36, num:1, size:2},
          parms2:{userId:null, orgId:null, status:3, input:null, month:36, num:1, size:2},
          parms3:{userId:null, orgId:null, status:4, input:null, month:36, num:1, size:2},
          parms4:{userId:null, orgId:null, status:6, input:null, month:36, num:1, size:2},
          parms5:{userId:null, orgId:null, status:7, input:null, month:36, num:1,size:2},
          parms6:{userId:null, orgId:null, status:8, input:null, month:36, num:1, size:2},
          parms7:{userId:null, orgId:null, status:9, input:null, month:36, num:1, size:2},
          parms8:{orgId:0, orderDate:'',subIdList:[]},
          list:[],//全部
          list1:[],//待审核
          list2:[],//待审批
          list3:[],//待接单
          list4:[],//待发货
          list5:[],//待收货
          list6:[],//待评价
          list7:[],//已评价
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
          pageData2:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData3:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData4:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData5:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData6:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
          pageData7:{
            curPage: 1,//当前页吗
            totalCount: 0,//总条数
            pageSize: 2//页面大小
          },
        }
      },
      mounted(){
        var $ul003= $('.ul003>li');
        $ul003.click(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $ul003.index(this);
          $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
        });
        // $('.aside3').click(function(){
        //   $('.zhezhao').show();
        //   $('.yschuang').show();
        // });
        $('.hiddd').click(function(){
          $('.zhezhao').hide();
          $('.yschuang').hide();
        });
        if (this.$route.params.tabl==0) {
          $("#y0").click();
        }else if (this.$route.params.tabl==1){
          $("#y1").click();
        } else if (this.$route.params.tabl==2){
          $("#y2").click();
        }else if (this.$route.params.tabl==3){
          $("#y3").click();
        }else if (this.$route.params.tabl==4){
          $("#y4").click();
        }else if (this.$route.params.tabl==5){
          $("#y5").click();
        }else if (this.$route.params.tabl==6){
          $("#y6").click();
        }else if (this.$route.params.tabl==7){
          $("#y7").click();
        }
      },
      created(){
        this.parms.userId=conf.userInfo.userId
        this.parms.orgId=conf.userInfo.orgId
        this.parms1.userId=conf.userInfo.userId
        this.parms1.orgId=conf.userInfo.orgId
        this.parms2.userId=conf.userInfo.userId
        this.parms2.orgId=conf.userInfo.orgId
        this.parms3.userId=conf.userInfo.userId
        this.parms3.orgId=conf.userInfo.orgId
        this.parms4.userId=conf.userInfo.userId
        this.parms4.orgId=conf.userInfo.orgId
        this.parms5.userId=conf.userInfo.userId
        this.parms5.orgId=conf.userInfo.orgId
        this.parms6.userId=conf.userInfo.userId
        this.parms6.orgId=conf.userInfo.orgId
        this.parms8.orgId=conf.userInfo.orgId
        selectAuditApprovalOrders(this);
      },beforeCreate(){
        _this = this;
      },
      methods:{
          setOrderInfo(item,tabl){
            sessionStorage.setItem('orderInfo',JSON.stringify(item));
            sessionStorage.setItem('namee','myOrders');
            sessionStorage.setItem('tabl',tabl);
            this.$router.push({name:'ps_orderDetail'});
          },
        selectAuditApprovalOrderss(){
          this.parms.num=1
          this.parms1.num=1
          this.parms2.num=1
          selectAuditApprovalOrders(this);
        },
        selectMyOrderss(){
          this.parms3.num=1
          this.parms4.num=1
          this.parms5.num=1
          this.parms6.num=1
          this.parms7.num=1
          selectMyOrders(this);
        },
        chekdpage(currentPage) {
          this.parms.num = currentPage//页码
          selectAuditApprovalOrders(this)
        },
        chekdpage1(currentPage) {
          this.parms1.num = currentPage//页码
          selectAuditApprovalOrders(this)
        },
        chekdpage2(currentPage) {
          this.parms2.num = currentPage//页码
          selectAuditApprovalOrders(this)
        },
        chekdpage3(currentPage) {
          this.parms3.num = currentPage//页码
          selectMyOrders(this)
        },
        chekdpage4(currentPage) {
          this.parms4.num = currentPage//页码
          selectMyOrders(this)
        },
        chekdpage5(currentPage) {
          this.parms5.num = currentPage//页码
          selectMyOrders(this)
        },
        chekdpage6(currentPage) {
          this.parms6.num = currentPage//页码
          selectMyOrders(this)
        },
        chekdpage7(currentPage) {
          this.parms7.num = currentPage//页码
          selectMyOrders(this)
        },
        updateOrderStatus(orderId,stat,status){
          if (status==0 || status==1 || status==2 || status==3) {
            this.$confirm('确定取消订单吗？').then(_ => {
              updateStatus(this,orderId,stat);
            }).catch(_ => {
            });
          }
          if (status==7) {
            this.$confirm('确定已收货了吗？').then(_ => {
              updateStatus(this,orderId,stat);
              this.$axios({
                url: '/biz/tob-order/biz/order/addAssets',
                params:  {orderId:orderId,orgId:conf.userInfo.orgId,userId:conf.userInfo.userId}
              }).then((res) => {
                if (res.data.code == 200) {
                  //alert("添加清单成功")
                }
              })
            }).catch(_ => {
            });
          }
        },
        getDetail:function (item) {
          $('.zhezhao').show();
          $('.ttt').show();
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
            this.parms2.num=1
            this.parms3.num=1
            this.parms4.num=1
            this.parms5.num=1
            this.parms6.num=1
            this.parms7.num=1
            if (newVal==0 || newVal==1 || newVal==2) {
              selectAuditApprovalOrders(this);
            }
            if (newVal==3 || newVal==4 || newVal==5 || newVal==6 || newVal==7) {
              selectMyOrders(this);
            }
          }
        }
      }
    }

    //待审核审批列表
    function selectAuditApprovalOrders(_this) {
      if (_this.parms.status==999) {
        _this.parms.status=null
      }
      var parmm=_this.parms
      if (_this.checkTab==0) {
        parmm=_this.parms
      }
      if (_this.checkTab==1) {
        parmm=_this.parms1
      }
      if (_this.checkTab==2) {
        parmm=_this.parms2
      }
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectAuditApprovalOrders',
        params:  parmm
      }).then((res) => {
        console.log(res)
        if (res.data.code == 200 && res.data.list!=null) {
          if (_this.checkTab==0){
            _this.list=res.data.list
            _this.pageData.curPage=_this.parms.num
            _this.pageData.totalCount=res.data.count
          }
          if (_this.checkTab==1){
            _this.list1=res.data.list
            _this.pageData1.curPage=_this.parms1.num
            _this.pageData1.totalCount=res.data.count
          }
          if (_this.checkTab==2){
            _this.list2=res.data.list
            _this.pageData2.curPage=_this.parms2.num
            _this.pageData2.totalCount=res.data.count
          }
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
//我的订单列表
    function selectMyOrders(_this) {
      var parmm=null
      if (_this.checkTab==3) {
        parmm=_this.parms3
      }
      if (_this.checkTab==4) {
        parmm=_this.parms4
      }
      if (_this.checkTab==5) {
        parmm=_this.parms5
      }
      if (_this.checkTab==6) {
        parmm=_this.parms6
      }
      if (_this.checkTab==7) {
        parmm=_this.parms7
      }
      _this.$axios({
        method: 'get',
        url: '/biz/tob-order/biz/order/selectMyOrders',
        params:  parmm
      }).then((res) => {
        if (res.data.code == 200 && res.data.list!=null) {
          if (_this.checkTab==3){
            _this.list3=res.data.list
            _this.pageData3.curPage=_this.parms3.num
            _this.pageData3.totalCount=res.data.count
          }
          if (_this.checkTab==4){
            _this.list4=res.data.list
            _this.pageData4.curPage=_this.parms4.num
            _this.pageData4.totalCount=res.data.count
          }
          if (_this.checkTab==5){
            console.log("确认收货订单信息"+JSON.stringify(res.data.list))
            _this.list5=res.data.list
            _this.pageData5.curPage=_this.parms5.num
            _this.pageData5.totalCount=res.data.count
          }
          if (_this.checkTab==6){
            _this.list6=res.data.list
            _this.pageData6.curPage=_this.parms6.num
            _this.pageData6.totalCount=res.data.count
          }
          if (_this.checkTab==7){
            _this.list7=res.data.list
            _this.pageData7.curPage=_this.parms7.num
            _this.pageData7.totalCount=res.data.count
          }
        }
      }).catch((err)=>{
        console.log(err);
      });
    }

    //修改订单状态
  function updateStatus(_this,orderId,status) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-order/biz/order/updateOrderStatus',
      params:  {orderId:orderId,status:status}
    }).then((res) => {
      console.log(res)
      if (res.data.code == 200 && res.data.string=="修改订单状态成功") {
        _this.$alert("操作成功！");
        selectAuditApprovalOrders(_this);
        selectMyOrders(_this);
      }
    }).catch((err)=>{
      console.log(err);
    });
  }


    //预算明细
    function getDetail(e) {
      _this.parms8.subIdList=[]
      _this.parms8.orderDate=e.createTime;
      _this.parms8.subIdList.push(e.subId)
      // if(e.isUseful==0 && e.childOrders!=null){
      //   var childOrders=e.childOrders;
      //   for(var i=0;i<childOrders.length;i++){
      //     var goodss=childOrders[i].goodss;
      //     if(goodss!=null){
      //       for(var k=0;k<goodss.length;k++){
      //         var index1=  $.inArray(goodss[k].subjects,_this.parms8.subIdList);
      //         if(index1<0){
      //           _this.parms8.subIdList.push(goodss[k].subjects);
      //         }
      //       }
      //     }
      //   }
      // }else{
      //   var goodss=e.goodss;
      //   if(goodss!=null){
      //     for(var j=0;j<goodss.length;j++){
      //       var index2=  $.inArray(goodss[j].subjects,_this.parms8.subIdList);
      //       if(index2<0){
      //         _this.parms8.subIdList.push(goodss[j].subjects);
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
        data: JSON.stringify(_this.parms8)
      }).then((res) => {
        console.log(res)
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
