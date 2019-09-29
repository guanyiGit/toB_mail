<template>
  <div>
    <swiper></swiper>
    <!--采购排行（旧）-->
    <div class="ww100 pdl15 pdr15 mgt60 dn">
      <div class="w1200 c-b mm100">
        <div class="ovh bor-b-ED145B">
          <div class="fl">
            <p class="fs18 cor101010 fb pdl35 ios21">采购排行</p>
          </div>
          <div class="fr tab1">
            <a href="javascript:;" class="db fl corED145B mgl40 active" @click.prevent="checkPurchaseInd(index)" v-for="(item,index) in  levList" :key="index">{{item.cname}}</a>
          </div>
        </div>
        <div>
          <!--办公用品111-->
          <div class="tab2 db">
            <ul class="ul-3 ovh">
              <li class="ovh">
                <div class="db fl pdl45 pdr45 ovh" v-for="(item,index) in list" :key="index" v-if="index < 2">
                  <div class="fl pdt50 ww50">
                    <p class="fs24 corED145B">{{'¥' + item.skuDOList[0].price}}</p>
                    <p class="fs36 cor101010 itemName">{{item.goodsName}}</p>
                    <p class="fs12 cor9 mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                    <div class="ovh mgt10">
                      <a href="javascriptL:;" class="db fl w120 h40 lh40 t-c corED145B bor-ED145B ras4 mgr25 btn-a3 time05" @click.prvent="$parent.addCart(item)">加入购物车</a>
                      <a href="javascriptL:;" @click.prvent="$message('功能暂未开放')" class="db fl pdt10 mgr20"><img src="/static/images/41.jpg" alt=""></a>
                      <a href="javascriptL:;" @click.prvent="$message('功能暂未开放')" class="db fl pdt10"><img src="/static/images/42.jpg" alt=""></a>
                    </div>
                  </div>
                  <div class="fr pdt30 ww50" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                    <a href="javascript:;" class="db picfd">
                      <!-- <img src="/static/images/43-1.jpg" alt="" class="c-b time05 imi1"> -->
                      <img class="c-b time05 imi1" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                    </a>
                  </div>
                </div>
              </li>
             <li class="ovh">
               <div class="db fl t-c rel ovh" v-for="(item,index) in list" :key="index" v-if="index >= 2 && index < 6">
                 <div class="ovh div4" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                  <img class="db c-b ww100 time05 imi2" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt=""></div>
                   <p class="cor101010 fb itemName">{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + item.skuDOList[0].price}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
              <li class="ovh">
               <div class="db fl t-c rel ovh" v-for="(item,index) in list" :key="index" v-if="index >= 6 && index < 10">
                 <div class="ovh div4" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                  <img class="db c-b ww100 time05 imi2" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt=""></div>
                  <p class="fs12 cor9 mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + item.skuDOList[0].price}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <!--办公用品111END-->
        </div>
      </div>
    </div>
    <!--采购排行end-->
    
    <!--最新上架(旧)-->
    <div class="ww100 pdl15 pdr15 pdb96 mgt60 dn">
      <div class="w1200 c-b mm100">
        <div class="ovh bor-b-ED145B">
          <div class="fl">
            <p class="fs18 cor101010 fb pdl35 ios22">最新上架</p>
          </div>
          <div class="fr tab1">
            <a href="javascript:;" class="db fl corED145B mgl40 active" @click.prevent="checkDateInd(index)" v-for="(item,index) in  levList" :key="index">{{item.cname}}</a>
          </div>
        </div>
        <div>
          <!--办公用品111-->
          <div class="tab2 db">
            <ul class="ul-3 ovh">
              <li class="ovh">
                <div class="db fl pdl45 pdr45 ovh" v-for="(item,index) in list2" :key="index" v-if="index < 2">
                  <div class="fl pdt50 ww50">
                    <p class="fs24 corED145B">{{'¥' + item.skuDOList[0].price}}</p>
                    <p class="fs36 cor101010 itemName">{{item.goodsName}}</p>
                    <p class="fs12 cor9 mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                    <div class="ovh mgt10">
                      <a href="javascript:;" class="db fl w120 h40 lh40 t-c corED145B bor-ED145B ras4 mgr25 btn-a3 time05" @click.prvent="$parent.addCart(item)">加入购物车</a>
                      <a href="javascript:;"@click.prvent="$message('功能暂未开放')" class="db fl pdt10 mgr20"><img src="/static/images/42.jpg" alt=""></a>
                      <a href="javascript:;" @click.prvent="$message('功能暂未开放')" class="db fl pdt10"><img src="/static/images/42.jpg" alt=""></a>
                    </div>
                  </div>
                  <div class="fr pdt30 ww50" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                    <a href="javascript:;" class="db picfd"><img class="c-b time05 imi1" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt=""></a>
                  </div>
                </div>
              </li>
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list2" :key="index" v-if="index >= 2 && index < 6">
                  <div class="ovh div4"  @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})"><img class="db c-b ww100 time05 imi2" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt=""></div>
                  <p class="cor101010 fb itemName">{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + item.skuDOList[0].price}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list2" :key="index" v-if="index >= 6 && index < 10">
                  <div class="ovh div4"  @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})"><img class="db c-b ww100 time05 imi2" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt=""></div>
                  <p class="cor101010 fb itemName">{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + item.skuDOList[0].price}}</p>
                   <div class="db abs z10 btna4 time05">
                      <div class="w186 ovh c-b">
                        <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                          <img class="db" src="/static/images/63-1.png" alt="">
                        </a>
                        <a href="javascript:;" class="db fl hov7 w38">
                          <img class="db" src="/static/images/63-2.png" alt="">
                        </a>
                        <a href="javascript:;" class="db fl hov7 w38">
                          <img class="db" src="/static/images/63-3.png" alt="">
                        </a>
                      </div>
                  </div> 
                </div>
              </li>
            </ul>
          </div>
          <!--办公用品111END-->

        </div>
      </div>
    </div>
    <!--最新上架END-->

    <!--采购排行-->
    <div class="ww100 pdl15 pdr15 mgt60">
      <div class="w1200 c-b mm100">
        <div class="ovh bor-b-ED145B">
          <div class="fl">
            <p class="fs18 cor101010 fb pdl35 ios21">采购排行</p>
          </div>
          <div class="fr tab1">
            <a href="javascript:;" class="db fl corED145B mgl40 active" @click.prevent="checkPurchaseInd(index)" v-for="(item,index) in  levList" :key="index">{{item.cname}}</a>
          </div>
        </div>
        <div>
          <!--办公用品111-->
          <div class="tab2 db">
            <ul class="ul-3 ovh">
              <!-- 首排 -->
              <li class="ovh">
                <div class="db rel fl pdl45 pdr45 ovh" v-for="(item,index) in list" :key="index" v-if="index < 2">
                  <div class="rel z2 pdt50 ww50 t-l">
                    <p class="fs24 corw">{{'¥' +  $options.filters.numFilter(item.skuDOList[0].price)}}</p>
                    <p class="fs36 corw itemName" style='width: 208px;margin:0;'>{{item.goodsName}}</p>
                    <p class="fs12 corw mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                    <div class="ovh mgt10">
                      <a href="javascript:;" class="db fl w115 h40 lh40 t-c corED145B bor-ED145B ras4 mgr20 btn-a3 time05" @click.prvent="$parent.addCart(item)">加入购物车</a>
                      <a href="javascript:;" class="db fl pdt10 mgr15" @click.prvent="$message('功能暂未开放')"><img src="/static/images/80.png" alt=""></a>
                      <a href="javascript:;" class="db fl pdt10" @click.prvent="$message('功能暂未开放')"><img src="/static/images/81.png" alt=""></a>
                    </div>
                  </div>
                  <div class="hou-tian-jia abs ovh " style="width:100%;height:291px;border: solid 1px #eee; display: flex; align-items: center; justify-content: center;">
                    <img style="width:90%;height:90%;" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="" class="db ww100 time05 cursor" >
                  </div>
                </div>
              </li>
              <!-- 第二排 -->
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list" :key="index" v-if="index >=2 && index < 6">
                  <div class="ovh div4" style="width: 100%; height: 322px; border: 1px solid #eee;display: flex;justify-content: center;align-items: center;">
                    <!-- border: solid 1px #000; -->
                    <img @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})" class="db c-b ww100 time05 cursor" style="width:90%;height:90%;" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                  </div>
                  <p class="cor101010 fb itemName" style='width: 208px;'>{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + $options.filters.numFilter(item.skuDOList[0].price)}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
              <!-- 第三排 -->
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list" :key="index" v-if="index >=6 && index < 10">
                  <div class="ovh div4" style="width: 100%; height: 322px; border: 1px solid #eee;display: flex;justify-content: center;align-items: center;">
                    <!-- border: solid 1px #000; -->
                    <img @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})" class="db c-b ww100 time05 cursor" style="width:90%;height:90%;" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                  </div>
                  <p class="cor101010 fb itemName" style='width: 208px;'>{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + $options.filters.numFilter(item.skuDOList[0].price)}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <!--办公用品111END-->

        </div>
      </div>
    </div>
    <!--采购排行end-->

    <!--最新上架-->
    <div class="ww100 pdl15 pdr15 pdb96 mgt60">
      <div class="w1200 c-b mm100">
        <div class="ovh bor-b-ED145B">
          <div class="fl">
            <p class="fs18 cor101010 fb pdl35 ios22">最新上架</p>
          </div>
          <div class="fr tab1">
            <a href="javascript:;" class="db fl corED145B mgl40 active" @click.prevent="checkDateInd(index)" v-for="(item,index) in  levList" :key="index">{{item.cname}}</a>
          </div>
        </div>
        <div>
          <!--办公用品111-->
          <div class="tab2 db">
            <ul class="ul-3 ovh">
              <li class="ovh">
                <div class="db fl pdl45 pdr45 ovh" style="border: solid 1px #eee;" v-for="(item,index) in list2" :key="index" v-if="index < 2">
                  <div class="fl pdt50 ww50">
                    <p class="fs24 corED145B">{{'¥' + $options.filters.numFilter(item.skuDOList[0].price) }}</p>
                    <p class="fs36 cor101010 itemName">{{item.goodsName}}</p>
                    <p class="fs12 cor9 mgt15">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                    <div class="ovh mgt10">
                      <a href="javascript:;" class="db fl w120 h40 lh40 t-c corED145B bor-ED145B ras4 mgr25 btn-a3 time05" @click.prvent="$parent.addCart(item)">加入购物车</a>
                      <a href="javascript:;"@click.prvent="$message('功能暂未开放')" class="db fl pdt10 mgr20"><img src="/static/images/42.jpg" alt=""></a>
                      <a href="javascript:;" @click.prvent="$message('功能暂未开放')" class="db fl pdt10"><img src="/static/images/42.jpg" alt=""></a>
                    </div>
                  </div>
                  <div class="fr pdt30 ww50" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                    <a href="javascript:;" class="db picfd">
                      <img class="c-b time05 imi1 cursor" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                    </a>
                  </div>
                </div>
              </li>
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list2" :key="index" v-if="index >= 2 && index < 6">
                  <div class="ovh div4" style="width: 100%; height: 322px; border: 1px solid #eee;display: flex;justify-content: center;align-items: center;"  @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                    <img style="width:90%;height:90%;" class="db c-b ww100 time05 cursor" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                  </div>
                  <p class="cor101010 fb itemName">{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + $options.filters.numFilter(item.skuDOList[0].price)}}</p>
                  <div class="db abs z10 btna4 time05">
                    <div class="w186 ovh c-b">
                      <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                        <img class="db" src="/static/images/63-1.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-2.png" alt="">
                      </a>
                      <a href="javascript:;" class="db fl hov7 w38">
                        <img class="db" src="/static/images/63-3.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </li>
              <li class="ovh">
                <div class="db fl t-c rel ovh" v-for="(item,index) in list2" :key="index" v-if="index >= 6 && index < 10">
                  <div style="width: 100%; height: 322px; border: 1px solid #eee;display: flex;justify-content: center;align-items: center;" class="ovh div4"  @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                    <img style="width:90%;height:90%;" class="db c-b ww100 time05 cursor" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
                  </div>
                  <p class="cor101010 fb itemName">{{item.goodsName}}</p>
                  <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</p>
                  <p class="fs24 corED145B mgb20">{{'¥' + $options.filters.numFilter(item.skuDOList[0].price)}}</p>
                   <div class="db abs z10 btna4 time05">
                      <div class="w186 ovh c-b">
                        <a href="javascript:;" class="db fl hov7 w110" @click="$parent.addCart(item)">
                          <img class="db" src="/static/images/63-1.png" alt="">
                        </a>
                        <a href="javascript:;" class="db fl hov7 w38">
                          <img class="db" src="/static/images/63-2.png" alt="">
                        </a>
                        <a href="javascript:;" class="db fl hov7 w38">
                          <img class="db" src="/static/images/63-3.png" alt="">
                        </a>
                      </div>
                  </div> 
                </div>
              </li>
            </ul>
          </div>
          <!--办公用品111END-->

        </div>
      </div>
    </div>
    <!--最新上架END-->

  </div>
