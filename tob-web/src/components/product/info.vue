<template>

<div class="pdl15 pdr15">
  <div class="w1200 c-b mm100 pdb110">
    <div class="bgw mgt30 pdt40 pdb30 pdl40 pdr60 ovh">
      <div class="fl w500">
        <div>
          <div id="preview" class="spec-preview bg-danger">
            <!-- <div style="width: 100%;height: 100%;border: solid 1px red;">
                <pic-zoom :url="mainJqImg" :scale="2" :big-url='mainJqImg' :show-edit="true" style="height: 100%;
    width: auto;"></pic-zoom>
            </div> -->
            <span class="jqzoom">
              <img :jqimg="mainJqImg" :src="mainJqImg" />
            </span>
          </div>

          <div class="spec-scroll"> <a class="prev">&lt;</a> <a class="next">&gt;</a>
            <div class="items">
              <ul>
                <li v-for="(item,index) in mianImgs" :key="index">
                  <img style="width: 61px;" class="cursor" :jqimg="item.mediaInfoDO?item.mediaInfoDO.url:''" :src="item.mediaInfoDO?item.mediaInfoDO.url:''" @mouseenter="preview(item.mediaInfoDO)"/>
                </li>
              </ul>
            </div>
          </div>
          <!--缩图结束-->
        </div>
      </div>
      <!--
      <div class="fr w560">
        <p class="fs16 cor0 fb">{{obj.goodsVO?obj.goodsVO.goodsName:''}}</p>
        <div class="hrd"></div>

        <p class="cor0 mgt20">单价：
          <span class="fs24 mgl15">￥{{obj.price | numFilter(2)}}</span>
        </p>
        <p class="cor0">供应商：<span class="mgl15 cor6">{{obj.goodsVO && obj.goodsVO.supplierDO ? obj.goodsVO.supplierDO.supName:''}}</span></p>
        <div class="hrd mgt20"></div>
        <div class="ovh mgt20">
          <div class="i_box fl" style="height: 40px;border: 1px solid #ddd;">
            <a href="javascript:;" class="J_add hov7">
              <img src="/static/images/25-1.png" alt="" class="imgi" @click="++cartNum">
            </a>
            <input type="text" v-model="cartNum" class="J_input t-l" style="width: 58px; height: 36px;line-height: 36px;text-align: left; border: none;">
            <a href="###" class="J_minus hov7" style="height: 16px;right: 10px;bottom: -3px;">
              <img src="/static/images/25-2.png" class="imgi" alt="" @click="--cartNum">
            </a>
          </div>
          <a href="###" class="db fl w162 h40 lh40 bgED145B t-c corw mgl10"><img class="mgr10" src="/static/images/49-2.png" alt="">加入购物车</a>
          <a href="###" class="db fl h40 lh40 ios13 pdl25 cor0 mgl25">收藏</a>
          <a href="###" class="db fl h40 lh40 ios27 pdl28 cor0 mgl25">对比</a>
        </div>
        <div class="mgt15 fs12 cor9">采购量：<span>{{obj.totalNum?obj.totalNum:'0'}}</span>  |  浏览量：<span>{{obj.viewNum?obj.viewNum:'0'}}</span></div>
      </div>
      -->
      <div class="fr w560">
        <p class="fs16 cor0 fb">{{obj.goodsVO?obj.goodsVO.goodsName:''}}</p>
        <div class="hrd"></div>
        <div class="ovh mgt13" v-for="(item,index) in attrAll" :key="index">
          <p class="fl h32 lh32 cor0 w80">{{item.attrName?item.attrName+':':''}}</p>
          <a href="javascript:;" class="db fl mgr10 w60 h32 lh32 t-c bor-d cor9 but-1"
          :class="{'active':isAvIdActive(e.avId)}" @click.prevent="checkAttrVal(item,e)"
          v-for="(e,i) in item.voAttrVals" :key="i">{{e.attrValue}}</a>
        </div>
        <p class="cor0 mgt20">单价：
          <span class="fs24 mgl15" >￥{{ price | numFilter(2)}}</span>
          <!-- <span class="fs24 mgl15" v-else>￥0.00</span> -->
        </p>
        <p class="cor0 mat10">供应商：<span class="mgl15 cor6">{{obj.goodsVO && obj.goodsVO.supplierDO ? obj.goodsVO.supplierDO.supName:''}}</span></p>
        <div class="hrd mgt20"></div>
        <div class="ovh mgt20">
          <div class="i_box fl" style="width: 90px;height: 40px;border: 1px solid #ddd;">
            <a href="javascript:;" class="J_add hov7" style="width: 10px;height: 13px;top: -4px;right: 13px;">
              <img src="/static/images/25-1.png" class="imi" alt="" @click="++cartNum">
            </a>
            <input type="text" value="0" v-model="cartNum" @blur="checkCartNum" class="J_input t-l" style="width: 58px; height: 36px;line-height: 36px;text-align: left; border: none;">
            <a href="javascript:;" class="J_minus hov7" style="width: 10px;height: 13px;right: 13px;bottom: 3px;">
              <img src="/static/images/25-2.png" class="imi" alt="" @click="cartMiuns">
            </a>
          </div>
          <a href="javascript:;"
              @click.prevent="$parent.addCart({
                'goodsId':obj.goodsId,
                'supId':obj.goodsVO.supId,
                'skuDOList':[{
                  'skuId':obj.skuId,
                  'price':obj.price
                }]
              },cartNum)" class="db fl w162 h40 lh40 bgED145B t-c corw mgl10"><img class="mgr10" src="images/49-2.png" alt="">加入购物车</a>
          <a href="###" class="db fl h40 lh40 ios13 pdl25 cor0 mgl25">收藏</a>
          <a href="###" class="db fl h40 lh40 ios27 pdl28 cor0 mgl25">对比</a>
        </div>
        <div class="mgt15 fs12 cor9">采购量：<span>{{obj.totalNum?obj.totalNum:'0'}}</span>  |  浏览量：<span>{{obj.viewNum?obj.viewNum:'0'}}</span></div>
      </div>

    </div>


    <div class="mgt30 bgw pdb50">
      <div class="ovh bor-b-ED145B pdr10">
        <a href="#" class="db fr w120 pdt10 pdb10 h60"><img src="/static/images/56.png" alt=""></a>
        <div class="fl mgr130 ovh tab-div">
          <a href="javascript:;" @click.prvent="childInfoTab = 0" class="db fl h60 lh60 fs16 cor101010 pdl30 pdr30"
          :class="{active:childInfoTab == 0}">商品详情</a>
          <a href="javascript:;" @click.prvent="childInfoTab = 1" class="db fl h60 lh60 fs16 cor101010 pdl30 pdr30"
          :class="{active:childInfoTab == 1}">商品图片</a>
          <a href="javascript:;" @click.prvent="childInfoTab = 2" class="db fl h60 lh60 fs16 cor101010 pdl30 pdr30"
          :class="{active:childInfoTab == 2}">售后保障</a>
          <a href="javascript:;" @click.prvent="childInfoTab = 3" class="db fl h60 lh60 fs16 cor101010 pdl30 pdr30"
          :class="{active:childInfoTab == 3}">商品评价</a>
        </div>
      </div>
      <div class="pdl40 pdr40 pdt30">
        <!--商品详情-->
        <div class="tab-div2 db" v-show="childInfoTab == 0">
          <div class="h40 lh40 bge pdl30 corED145B">商品详情</div>
          <div class="mgt20 pdl30">
            <div class="fs16 cor3">品牌：{{goodsBrand.brandEnName}}</div>
            <table class="ww100">
               <tbody>
                 <tr>
                    <td>
                      <p class="cor3 fs16">商品名称：{{obj.goodsVO?obj.goodsVO.goodsName:'-'}}</p>
                    </td>
                    <td>
                      <p class="cor3 fs16">商品编号：{{obj.goodsVO?obj.goodsVO.goodsNum:'-'}}</p>
                    </td>
                    <td>
                      <!-- <p class="cor3 fs16">内存容量：4G</p> -->
                    </td>
                    <td>
                       <!-- <p class="cor3 fs16">处理器：Intel i5低功耗版</p> -->
                    </td>
                 </tr>
                 <tr v-for="(item,index) in goodAttrVos" :key="index">
                    <td>
                      <p class="cor3 fs16">
                        {{item.attrValueVo && item.attrValueVo.attrDO ? item.attrValueVo.attrDO.attrName + "：":"" }}
                        {{ item.attrValueVo ? item.attrValueVo.attrValue :"" }}
                      </p>
                    </td>
                 </tr>
               </tbody>
            </table>
          </div>
          <div class="h40 lh40 bge pdl30 corED145B mgt30">商品图片</div>
          <div class="mgt30">
            <img class="c-b" v-for="(item,index) in goodsInfoImgs" :key="index" :src="item.mediaInfoDO?item.mediaInfoDO.url:''" alt="">
          </div>
         <!--
         <div class="h40 lh40 bge pdl30 corED145B mgt30">售后保障</div>
          <div class="pdt30">
            <div class="fs16 cor0">厂家服务</div>
            <div class="cor9 lh36">本产品全国联保，享受三包服务，质保期为：三年质保<br>如因质量问题或故障，凭厂商维修中心或特约维修点的质量检测证明，享受7日内退货，15日内换货，15日以上在质保期内享受免费保修等三包服务！</div>
            <div class="fs16 cor0 mgt20">全国联保</div>
            <div class="cor9 lh36">
              凭质保证书及发票，可享受全国联保服务，与您亲临商场选购的商品享受相同的质量保证。
            </div>
          </div>
        -->
        </div>
        <!--商品详情END-->

        <!--商品图片-->
        <div class="tab-div2" v-show="childInfoTab == 1">
           <div class="mgt30">
            <img class="c-b" v-for="(item,index) in goodsInfoImgs" :key="index" :src="item.mediaInfoDO?item.mediaInfoDO.url:''" alt="">
          </div>
        </div>
        <!--商品图片END-->

        <!--售后保障-->
        <div class="tab-div2" v-show="childInfoTab == 2">
          售后保障
        </div>
        <!--售后保障END-->

        <!--商品评价-->
        <div class="tab-div2" v-show="childInfoTab == 3">
          <table width="100%;" class="table">
            <thead>
              <tr height="50px;">
                <th width="25%">评论类型</th>
                <th width="25%">评论内容</th>
                <th width="25%">评论人</th>
                <th width="25%">评论时间</th>
              </tr>
            </thead>
            <tbody>
              <tr height="30px;" v-for="(item,index) in obj.evaluationDOS">
                <td>
                  {{
                    item.type && item.type == 1 ? "好评" :
                    item.type && item.type == 2 ? "中评" :
                    item.type && item.type == 3 ? "差评" : "其他"
                  }}
                </td>
                <td>{{item.content?item.content:""}}</td>
                <td>{{item.userName?item.userName:""}}</td>
                <td v-if="item.createTime">{{item.createTime | formatDate}}</td>
                <td v-else>---</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!--商品评价END-->
      </div>
    </div>
  </div>
