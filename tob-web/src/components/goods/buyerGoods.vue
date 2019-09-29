<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">商品管理</p>
      <ul class="ul003 mgt20">
        <li id="1id" class="active"   @click.prevent="checkTab = 1"><a href="###">待入库</a></li>
        <li id="2id" @click.prevent="checkTab = 2"><a href="###">已入库</a></li>
        <li id="3id" @click.prevent="checkTab = 3"><a href="###">已上架</a></li>
        <li id="4id" @click.prevent="checkTab = 4"><a href="###">已下架</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--内容1-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0 bor-d" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0 bor-d" v-model="params.subId" @change="selectSub">
                  <option value="0">所属科目</option>
                  <option  :value="item.id" v-for="(item,index) in subjectList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios2">新增商品</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios7">批量入库</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>
                    <p><label class="lab2"><input type="checkbox" name="abc" checked><i></i></label></p>
                  </td>
                  <td>商品编号</td>
                  <td>商品名称</td>
                  <td>所属供应商</td>
                  <td>所属科目</td>
                  <td>创建单位</td>
                  <td>创建时间</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList1">
                  <td>
                    <p><label class="lab2"><input type="checkbox" name="inpt"><i></i></label></p>
                  </td>
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.subName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td>
                    <!--<router-link href="###" class="btn btn-xs btn-primary" :to="{name:'goodsDetail',query:{goodsId:item.goodsId}}">查看</router-link> <br>-->
                    <a href="javascript:void (0)" class="btn btn-sm btn-primary" @click="goDetail(item.goodsId)">查看</a>
                    <a href="###" class="btn btn-xs btn-info mgt10" @click="enter(item.goodsId)">入库</a>
                    <a href="###" class="btn btn-xs btn-warning mgt10" v-if="item.operator==1" @click="back(item.goodsId)">打回</a>
                    <a href="###" class="btn btn-xs btn-success mgt10" v-if="item.operator==2">编辑</a>
                    <a href="###" class="btn btn-xs btn-danger mgt10" v-if="item.operator==2">删除</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容2-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0 bor-d" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0 bor-d" v-model="params.subId" @change="selectSub">
                  <option value="0">所属科目</option>
                  <option  :value="item.id" v-for="(item,index) in subjectList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="mgt40">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>商品编号</td>
                  <td>商品名称</td>
                  <td>所属供应商</td>
                  <td>所属科目</td>
                  <td>创建单位</td>
                  <td>创建时间</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList2">
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.subName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td><a href="javascript:void (0)" class="btn btn-sm btn-primary" @click="goDetail(item.goodsId)">查看</a></td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容3-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0 bor-d" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0 bor-d" v-model="params.subId" @change="selectSub">
                  <option value="0">所属科目</option>
                  <option  :value="item.id" v-for="(item,index) in subjectList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline"  @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="mgt40">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>商品编号</td>
                  <td>商品名称</td>
                  <td>所属供应商</td>
                  <td>所属科目</td>
                  <td>上架时间</td>
                  <td>操作</td>
                </tr>
                <tr  v-for="(item,index) in goodsList3">
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.subName}}</td>
                  <td>{{item.listtingTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <!--<td><router-link href="###" class="btn btn-sm btn-primary" :to="{name:'goodsDetail',query:{goodsId:item.goodsId}}">查看</router-link></td>-->
                  <td><a href="javascript:void (0)" class="btn btn-sm btn-primary" @click="goDetail(item.goodsId)">查看</a></td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容4-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0 bor-d" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0 bor-d" v-model="params.subId" @change="selectSub">
                  <option value="0">所属科目</option>
                  <option  :value="item.id" v-for="(item,index) in subjectList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="mgt40">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>商品编号</td>
                  <td>商品名称</td>
                  <td>所属供应商</td>
                  <td>所属科目</td>
                  <td>下架时间</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList4">
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.subName}}</td>
                  <td>{{item.delistingTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td><a href="javascript:void (0)" class="btn btn-sm btn-primary" @click="goDetail(item.goodsId)">查看</a></td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
        </ul>
      </div>
      <!--分页-->
      <div class="fen c-b ovh mgt30 t-c w410">
        <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
      </div>
      <!--分页END-->
    </div>

  </div>


</template>


<style>

</style>

<script>
  import J from 'swiper';
  var _this = '';
  export default {
    name: "buyerGoods",
    data() {
      return {
        params:{status: 1, pageNum: 1, pageSize:10, string:'', orgId:0, subId:0, supId:0},//1待入库，2已入库，3上架，4下架
        checkTab:1,//页签tab
        goodsList1: [],//待入库
        goodsList2: [],//已入库
        goodsList3: [],//3上架
        goodsList4: [],//4下架

        supplierList:[],
        subjectList:[],

        pageData:{
          curPage:1,//当前页吗
          totalCount:1,//总条数
          pageSize:1//页面大小
        },
      }
    },
    created() {
        this.configJS.getUserInfo(this);
      _this.params.orgId=this.configJS.userInfo.orgId;
      initSupplier(_this.params.orgId);
      initSubject(_this.params.orgId);
      selectgoodsList();
    },beforeCreate(){
      _this = this;
    },
    mounted(){
      var $ul003= $('.ul003>li');
      $ul003.click(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var i= $ul003.index(this);
        $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
      });
       let status=this.$route.query.status;
      if(status!=undefined){
        if(status==1){
          $("#1id").click();
        }
        else if(status==2){
          $("#2id").click();
        }
        else if(status==3){
          $("#3id").click();
        }
        else if(status==4){
          $("#4id").click();
        }
      }


    },
    methods: {
      chekdpage: function(pageNum) {
        _this.params.pageNum = pageNum
        selectgoodsList();
      },
      search:function () {
        selectgoodsList();
      },
      selectSup:function () {
        selectgoodsList();
      },
      selectSub:function () {
        selectgoodsList();
      },
      goDetail:function (goodsId) {
        _this.$router.push({name: 'goodsDetail',query:{ goodsId:goodsId,status:_this.params.status}});
      },
  enter:function (e) {//入库
    _this.$confirm('确定入库吗？').then(_ => {
        enter(e);
        }).catch(_ => {
        });
      },
      back:function (e) {
        _this.$confirm('确定打回吗？').then(_ => {
          back(e);
        }).catch(_ => {
        });
      }
    },
    watch:{
     checkTab(newVal,oldVal){
       if(newVal != oldVal) {
         _this.params.pageNum=1;
         _this.params.status = newVal;//切换tab
        selectgoodsList();
     }
     },
     },

    computed: {
    },
  }
  function selectgoodsList(){    //查询商品列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/list',
      params:  _this.params
    }).then((res) => {
      if (res.status == 200) {
          if(_this.checkTab==1){
            _this.goodsList1=res.data.rows;
          }else if(_this.checkTab==2){
            _this.goodsList2=res.data.rows;
          } else if(_this.checkTab==3){
            _this.goodsList3=res.data.rows;
          }else {
            _this.goodsList4=res.data.rows;
          }
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  //入库
  function enter(goodsId) {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goods/update',
      params: {goodsId:goodsId,status:2}
    }).then((res) => {

      selectgoodsList();

    })
  }

  //打回
  function back(goodsId) {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goods/update',
      params: {goodsId:goodsId,status:5}
    }).then((res) => {

      selectgoodsList();
    })
  }

  //供应商
  function initSupplier(orgId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/findSupplierByOrg',
      params: {orgId:orgId}
    }).then((res) => {
      _this.supplierList=res.data
    })
  }

  //科目
  function initSubject(orgId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/findSubjectByOrg',
      params: {orgId:orgId}
    }).then((res) => {

      _this.subjectList=res.data
    })
  }

</script>
