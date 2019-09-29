<template>
  <div class="pdl15 pdr15">
    <div class="w1200 c-b mm100 pdb110">
      <div class="ovh pdt25 pdb20">
        <div class="h40 lh40 fl mgr20 cor0">{{$parent.category && $parent.category.cid >=0 ?$parent.category.cname:'所有商品'}}  ></div>
        <div class="fl myspqnparn">
          <input class="db w200 h40 lh40 bor-d ios25" type="text" v-model="childSearchKey" placeholder="输入搜索" @keyup.enter="listSearch">
          <span class="mySpanIcon" @click="listSearch"></span>
        </div>
      </div>

<!--  
      <div class="pdt30 pdb30 pdl40 pdr40 bgw">
        <p class="fs16 cor0 fb">在笔记本电脑中筛选</p>
        <div class="hrd"></div>
        <div class="mgt10 ovh pdt20 pdb20 bor-dask1">
          <div class="fl cor101010 fb w80 h30 lh30">品牌：</div>
          <div class="fr ovh w170">
            <a href="###" class="db fl cor101010 h30 lh30 bor-d w80 t-c ras6">多选</a>
            <a href="###" class="db fr cor101010 h30 lh30 ios26 pdr35 zhan-kai">展开</a>
          </div>
          <div class="mgl90 mgr230 btn-a4">
            <a href="###" class="db fl cor9">联想</a>
            <a href="###" class="db fl cor9">戴尔</a>
            <a href="###" class="db fl cor9">华硕</a>
            <a href="###" class="db fl cor9">惠普</a>
            <a href="###" class="db fl cor9">神州</a>
            <a href="###" class="db fl cor9">华为</a>
            <a href="###" class="db fl cor9">小米</a>
            <a href="###" class="db fl cor9">三星</a>
            <a href="###" class="db fl cor9">苹果</a>
            <a href="###" class="db fl cor9">联想</a>


            <div class="dn btn-div ovh fl ww100">
              <a href="###" class="db fl cor9">戴尔</a>
              <a href="###" class="db fl cor9">华硕</a>
              <a href="###" class="db fl cor9">惠普</a>
              <a href="###" class="db fl cor9">神州</a>
              <a href="###" class="db fl cor9">华为</a>
              <a href="###" class="db fl cor9">小米</a>
              <a href="###" class="db fl cor9">三星</a>
              <a href="###" class="db fl cor9">苹果</a>
            </div>
          </div>
        </div>

        <div class="mgt10 ovh pdt20 pdb20 bor-dask1">
          <div class="fl cor101010 fb w80 h30 lh30">分类：</div>
          <div class="fr ovh w170">
            <a href="###" class="db fl cor101010 h30 lh30 bor-d w80 t-c ras6">多选</a>
          </div>
          <div class="mgl90 mgr230 btn-a4">
            <a href="###" class="db fl cor9">游戏本</a>
            <a href="###" class="db fl cor9">轻薄本</a>
            <a href="###" class="db fl cor9">二合一笔记本</a>
          </div>
        </div>

        <div class="mgt10 ovh pdt20 pdb20 bor-dask1">
          <div class="fl cor101010 fb w80 h30 lh30">价格：</div>
          <div class="fr ovh w170">
            <a href="###" class="db fl cor101010 h30 lh30 bor-d w80 t-c ras6">多选</a>
          </div>
          <div class="mgl90 mgr230 btn-a4">
            <a href="###" class="db fl cor9">0-2000元</a>
            <a href="###" class="db fl cor9">2000-3000元</a>
            <a href="###" class="db fl cor9">3000-5000元</a>
            <a href="###" class="db fl cor9">5000-8000元</a>
            <a href="###" class="db fl cor9">8000元以上</a>
            <div class="fl">
              <input type="text" class="w80 h30 lh30 bor-d ras6 pdl15" placeholder="￥">
              <span class="mgl5 mgr5">-</span>
              <input type="text" class="w80 h30 lh30 bor-d ras6 pdl15" placeholder="￥">
            </div>
          </div>
        </div>

        <div class="mgt10 ovh pdt20 pdb20">
          <div class="fl cor101010 fb w80 h30 lh30">高级选项：</div>
          <div class="mgl90 btn-a4">
            <select class="w100 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">色系</option>
              <option value="1">色系</option>
            </select>

            <select class="w130 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">屏幕尺寸</option>
              <option value="1">屏幕尺寸</option>
            </select>
            <select class="w130 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">处理器</option>
              <option value="1">处理器</option>
            </select>
            <select class="w130 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">显卡类别</option>
              <option value="1">显卡类别</option>
            </select>
            <select class="w130 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">显卡型号</option>
              <option value="1">显卡型号</option>
            </select>
            <select class="w130 h30 lh30 bor-d ras6 pdl15 ios26">
              <option value="1">其他分类</option>
              <option value="1">其他分类</option>
            </select>
          </div>
        </div>
      </div>
    -->
      <div class="bgw mgt20 ovh pdt15 pdl40 pdr40">
        <div class="fl ovh ww50">
          <p class="fl cor0 fb w90 h30 lh30">排序：</p>

          <a href="###" class="db btn-a5 fl">默认</a>
          <!-- 
          <a href="###" class="db btn-a5 fl">价格</a>
          <a href="###" class="db btn-a5 fl">销量</a>
          <a href="###" class="db btn-a5 fl">人气</a>
          <a href="###" class="db btn-a5 fl">上架时间</a>
           -->
        </div>
        <div class="fr ovh btn-a6 ww30">
          <p class="fl">共{{page.totalCount}}个商品</p>
          <p class="fl">
            <span>{{page.pageNum}}</span> / <span>{{Math.ceil(page.totalCount / page.limit)}}</span>
          页</p>
          <p class="fl"><a href="javascript:;" class="db cor6" @click.prevent="page.pageNum > 1 ? $parent.initSearch(page.pageNum + 1) : $message('没有上一页了')">上一页</a></p>
          <p class="fl"><a href="javascript:;" class="db cor5" @click.prevent="page.pageNum < Math.ceil(page.totalCount / page.limit) ? $parent.initSearch(page.pageNum - 1) : $message('没有下一页了')">下一页</a></p>
        </div>
      </div>

    <div>

      <!--默认-->
      <div class="oDiv1 db">
        <ul class="ul-44 ovh">
          <li v-for="(item,index) in list" :key="index" class="liStl">
            <div class="db t-c rel ovh bgw">
              <div class="ovh div4 cursor boEEE" @click.prvent="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}})">
                <!--  @click="$router.push({'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}}) -->
                <img class="db c-b ww100 time05 myimg" :src="item.tMediaVos && item.tMediaVos.length > 0 && item.tMediaVos[0].mediaInfoDO ? item.tMediaVos[0].mediaInfoDO.url:''" alt="">
              </div>
              <p class="cor101010 fb h33">{{item.goodsName}}</p>
              <p class="fs12 cor9 mgt5">采购量：{{item.totalNum?item.totalNum:'0'}} |  浏览量0：{{item.viewNum?item.viewNum:'0'}}</p>
              <p class="fs24 corED145B mgb20">{{'￥'+ $options.filters.numFilter(item.skuDOList[0].price)}}</p>
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
      <!--默认END-->
    </div>

    <div class="mgt34">
      <p class="fs16 cor0 fb">您最近查看的商品</p>
      <div class="hrd"></div>
    </div>
    <div class="mgt20 rel pdl30 pdr30 kk1">
      <div class="swiper-container" id="swiper1">
        <div class="swiper-wrapper">
          <div class="swiper-slide" v-for="(item,index) in histroyList" :key="index"> 
            <router-link :to="{'name':'product_info',params:{'skuId':item.skuDOList[0].skuId}}" class="db bgw t-c hisimg">
              <div class="ovh">
                <img :src="hisImgUrl(item)" alt="" class="db ww100 h201 imim2" style="height: 230px;">
              </div>
              <div class="cor101010 h33 testtxt" style="">{{item.goodsDO?item.goodsDO.goodsName:''}}</div>
              <div class="fs12 cor9 mgt6">采购量：{{item.totalNum?item.totalNum:'0'}}  |  浏览量：{{item.viewNum?item.viewNum:'0'}}</div>
              <div class="fs20 cor101010 mgt10 pdb25">￥{{item.skuDOList[0].price | numFilter}}</div>
            </router-link> 
          </div>
        </div>
      </div>
      <div class="swiper-button-next arrow-left"></div>
      <div class="swiper-button-prev arrow-right"></div> 
    </div>
  </div>
