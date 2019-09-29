<template>
  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">商品详情</p>
        <!--<router-link href="###" class="db fr fs18 cor0" :to="{name:'buyerGoods'}">返回</router-link>-->
        <a href="javascript:void (0)" class="db fr fs18 cor0" @click="goBack">返回</a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb200 pdl50 pdr50">
        <div>
          <p class="fs18 cor0 bgf9 h50 lh50 pdl20 pdr20 btn-a7">基本信息<a href="javascript:void(0)" class="dinb fr fs12 cor0">折叠</a></p>
          <div class="btn-div pdt10 pdb60">
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">商品编号：</span>
              <div class="mgl150">{{goods.goodsNum}}</div>
            </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">商品名称：</span>
              <div class="mgl150">{{goods.goodsName}}</div>
            </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">品牌：</span>
              <div class="mgl150">{{goods.brandName}}</div>
           </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">商品产地：</span>
              <div class="mgl150">{{goods.origin}}</div>
             </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">商品毛重：</span>
              <div class="mgl150">{{goods.weight}}</div>
            </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">包装清单：</span>
              <div class="mgl150">{{goods.packingList}}</div>
            </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">所属供应商：</span>
              <div class="mgl150">{{goods.supName}}</div>
            </div>
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">商品分类：</span>
              <div class="mgl150">{{goods.cName}}</div>
            </div>

<!--
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品编号：</span>{{goods.goodsNum}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品名称：</span>{{goods.goodsName}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">品牌：</span>{{goods.brandName}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品产地：</span>{{goods.origin}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品毛重：</span>{{goods.weight}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">包装清单：</span>{{goods.packingList}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">所属供应商：</span>{{goods.supName}}</p>
            <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品分类：</span>{{goods.cName}}</p>-->
          </div>
        </div>
        <div>
          <p class="fs18 cor0 bgf9 h50 lh50 pdl20 pdr20 btn-a7">商品属性<a href="javascript:void(0)" class="dinb fr fs12 cor0">折叠</a></p>
          <div class="btn-div pdt10 pdb60" v-for="(item,index) in attrList">
            <div class="mgt20 ovh fs16">
              <span class="w150 db fl ">{{item.attrName}}：</span>
              <div class="mgl150">{{item.attrValue}}</div>
            </div>
          <!--  <p class="mgt20 fs16 cor0"><span class="w150 dinb">{{item.attrName}}：</span>{{item.attrValue}}</p>-->
          </div>
        </div>
        <div>
          <p class="fs18 cor0 bgf9 h50 lh50 pdl20 pdr20 btn-a7">销售信息<a href="javascript:void (0)" class="dinb fr fs12 cor0">折叠</a></p>
          <div class="btn-div pdt10 pdb60" >
            <table class="tab3" v-if="skuAttr.length>0">
              <tbody>
              <tr >
                <td class="pdt10 pdb10" v-for="(item,index) in skuAttr"><p class="t-c mgb0">{{item}}</p></td>
                <td class="pdt10 pdb10">价格（元）</td>
                <td class="pdt10 pdb10">数量</td>
              </tr>
              <tr v-for="(item,index) in skuDetail">
                <td class="pdt10 pdb10" v-for="(item,index) in item.attrValue">{{item}}</td>
                <td class="pdt10 pdb10">{{item.price}}</td>
                <td class="pdt10 pdb10">{{item.stock}}</td>
              </tr>
              </tbody>
            </table>
            <div v-if="skuAttr.length==0">
              <div class="mgt20 ovh fs16">
                <span class="w150 db fl ">商品单价：</span>
                <div class="mgl150">{{price}}</div>
              </div>
              <div class="mgt20 ovh fs16">
                <span class="w150 db fl ">商品数量：</span>
                <div class="mgl150">{{stock}}</div>
              </div>
           <!--   <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品单价：</span>{{price}}</p>
              <p class="mgt20 fs16 cor0"><span class="w150 dinb">商品数量：</span>{{stock}}</p>-->
            </div>
          </div>
        </div>
        <div>
          <p class="fs18 cor0 bgf9 h50 lh50 pdl20 pdr20 btn-a7">商品图片<a href="javascript:void (0)" class="dinb fr fs12 cor0">折叠</a></p>
          <div class="btn-div pdt20 pdb60 ovh">
            <div class="fl">
              <ul class="ovh" v-for="(item,index) in goodsPic">
                <li class="mgr20 fl mgt15"><img :src="item.url" alt=""></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>

</style>

<script>
  var _this = '';
  import $ from 'jquery';
  export default {
    name: "goodsDetail",
    data() {
      return {
        goods:{},
        attrList:[],
        skuAttr:[],
        skuNum:[],
        skuDetail:[],
        goodsPic:[],
        price:0.0,
        stock:0,
        status:0
      }
    },
    mounted(){
      $('.btn-a7').click(function(){
        if($('.btn-div').is(':visible')){
          $(this).next('.btn-div').slideToggle();
          $(this).addClass('active');
        }else{
          $(this).next('.btn-div').slideDown();
        }
      });
    },
    created(){
      var goodsId=this.$route.query.goodsId;
      _this.status=this.$route.query.status;
      Getgoods(goodsId);
    },beforeCreate(){
      _this = this;
    },
    methods: {
      goBack:function () {
        _this.$router.push({name: 'buyerGoods',query:{ status:_this.status}});
      }
    },
  }

  //商品基本信息
  function Getgoods(goodsId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/findGoodsDetail',
      params:{goodsId:goodsId}
    }).then((res) => {
      _this.goods=res.data;
      _this.attrList=res.data.attrList;
      _this.goodsPic=res.data.goodsPicList;
      var skuAttrList=res.data.skuDetailList;

      if(skuAttrList.length>0){
        for (var i=0;i<skuAttrList.length;i++){
        var index1=  $.inArray(skuAttrList[i].attrName,_this.skuAttr);
          if( index1<0 && skuAttrList[i].attrName!=undefined){
            _this.skuAttr.push(skuAttrList[i].attrName);
          }
          var index2=  $.inArray(skuAttrList[i].skuId,_this.skuNum);
          if(index2<0){
            _this.skuNum.push(skuAttrList[i].skuId);
            var skuObj={};
            skuObj.price=skuAttrList[i].price;
            _this.price=skuAttrList[i].price;
            skuObj.stock=skuAttrList[i].stock;
            _this.stock=skuAttrList[i].stock;
            skuObj.skuId=skuAttrList[i].skuId;
            var attrValue=[];
            for(var k=0;k<skuAttrList.length;k++){
              if(skuObj.skuId==skuAttrList[k].skuId){
                var  Value='';
                  if(skuAttrList[k].attrValue!=null){
                    Value=skuAttrList[k].attrValue;
                  }
                 attrValue.push(Value);
                }
            }
            skuObj.attrValue=attrValue;
            _this.skuDetail.push(skuObj);
          }
        }
      }
    })


  }

</script>
