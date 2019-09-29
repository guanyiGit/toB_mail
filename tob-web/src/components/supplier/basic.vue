<template>
  <div style="font-size: 14px;line-height: 1.42857143;color: #333;background-color: #fff;">
    <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

      <div>
        <p class="fs18 h28 lh28 cor0">基本信息</p>
        <ul class="ul003 mgt20">
          <li class="active"><a href="###">供应商基本信息</a></li>
          <li><a href="###">送货人员</a></li>
          <li><a href="###">安装人员</a></li>
        </ul>

        <div class="mgt20">
          <ul class="ul004">
            <li class="db">
              <div class="ovh">
                <router-link class="db mgt20 corED145B pdl28 ios1":to="{name:'supplierUpd',params:{'id':supplier.orgId}}">编辑</router-link>
                <!--<a href="###" class="db mgt20 corED145B pdl28 ios1">编辑</a>-->
              </div>
              <div class="mgt30">
                <table class="table t-c table-bordered">
                  <TBODY>
                  <tr>
                    <td class="bgf2">供应商编号：</td>
                    <td>{{supplier&&supplier.sup_id?supplier.sup_id:'' +
                      ''}}</td>
                  </tr>
                  <tr>
                    <td class="bgf2">供应商名称：</td>
                    <td style="text-align: center;">{{supplier&&supplier.sup_name?supplier.sup_name:''}}</td>
                  </tr>
                  <tr>
                    <td class="bgf2">联系人：</td>
                    <td>{{supplier&&supplier.legal_person?supplier.legal_person:''}}</td>
                  </tr>
                  <tr>
                    <td class="bgf2">联系电话：</td>
                    <td>{{supplier&&supplier.phone?supplier.phone:''}}</td>
                  </tr>
                  <tr>
                    <td class="bgf2">电子邮箱：</td>
                    <td>{{supplier&&supplier.email?supplier.email:''}}</td>
                  </tr>
                  <tr>
                    <td class="bgf2">详细地址：</td>
                    <td>{{supplier&&supplier.address?supplier.address:''}}</td>
                  </tr>
                  </TBODY>
                </table>
              </div>
            </li>
            <li class="dn">
              <div class="ovh">
                <router-link :to="{name:'deliveryManAdd'}"class="db corED145B pdl28 ios2 fl">新增送货人员</router-link>
                <!--<a href="###" class="db corED145B pdl28 ios2 fl">新增送货人员</a>-->
                <div class="fr">
                  <form action="" class="form-inline">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="姓名/手机号" v-model="map0.input">
                      <span class="input-group-btn">

                  <button type="submit" class="btn bged145b" @click="select0">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                    </div>
                  </form>
                </div>
              </div>
              <div class="mgt30">
                <table class="table t-c table-d">
                  <thead>
                  <tr>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>手机号</td>
                    <td>创建时间</td>
                    <td>状态</td>
                    <td>操作</td>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(item,index) in map0.list">
                    <td>{{item.id?item.id:''}}</td>
                    <td>{{item.name?item.name:''}}</td>
                    <td>{{item.sex==0?'男':'女'}}</td>
                    <td>{{item.phone_num?item.phone_num:''}}</td>
                    <td v-if="item.create_time">{{item.create_time|formatDate("yyyy-MM-dd")}}</td>
                    <td v-else>--</td>
                    <td><a href="###" class="corF0241D">{{item.state==0?'启用':'禁用'}}</a></td>
                    <td>
                      <router-link class="btn btn-xs btn-primary" :to="{name:'deliveryManDetails',params:{'id':item.id}}">查看</router-link>
                      <router-link class="btn btn-xs btn-info" :to="{name:'deliveryManUpd',params:{'id':item.id}}">编辑</router-link>
                      <a href="javascript:" class="btn btn-xs btn-danger" v-if="item.state==0" @click="updState(item.id,1,'map0')">禁用</a>
                      <a href="javascript:" class="btn btn-xs btn-danger" v-if="item.state==1" @click="updState(item.id,0,'map0')">启用</a>
                    </td>
                  </tr>
                  </tbody>
                </table>
                <page-vue :pageData="pageData0" @chekdpageCallcak="chekdpage0"></page-vue>
              </div>
            </li>
            <li class="dn">
              <div class="ovh">
                <router-link :to="{name:'installerAdd'}"class="db corED145B pdl28 ios2 fl">新增安装人员</router-link>
                <!--<a href="###" class="db corED145B pdl28 ios2 fl">新增安装人员</a>-->
                <div class="fr">
                  <form action="" class="form-inline">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="姓名/手机号"v-model="map1.input">
                      <span class="input-group-btn">
									<button type="submit" class="btn bged145b" @click="select1">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                    </div>
                  </form>
                </div>
              </div>
              <div class="mgt30">
                <table class="table t-c table-d">
                  <thead>
                  <tr>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>手机号</td>
                    <td>创建时间</td>
                    <td>状态</td>
                    <td style="width: 28%">操作</td>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(item,index) in map1.list">
                    <td>{{item.id?item.id:''}}</td>
                    <td>{{item.name?item.name:''}}</td>
                    <td>{{item.sex==0?'男':'女'}}</td>
                    <td>{{item.phone_num?item.phone_num:''}}</td>
                    <td v-if="item.create_time">{{item.create_time|formatDate("yyyy-MM-dd")}}</td>
                    <td v-else>--</td>
                    <td><a href="javascript:" class="corF0241D">{{item.state==0?'启用':'禁用'}}</a></td>
                    <td>
                      <router-link class="btn btn-xs btn-primary" :to="{name:'installerDetails',params:{'id':item.id}}">查看</router-link>
                      <router-link class="btn btn-xs btn-info" :to="{name:'installerUpd',params:{'id':item.id}}">编辑</router-link>
                      <a href="javascript:" class="btn btn-xs btn-danger" v-if="item.state==0" @click="updState(item.id,1,'map1')">禁用</a>
                      <a href="javascript:" class="btn btn-xs btn-danger" v-if="item.state==1" @click="updState(item.id,0,'map1')">启用</a>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
            </li>
          </ul>
        </div>
        <!--分页-->
        <!--<div class="fen c-b ovh mgt30 t-c w410">
          <a href="###" class="db fl fs14 cor0">1</a>
          <a href="###" class="db fl fs14 cor0 active">2</a>
          <a href="###" class="db fl fs14 cor0">3</a>
          <a href="###" class="db fl fs14 cor0">4</a>
          <a href="###" class="db fl fs14 cor0">5</a>
          <a href="###" class="db fl fs14 cor0"><img src="images/7.png" alt=""></a>
          <a href="###" class="db fl fs14 cor0">99</a>
          <a href="###" class="db fl fs14 cor0 curr"></a>
          <a href="###" class="db fl fs14 cor0"></a>
        </div>-->
        <!--分页END-->
      </div>

    </div>
  </div>