</div>


</template>
<script>

import configJS from '@/common/config'
import '../../../static/js/base.js'
import '../../../static/js/jquery.jqzoom.js'
import '../../../static/js/jquery.jqzoom-core.js'
import '../../../static/css/jqzoom.css'

import PicZoom from 'vue-piczoom'

  export default {
    name:'prodectInfo',
    data() {
      return {
        skuId:'',
        obj:'',
        mianImgs:[],//主图片
        cartNum:1,//购物车数量
        childInfoTab:0,//默认展示第一个子tab
        selectSku:{//选择的sku
            skuId:0,
            skuName:'',
            price:0,
            attrList:[{
                attrId:0,
                attrName:'',
                avId:0,
                attrValue:''
            }]
        },
        attrAll:'',//所有sku
        skuAttrs:'',//sku所有sku
        defaultAttr:'',//默认数据
      }
    },
    watch:{
    },
    components: {
     PicZoom
    },
    methods: {
      preview(item){
          const url = item.url;
          $("#preview .jqzoom img").attr("src",url);
          $("#preview .jqzoom img").attr("jqimg",url);
      },
      //购物车相减
      cartMiuns(){
        if(this.cartNum > 1){
          this.cartNum = this.cartNum -1 ;
        }else{
          this.$message("不能再减了！")
        }
      },
      //切换商品sku
      checkAttrVal(item,attrVal){
        const _this  = this;
        let attrId = attrVal.attrId;
        let avId = attrVal.avId;

        //选择了“正在选择”的
        if(this.selectSku.attrList.some(x=>x.avId === avId && x.attrId === attrId)){
          return;
        }

        //查找 skuAttrs 所在index
        let skuAttrsIndexs = {};
        let bl = _this.skuAttrs.some((x,skuInd)=>{
          skuAttrsIndexs.skuInd = skuInd;//sku index
           return x.attrVOS.some((y,aInd)=>{
              skuAttrsIndexs.aInd = aInd;// attr index
              if(y.attrId === attrId){
                  return y.attrValueDOS.some((z,avInd)=>{
                    skuAttrsIndexs.avInd = avInd; // attrValue index
                    if(z.avId === avId){
                      return true;
                    }
                  })
              }
           })
        })
        if(bl){
          //判断是否需要强制跳
          let temp2 = {attrList:[]};
          bl = _this.skuAttrs.find((x,skuInd)=>{
            if(skuInd === skuAttrsIndexs.skuInd){
              return x.attrVOS.find((y,aInd)=>{
                if(aInd != skuAttrsIndexs.aInd){//sku 其他的属性
                  return y.attrValueDOS.find((z,zvInd)=>{
                    if(_this.selectSku.attrList.some(e=>e.attrId === y.attrId && e.avId === z.avId)){
                      return true;
                    }else{//没有属性自动该表属性选项
                      temp2.skuId = x.skuId;
                      temp2.skuName = x.skuName;
                      temp2.price = x.price;

                      if(temp2.attrList.length == 0){//第一个位置添加选择
                        let {attrId,attrName} = _this.skuAttrs[skuAttrsIndexs.skuInd].attrVOS[skuAttrsIndexs.aInd];
                        let {avId,attrValue} = _this.skuAttrs[skuAttrsIndexs.skuInd].attrVOS[skuAttrsIndexs.aInd].attrValueDOS[skuAttrsIndexs.avInd];
                        temp2.attrList.push({
                          attrId,
                          attrName,
                          avId,
                          attrValue
                        })
                      }

                      temp2.attrList.push({
                        attrId:y.attrId,
                        attrName:y.attrName,
                        avId:z.avId,
                        attrValue:z.attrValue
                      })
                    }
                  })
                }
              })
            }
          })

          // console.log("result",bl,temp2)
          if(bl && bl.skuId){//不需要跳
            const {skuId,skuName,price} = bl;
            const sku = {
                skuId,
                skuName,
                price,
                attrList:[]
            }
            sku.attrList = bl.attrVOS.map(x=>{
              const {attrId,attrName} = x;
              x.attrValueDOS.map(y=>{
                const {avId,attrValue} = y;
                sku.attrList.push({
                  attrId,
                  attrName,
                  avId,
                  attrValue
                })
              })
            })
            this.selectSku = sku;
          }else{//需要跳
             this.selectSku = temp2;
          }
        }
      },
      checkCartNum(){
        const n = this.cartNum
        if(!n || !n.trim() || isNaN(n) || n.trim() < 0){
          this.$message("输入有误！");
          this.cartNum = 1;
        }else{
          this.cartNum = n.trim();
        }
      }

    },
    mounted() {

    },
    created(){
      this.skuId = this.$route.params.skuId;

      //查商品详情
      initData.call(this)
    },
    computed:{
      mainJqImg(){
        // let temp = '/static/images/54-1.jpg'//默认图片
        let temp = ''//默认图片
        try{
          temp = this.obj.goodsVO.mediaRefVOS[0].mediaInfoDO.url;
        }catch(err){
        }
        return temp
      },
      goodsBrand(){
        let temp = '';
        try{
          temp = this.obj.goodsVO.goodsBrandDO;
        }catch(err){
        }
        return temp
      },
      price(){
        return this.selectSku.price?this.selectSku.price:this.obj.price
      },
      goodAttrVos(){
        let temp = [
          {
            attrValueVo:{
              attrDO:{}
            }
          }
        ];
        try{
          temp = this.obj.goodsVO.goodsValRefVOS;
        }catch(err){
        }
        //4个分组
        let result = [];
        // for(let i = 0 ; i < temp.length  && temp.length > 0 ;){
        //   result.push([temp[i++],temp[i++],temp[i++],temp[i++]])
        // }
        return temp
      },
      goodsInfoImgs(){
        let temp = [];
        try{
          temp = this.obj.goodsVO.goodsInfoMedias;
        }catch(err){}
        return temp;
      },
      isAvIdActive(){
        return (avId)=>{
          return this.selectSku.attrList.some(x=>{
            return x.avId === avId
          })
        }
      }
    },

  }

