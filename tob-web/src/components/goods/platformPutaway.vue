<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">商品上架管理</p>
      <ul class="ul003 mgt20">
        <li class="active" @click.prevent="checkTab = 2"><a href="javascript:void(0)">待上架</a></li>
        <li><a href="javascript:void(0)" @click.prevent="checkTab = 3">已上架</a></li>
        <li><a href="javascript:void(0)" @click.prevent="checkTab = 4">已下架</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--内容1-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0" v-model="params.orgId" @change="selectOrg">
                  <option value="0">创建单位</option>
                  <option  :value="item.id" v-for="(item,index) in orgList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
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
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios2">新增</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15"><label class="lab2"><input type="checkbox" v-model="isCheckAll2"  @click="handleCheckAllChange2"><i></i></label></td>
                  <td class="pdt15 pdb15">商品编号</td>
                  <td class="pdt15 pdb15">商品名称</td>
                  <td class="pdt15 pdb15">所属供应商</td>
                  <td class="pdt15 pdb15">创建单位</td>
                  <td class="pdt15 pdb15">创建时间</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList2">
                  <td><label class="lab2"><input type="checkbox" class="cidCheck" @click="handleCheckCidChange2(item.goodsId)"><i></i></label></td>
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.createTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td>
                    <a href="javascript:void (0)" class="btn btn-xs btn-success" @click="putAway(item.goodsId)">上架</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容2-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0" v-model="params.orgId" @change="selectOrg">
                  <option value="0">创建单位</option>
                  <option  :value="item.id" v-for="(item,index) in orgList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string" >
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
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios24">批量下架</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15"><span class="lab2"><input type="checkbox"><i></i></span></td>
                  <td class="pdt15 pdb15">商品编号</td>
                  <td class="pdt15 pdb15">商品名称</td>
                  <td class="pdt15 pdb15">所属供应商</td>
                  <td class="pdt15 pdb15">创建单位</td>
                  <td class="pdt15 pdb15">上架时间</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList3">
                  <td><span class="lab2"><input type="checkbox"><i></i></span></td>
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.listtingTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td>
                    <a href="javascript:void (0)" class="btn btn-xs btn-info" @click="soldout(item.goodsId)">下架</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容3-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr30">
                <select class="form-control ios0" v-model="params.orgId" @change="selectOrg">
                  <option value="0">创建单位</option>
                  <option  :value="item.id" v-for="(item,index) in orgList">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="params.supId" @change="selectSup">
                  <option value="0">所属供应商</option>
                  <option  :value="item.id" v-for="(item,index) in supplierList">{{item.name}}</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="商品名称/编号" v-model="params.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw" @click="putAway(item.goodsId)"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios23">批量上架</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15"><span class="lab2"><input type="checkbox"><i></i></span></td>
                  <td class="pdt15 pdb15">商品编号</td>
                  <td class="pdt15 pdb15">商品名称</td>
                  <td class="pdt15 pdb15">所属供应商</td>
                  <td class="pdt15 pdb15">创建单位</td>
                  <td class="pdt15 pdb15">下架时间</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in goodsList4">
                  <td><span class="lab2"><input type="checkbox"><i></i></span></td>
                  <td>{{item.goodsNum}}</td>
                  <td>{{item.goodsName}}</td>
                  <td>{{item.supName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.delistingTime | formatDate('yyyy-MM-dd HH:mm:ss')}}</td>
                  <td>
                    <a href="javascript:void (0)" class="btn btn-xs btn-success" @click="putAway(item.goodsId)">上架</a>
                  </td>
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
  var _this = '';
  export default {
    name: "platformPutaway",
    data() {
      return {
        params:{
          status: 2,//2待上架，3上架，4下架
          pageNum: 1,
          pageSize:10,
          string:'',
          orgId:0,
          supId:0
        },
        checkTab:2,//页签tab
        isCheckAll2:false, //复选框
        goodsId2:[],
        goodsList2: [],//2待上架
        goodsList3: [],//3上架
        goodsList4: [],//4下架

        supplierList:[],
        orgList:[],

        pageData:{
          curPage:1,//当前页吗
          totalCount:1,//总条数
          pageSize:1//页面大小
        },
      }
    },
    created() {
 /*     this.configJS.getUserInfo(this);
      _this.params.orgId=this.configJS.userInfo.orgId;*/
      initSupplier( _this.params.orgId);
      initOrg();
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
    },
    methods: {
      chekdpage: function(pageNum) {
        _this.params.pageNum = pageNum;
        selectgoodsList();
      },
      search:function () {
        selectgoodsList();
      },
      selectOrg:function () {
        selectgoodsList();
        initSupplier(_this.params.orgId);
      },
      selectSup:function () {
        selectgoodsList();
      },
      putAway:function (e) {//上架
        _this.$confirm('确定上架吗？').then(_ => {
          putAway(e);
        }).catch(_ => {
        });
      },
      soldout:function (e) {
        _this.$confirm('确定下架吗？').then(_ => {
          soldout(e);
        }).catch(_ => {
        });
      },
      handleCheckAllChange2:function (e) {
          if( _this.isCheckAll2){
            ///_this.checkGoods2=true;
            _this.goodsId2=[];
            _this.goodsList2.forEach(item=>{
                _this.goodsId2.push(item.goodsId);
              });
          }else{
           // _this.checkGoods2=false;

          }

      },
      handleCheckCidChange2:function (goodsId) {
        //console.log(_this.checkGoods2);
        var idIndex = this.goodsId2.indexOf(goodsId)
        if (idIndex >= 0) {//如果已经包含就去除
          this.goodsId2.splice(idIndex, 1)
        } else {//如果没有包含就添加
          this.goodsId2.push(goodsId)
        }
      },

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
  }
  function selectgoodsList(){    //查询商品列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/list',
      params:  _this.params
    }).then((res) => {
      if (res.status == 200) {
         if(_this.checkTab==2){
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

  //上架
  function putAway(goodsId) {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goods/putaway',
      params: {goodsId:goodsId,status:3}
    }).then((res) => {

      selectgoodsList();

    })
  }

  //下架
  function soldout(goodsId) {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goods/soldout',
      params: {goodsId:goodsId,status:4}
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

  //组织
  function initOrg() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goods/findOrgByPid',
    }).then((res) => {
      _this.orgList=res.data
    })
  }

</script>