</div>
</template>
<script>
  import Swiper from 'swiper'
  import configJS from '@/common/config'

  export default {
    name: "list",
    data() {
      return {
        childSearchKey:'',//分类中的关键字
        histroyList:[],
      }
    },
    props:{
      serachKey:[String],
      levList:[Array],
      list:{//商品
        type: Array,
        default:function () {
         return [];
        }
     },
     page:{
        type:[Object],
        default:function(){
          return {
            pageNum:1, //页码
            limit:16, //页面大小
            totalCount:0
          }
        }
     }
    },
     beforeCreate(){
      this.$parent.initSearch()
    },
    created(){
      history.call(this);
    },
    watch:{
    },
    mounted(){
      $(".zhan-kai").click(function(){
        if($(".btn-div").is(':hidden')){
          $(".btn-div").slideDown();
          $(this).text('收起')
        }else{
          $(".btn-div").slideUp();
          $(this).text('展开')
        }
      });
    },
    methods:{
      //分类中搜索
      listSearch(){
        this.$parent.initSearch(1,this.childSearchKey)
      },
    },
    computed:{
      hisImgUrl(){
        return (item)=>{
          let temp = ""
          try{
            temp = item.mediaRefVOS[0].mediaInfoDO.url
          }catch(res){}
          return temp;
        }
      }
    }
  }
  function history(){
    let userId = configJS.userInfo.userId;
    this.$axios({
      url:"/biz/tob-mall/pruduct/historys/" + userId
    }).then(res=>{
      if(res.data.success){
        this.histroyList = res.data.obj;
      }
    }).finally(_=>{
      var swiper1 = new Swiper('#swiper1', {
          loop:true,
          speed:1300, 
          spaceBetween: 10,
          slidesPerView: 5,
          autoplay: {
            delay: 2500,
            disableOnInteraction: false,
          },
          navigation: {
            nextEl: '.arrow-right',
            prevEl: '.arrow-left',
          },
          observer: true,
          observeParents: true
      });
    })  
  }

</script>

<style scoped>
.myspqnparn{
  position: relative;
}
.mySpanIcon{
  position: absolute;
  width: 35px;
  height:40px;
  right: 0px;
  top: 0px;
  /*border: solid 1px red;*/
  background:  url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAAQCAYAAADwMZRfAAAABHNCS…6Ynp5e6/uhhsV7f9HM7kuKgDXn3Fj0v4hzbgmYBL4DbwD+AINttpZFbyfWAAAAAElFTkSuQmCC) no-repeat center center;
  cursor: pointer;
}
.myimg{
    height: 90%;
    width: 90%;
}
.imim2{
   width: 220px;
  height: 202px;
  border:solid 1px #eee;
}
.hisimg{
  border: solid 1px #ddd;
}
.testtxt{
  overflow: hidden; 
  text-overflow: ellipsis; 
  -webkit-line-clamp:2;  
}
.boEEE{
  border: solid 1px #eee;
  height: 333px;
  width: 290px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.liStl:nth-child(-n+4){
  border-color: red;
  margin-top: 0px;
}
</style>