</template>

<script>
  import $ from 'jquery'
  import config from '../../common/config'
    export default {
        name: "basic"
      ,data(){
          return{
            orgId:config.userInfo.orgId//机构id
            ,supplier:{}//供应商对象
            ,map0:{
              list:{}//送货人员集合
              ,pageInfo:{//送货分页对象
                pageSize:8
                ,pageNum:1
              },input:''
            },map1:{
              list:{}//安装人员集合
              ,pageInfo:{//安装分页对象
                pageSize:8
                ,pageNum:1
              },input:''
            }
          }
      },created(){
          if(this.orgId==1){
            this.orgId=34//机构id
          }

        initDate(this);
      },
      mounted(){
        var $ul003= $('.ul003>li');
        $ul003.click(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $ul003.index(this);
          $('.ul004>li').eq(i).show().siblings('.ul004>li').hide();
        });
      },
      methods:{
        chekdpage0(pageNum){//点击分页按钮
        this.map0.pageInfo.pageNum=pageNum
        delivery0(this)
        },chekdpage1(pageNum){//点击分页按钮
          this.map1.pageInfo.pageNum=pageNum
          delivery1(this)
        },select0:function(){//点击搜索
          this.map0.pageInfo.pageNum=1
          delivery0(this)
        },select1:function(){//点击搜索
          this.map1.pageInfo.pageNum=1
          delivery1(this)
        },updState:function(id,state,mapType){//修改状态
          updDelivery(this,id,state,mapType);
        }
      },computed:{
        pageData0(){
          return{
            curPage:this.map0.pageInfo.pageNum,//当前页吗
            totalCount:this.map0.pageInfo.totalCount?this.map0.pageInfo.totalCount:0,//总条数
            pageSize:this.map0.pageInfo.pageSize,//页面大小
          }
        },pageData1(){
          return{
            curPage:this.map1.pageInfo.pageNum,//当前页吗
            totalCount:this.map1.pageInfo.totalCount?this.map1.pageInfo.totalCount:0,//总条数
            pageSize:this.map1.pageInfo.pageSize,//页面大小
          }
        }
      }
    }
    //初始化供应商，安装人员，送货人员
    function initDate(_this) {
    console.log("登陆用户信息"+JSON.stringify(config))
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/getSupplierById'
        ,params:{id:_this.orgId}
      }).then((ref)=>{
        console.log("供应商"+JSON.stringify(ref.data))
        if(ref.data.code==200){
          _this.supplier=ref.data.map
          _this.supplier.orgId=_this.orgId
          delivery0(_this)
          delivery1(_this)
        }
        console.log(JSON.stringify(_this.supplier))
      })
    }
    //得到送货人员集合
    function delivery0(_this){
      _this.$axios({//送货人员
        url:'/biz/tob-supplier/com/supplier/getDelivery'
        ,params:{input:_this.map0.input,supplier_id:_this.orgId,type:0
          ,start:_this.map0.pageInfo.pageNum,size:_this.map0.pageInfo.pageSize}
      }).then((ref)=>{
        console.log("送货人员"+JSON.stringify(ref.data))
        if(ref.data.code==200){
          _this.map0.list=ref.data.map.list
          _this.map0.pageInfo=ref.data.map.pageInfo
        }
      })
    }
    //得到安装人员集合
    function delivery1(_this){
      _this.$axios({//安装人员
        url:'/biz/tob-supplier/com/supplier/getDelivery'
        ,params:{input:_this.map1.input,supplier_id:_this.orgId,type:1
          ,start:_this.map1.pageInfo.pageNum,size:_this.map1.pageInfo.pageSize}
      }).then((ref)=>{
        console.log("安装人员"+JSON.stringify(ref.data))
        if(ref.data.code==200){
          _this.map1.list=ref.data.map.list
          _this.map1.pageInfo=ref.data.map.pageInfo
        }
      })
    }
    function updDelivery(_this,id,state,mapType) {
      _this.$axios({
        methods:"post"
        ,url:'/biz/tob-supplier/com/supplier/updDelivery'
        ,params:{"id":id,"state":state}
      }).then((ref=>{
        if(ref.data.code==200){
          if(mapType=='map0'){
            //_this.map0.pageInfo.pageNum=1;
            //_this.map0.pageInfo.input=''
            delivery0(_this)
          }else if(mapType=='map1'){
            //_this.map1.pageInfo.pageNum=1;
            //_this.map1.pageInfo.input=''
            delivery1(_this)
          }
        }
      }))
    }
</script>

<style scoped>
  .bgf2,.bgf2+td{
    text-align: center;
  }
  tr>td{
    text-align: center;

  }
  .bgf2{
    width: 50%;
  }
</style>