//查商品详情
function initData(){
  const _this = this;
  new Promise(function(resolve,reject){
    _this.$axios({
      url:'/biz/tob-mall/pruduct/details/'+_this.skuId,
      params:{
        isHistroy:true,
        hisUserId:configJS.userInfo.userId
      },
    }).then(res=>{
      if(res.data.success){
        _this.obj = res.data.obj;
        let {skuId,skuName,price,skuValueRefVos} = res.data.obj;
        //封装默认选择的数据
        let attrList = skuValueRefVos.map(item=>{
          let intemp = {};
          if(item.attrValueVo){
            intemp.avId = item.attrValueVo.avId;
            intemp.attrValue = item.attrValueVo.attrValue;
          }
          if(item.attrValueVo && item.attrValueVo.attrDO){
            intemp.attrId = item.attrValueVo.attrDO.attrId;
            intemp.attrName = item.attrValueVo.attrDO.attrName;
          }
          if(intemp){
            return intemp;
          }
        })
        _this.selectSku = {
          skuId,
          skuName,
          price,
          attrList
        }

        if(_this.obj.goodsVO.mediaRefVOS && _this.obj.goodsVO.mediaRefVOS.length > 0){
          _this.mianImgs = _this.obj.goodsVO.mediaRefVOS.map(e=>{
            if(e.mediaInfoDO){
              return e;
            }
          })
        }
      }
      resolve(res)
    }).catch((err)=>{
      reject(err)
    })
  }).then(res=>{
   //查商品所有sku属性
    _this.$axios.get('/biz/tob-mall/pruduct/skuAttrs/'+res.data.obj.goodsVO.goodsId)
    .then(res=>{
      if(res.data.success){
        _this.attrAll = res.data.obj.attrAll;
        _this.skuAttrs = res.data.obj.skuAttrs;
      }
    }).finally(_=>{
       $(".jqzoom").jqueryzoom({
           xzoom:250,
           yzoom:410,
           lens:1
       });
    })
  })


}

</script>

<style scoped>
#preview{
  display: flex;
  justify-content: center;
  background: none;
}
.jqzoom{
  height: 100%;
}
.jqzoom img{
  height: 100%;
}
.imi{
  width: 14px;
}
.table{
  border: solid 1px #ccc;
}
.table td,.table th{
   border: solid 1px #ccc;
}
.table th{
  text-align: center;
}
.magnifier-box img{
  height: 100%;
}
</style>