</template>
<script>
  let params1 = {
    pageNum:1,
    limit:12,
    type:1
  }
  let params2 = {
    pageNum:1,
    limit:12,
    type:2
  }
  import swiper from './swiper'
  export default {
    name: "productList",
    components: {
      swiper
    },
    data (){
      return {
        list:[],//采购排行
        list2:[],//时间排行
      }
    },
    props:[
      "levList",//级别
      "activeTabInd"//tab坐标
    ],
    methods : {
      //切换采购排行index
      checkPurchaseInd(index) {
        if(index >= 0){
          let curObj = this.levList[index];
          params1.cid = curObj.cid;
        }else{
          delete params1.cid
        }
        //商品采购排行
        purchaseInitData.call(this)
      },
      //切换时间排行index
      checkDateInd(index){
        if(index >= 0){
          let curObj = this.levList[index];
          params2.cid = curObj.cid;
        }else{
          delete params2.cid
        }
        //商品时间排行
        dateInitData.call(this)
      },
    },
    created(){
      //商品采购排行
      purchaseInitData.call(this)
      //商品时间排行
      dateInitData.call(this)
 
    }
  }
  
  

  //商品类别
  function categorys() {
   this.$axios({
      url:'/biz/tob-mall/pruduct/categorys',
      params:{level:0}//顶级级别
    }).then(res=>{
      if(res.data.success){
        this.levList = res.data.obj;
      }
    })
  }

  //采购排行
  function purchaseInitData() {
    this.$axios({
      url:'/biz/tob-mall/pruduct',
      params:params1
    }).then(res=>{
      if(res.data.success){
        this.list = res.data.obj.list;
      }

    })
  }
  //时间排行
  function dateInitData() {
    this.$axios({
      url:'/biz/tob-mall/pruduct',
      params:params2
    }).then(res=>{
      if(res.data.success){
        this.list2 = res.data.obj.list;
      }

    })
  }
</script>

<style scoped>
.itemName{
  width: 240px;     
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
  margin: 0 auto;
}
.imi1{
  width: 221px;
  height: 221px;
  border:solid 1px #ddd;
  /*background-color: #eee;*/
}
.imi2{
  border:solid 1px #ddd;
  /*background-color: #eee;*/
  width: 290px;
  height: 322px;
}
</style>
