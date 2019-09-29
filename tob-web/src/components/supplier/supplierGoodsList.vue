<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30" style="font-size: 14px;">

    <div>
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">商品管理</p>
        <!--<a href="###" class="db fr fs18 cor0">返回>></a>-->
      </div>

      <div class="ovh mgt20">
        <div class="fl">
          <select class="form-control ios0">
            <option>商品分类</option>
            <option>商品分类2</option>
          </select>
        </div>
        <div class="fr">
          <form action="" class="form-inline">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="商品名称/编号" v-model="input">
              <span class="input-group-btn">
						<button type="submit" class="btn bged145b" @click="select">
							<i class="glyphicon glyphicon-search corw"></i>
						</button>
					</span>
            </div>
          </form>
        </div>
      </div>

      <div>
        <ul class="ul004 ovh" style="margin-bottom: 10px;">
          <li class="fl mgr40 mgt20"><a href="###" class="db corED145B ios2 pdl29">新增商品</a></li>
          <li class="fl mgr40 mgt20"><a href="###" class="db corED145B ios4 pdl29">导入</a></li>
          <li class="fl mgr40 mgt20"><a href="###" class="db corED145B ios5 pdl29">模板下载</a></li>
        </ul>
      </div>

      <div>
        <table class="table t-c table-d">
          <thead>
          <tr>
            <td style="width: 120px">商品标号</td>
            <td style="width: 410px">商品名称</td>
            <td style="width: 210px">商品分类</td>
            <td>创建时间</td>
            <td>状态</td>
            <td>操作</td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item,index) in goodsList">
            <td>{{item.goods_num}}</td>
            <td>{{item.goods_name}}</td>
            <td>
              <p>{{item.gc1_c_name?item.gc1_c_name:'/'}}-{{item.gc2_c_name?item.gc2_c_name:'/'}}-{{item.gc3_c_name?item.gc3_c_name:'/'}}</p>
            </td>
            <td>{{item.create_time|formatDate("yyyy-MM-dd")}}</td>
            <td>
              <a href="javascript:" class="cor59B359" v-if="item.status==3">已上架</a>
              <a href="javascript:" class="corF0241D" v-if="item.status==4">已下架</a>
            </td>
            <td>
              <router-link :to="{name:'supplierGoodsDetail',params:{'id':item.goods_id}}"class="btn btn-xs btn-primary">查看</router-link>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
      <!--分页-->
      <div class="fen c-b ovh mgt30 t-c w410">
        <page-vue :pageDate="pageData" @chekdpageCallcak="chekdpage"></page-vue>
        <!--<a href="###" class="db fl fs14 cor0">1</a>
        <a href="###" class="db fl fs14 cor0 active">2</a>
        <a href="###" class="db fl fs14 cor0">3</a>
        <a href="###" class="db fl fs14 cor0">4</a>
        <a href="###" class="db fl fs14 cor0">5</a>
        <a href="###" class="db fl fs14 cor0"><img src="images/7.png" alt=""></a>
        <a href="###" class="db fl fs14 cor0">99</a>
        <a href="###" class="db fl fs14 cor0 curr"></a>
        <a href="###" class="db fl fs14 cor0"></a>-->
      </div>
      <!--分页END-->
    </div>

  </div>
</template>

<script>
  import config from '../../common/config'
    export default {
        name: "supplierGoodsList"
      ,data(){
          return{

            goodsList:{}//商品集合
            ,pageInfo:{//分页对象
              pageNum:1
              ,pageSize:5
            }
            ,input:''
          }
      },created(){
          initDate(this);
      },methods:{
          select(){
            initDate(this)
          },chekdpage(pageNum){
            alert(pageNum)
        }
      },computed:{
          pageData(){
            return{
              curPage:this.pageInfo.pageNum,//当前页吗
              totalCount: this.pageInfo.totalCount?this.pageInfo.totalCount:0,//总条数
              pageSize: this.pageInfo.pageSize,//页面大小
            }
          }
      }
    }
    //初始化商品集合
    function initDate(_this){
      /*var timestamp4 = new Date(1472048779952);//直接用 new Date(时间戳) 格式转化获得当前时间
      console.log(timestamp4);
      console.log(timestamp4.toLocaleDateString().replace(/\//g, "-"));*/
      var orgId=11
      if(config.userInfo&&config.userInfo.orgId){
        orgId=config.userInfo.orgId
      }
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/getGoodsList'
        ,params:{"orgId":orgId,input:_this.input,start:_this.pageInfo.pageNum,size:_this.pageInfo.pageSize}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.goodsList=ref.data.map.list
        }
      })
    }
</script>

<style scoped>

</style>
