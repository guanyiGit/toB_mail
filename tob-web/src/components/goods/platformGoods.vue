<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">商品基本信息管理</p>
      <ul class="ul003 mgt20">
        <li id="5id" class="active" @click.prevent="checkTab = 1"><a href="javascript:void(0)">属性分组设置</a></li>
        <li id="6id" @click.prevent="checkTab = 2"><a href="javascript:void(0)" >属性科目维护</a></li>
      <!--  <li><a href="###">计量单位维护</a></li>-->
        <li id="7id" @click.prevent="checkTab = 3"><a href="javascript:void(0)" >品牌标识维护</a></li>
        <li id="8id" @click.prevent="checkTab = 4"><a href="javascript:void(0)" >商品分类设置</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--内容1-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl">
                <select class="form-control ios0" v-model="params1.status" @change="selectStatus1">
                  <option value="0">状态</option>
                  <option value="1">启用</option>
                  <option value="2">停用</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline">
                  <div class="input-group" @keyup.13="search1">
                    <input type="text" class="form-control" placeholder="类别名称" v-model="params1.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search1">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><router-link :to="{name:'addAttrGroup'}" class="db pdl29 corED145B ios2">新增</router-link></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15">属性组名称</td>
                  <td class="pdt15 pdb15">属性类别</td>
                  <td class="pdt15 pdb15">状态</td>
                  <td class="pdt15 pdb15">排序</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in attrGroupList">
                  <td>{{item.groupName}}</td>
                  <td>{{item.cName}}</td>
                  <td>{{item.status==1?"启用":"停用"}}</td>
                  <td>{{item.groupId}}</td>
                  <td>
                    <a href="javascript:void(0)" class="btn btn-xs btn-primary" @click="editAttrGroup(item.groupId)">编辑</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger" @click="stopAttrGroup(item.groupId)">停用</a>
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
                <select class="form-control ios0" v-model="params2.cid" @change="selectCategory2" >
                  <option value="0">所属类别</option>
                  <option  :value="item.id" v-for="(item,index) in selectGoodsCategory">{{item.name}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="params2.status" @change="selectStatus2" >
                  <option value="0">状态</option>
                  <option value="1">启用</option>
                  <option value="2">停用</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline">
                  <div class="input-group" @keyup.13="search2">
                    <input type="text" class="form-control" placeholder="属性名称" v-model="params2.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search2">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><router-link :to="{name:'addAttr'}" class="db pdl29 corED145B ios2">新增</router-link></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15">属性名称</td>
                  <td class="pdt15 pdb15">属性类别</td>
                  <td class="pdt15 pdb15">是否必填</td>
                  <td class="pdt15 pdb15">是否suk属性</td>
                  <td class="pdt15 pdb15">状态</td>
<!--                  <td class="pdt15 pdb15">类别排序吗</td>
                  <td class="pdt15 pdb15">属性排序</td>
                  <td class="pdt15 pdb15">组内排序</td>-->
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in attrList">
                  <td>{{item.attrName}}</td>
                  <td>{{item.cName}}</td>
                  <td>{{item.ismust==1?"是":"否"}}</td>
                  <td>{{item.issku==1?"是":"否"}}</td>
                  <td>{{item.status==1?"启用":"停用"}}</td>
           <!--       <td>{{item.attrName}}</td>
                  <td>{{item.attrName}}</td>
                  <td>{{item.attrName}}</td>-->
                  <td>
                    <a href="javascript:void(0)" class="btn btn-xs btn-primary">编辑</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger">停用</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容3-->
         <!-- <li class="dn">
            <div class="ovh mgt20">
              <div class="fl">
                <select class="form-control ios0">
                  <option>状态</option>
                  <option>状态2</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="计量单位名称">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
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
                  <td class="pdt15 pdb15">计量单位名称</td>
                  <td class="pdt15 pdb15">描述</td>
                  <td class="pdt15 pdb15">状态</td>
                  <td class="pdt15 pdb15">排序</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr>
                  <td>墨水特性</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>
                    <a href="#" class="btn btn-xs btn-primary">编辑</a>
                    <a href="#" class="btn btn-xs btn-danger">停用</a>
                    <a href="#" class="btn btn-xs btn-success">上移</a>
                    <a href="#" class="btn btn-xs btn-info">下移</a>
                  </td>
                </tr>
                <tr>
                  <td>墨水特性</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>
                    <a href="#" class="btn btn-xs btn-primary">编辑</a>
                    <a href="#" class="btn btn-xs btn-danger">停用</a>
                    <a href="#" class="btn btn-xs btn-success">上移</a>
                    <a href="#" class="btn btn-xs btn-info">下移</a>
                  </td>
                </tr>
                <tr>
                  <td>墨水特性</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>启用</td>
                  <td>
                    <a href="#" class="btn btn-xs btn-primary">编辑</a>
                    <a href="#" class="btn btn-xs btn-danger">停用</a>
                    <a href="#" class="btn btn-xs btn-success">上移</a>
                    <a href="#" class="btn btn-xs btn-info">下移</a>
                  </td>
                </tr>

                </tbody>
              </table>
            </div>
          </li>-->
          <!--内容4-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl">
                <select class="form-control ios0" v-model="params3.brandStatus" @change="selectStatus3">
                  <option value="0">状态</option>
                  <option value="1">启用</option>
                  <option value="2">停用</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline">
                  <div class="input-group" @keyup.13="search3">
                    <input type="text" class="form-control" placeholder="品牌中文名称" v-model="params3.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search3" >
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><router-link :to="{name:'addBrand'}" class="db pdl29 corED145B ios2">新增</router-link></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15">品牌中文名称</td>
                  <td class="pdt15 pdb15">品牌英文名称</td>
                  <td class="pdt15 pdb15">品牌描述</td>
                  <td class="pdt15 pdb15">品牌logo</td>
                  <td class="pdt15 pdb15">状态</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in brandList">
                  <td>{{item.brandChName}}</td>
                  <td>{{item.brandEnName}}</td>
                  <td>{{item.description}}</td>
                  <td>{{item.brandEnName}}</td>
                  <td>{{item.brandStatus==1?"启用":"停用"}}</td>
                  <td>
                    <a href="javascript:void(0)" class="btn btn-xs btn-primary">编辑</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger">停用</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容5-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl">
                <select class="form-control ios0" v-model="params4.status" @change="selectStatus4">
                  <option value="0">状态</option>
                  <option value="1">启用</option>
                  <option value="2">停用</option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline">
                  <div class="input-group" @keyup.13="search4">
                    <input type="text" class="form-control" placeholder="类别名称" v-model="params4.string">
                    <span class="input-group-btn">
									<button type="button" class="btn bged145b" @click="search4">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30"><router-link :to="{name:'addGoodsCategory'}" class="db pdl29 corED145B ios2">新增</router-link></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="mgt30">
              <table class="t-c ww100 tab2">
                <tbody>
                <tr>
                  <td class="pdt15 pdb15">类别名称</td>
                  <td class="pdt15 pdb15">上级分类</td>
                  <td class="pdt15 pdb15">状态</td>
                  <td class="pdt15 pdb15">级别</td>
                  <td class="pdt15 pdb15">操作</td>
                </tr>
                <tr v-for="(item,index) in goodsCateGoryList">
                  <td>{{item.cname}}</td>
                  <td>{{item.pName}}</td>
                  <td>{{item.status==1?"启动":"停用"}}</td>
                  <td>{{item.lev}}</td>
                  <td>
                    <a href="javascript:void(0)" class="btn btn-xs btn-primary">编辑</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger">停用</a>
                    <!--<a href="#" class="btn btn-xs btn-success">上移</a>
                    <a href="#" class="btn btn-xs btn-info">下移</a>-->
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
    name: "platformGoods",
    data() {
      return {
        params1:{pageNum: 1, pageSize:10, string:'', status:0},//属性分组
        params2:{pageNum: 1, pageSize:10, string:'', status:0 , cid:0},//属性
        params3:{pageNum: 1, pageSize:10, string:'', brandStatus:0},//品牌
        params4:{pageNum: 1, pageSize:10, string:'', status:0},//商品分类
        checkTab:1,//页签tab

        attrGroupList:[],
        attrList:[],
        brandList:[],
        goodsCateGoryList:[],
        selectGoodsCategory:[],


        pageData:{
          curPage:1,//当前页吗
          totalCount:1,//总条数
          pageSize:1//页面大小
        },
      }
    },
    created() {
      selectAttrGroupList();
      initCategory();
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
          $("#5id").click();
        }
        else if(status==2){
          $("#6id").click();
        }
        else if(status==3){
          $("#7id").click();
        }
        else if(status==4){
          $("#8id").click();
        }
      }
    },
    methods: {
      chekdpage: function(pageNum) {
          if(_this.checkTab==1){
            _this.params1.pageNum = pageNum;
            selectAttrGroupList();
          }else if(_this.checkTab==2){
            _this.params2.pageNum = pageNum;
            selectAttrList();
          }else if(_this.checkTab==3){
            _this.params3.pageNum = pageNum;
            selectBrandList();
          }else {
            _this.params4.pageNum = pageNum;
            selectgoodsCategoryList();
          }
      },
      selectStatus1:function () {
        selectAttrGroupList();
      },
      search1:function () {
        selectAttrGroupList();
      },
      selectStatus2:function () {
        selectAttrList();
      },
      selectCategory2:function () {
        selectAttrList();
      },
      search2:function () {
        selectAttrList();
      },
      selectStatus3:function () {
        selectBrandList();
      },
      search3:function () {
        selectBrandList();
      },
      selectStatus4:function () {
        selectgoodsCategoryList();
      },
      search4:function () {
        selectgoodsCategoryList();
      },

      editAttrGroup:function () {

      },
      stopAttrGroup:function (groupId) {
        //stopAttrGroup(groupId);
      }
    },
    watch:{
      checkTab(newVal,oldVal){
       console.log(newVal);
        if(newVal != oldVal) {
          if(newVal==1){
            _this.params1.pageNum=1;
            selectAttrGroupList();
          }else if(newVal==2){
            _this.params2.pageNum=1;
            selectAttrList();
          }else if(newVal==3){
            _this.params3.pageNum=1;
            selectBrandList();
          }else{
            _this.params4.pageNum=1;
            selectgoodsCategoryList();
          }
        }
      },
    },
  }
  function selectAttrGroupList(){    //查询商品分类列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/attrGroup/list',
      params:  _this.params1
    }).then((res) => {
      if (res.status == 200) {
        _this.attrGroupList=res.data.rows;
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params1.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params1.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }
  function selectAttrList(){    //查询商品分类列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/attr/list',
      params:  _this.params2
    }).then((res) => {
      if (res.status == 200) {
        _this.attrList=res.data.rows;
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params2.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params2.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }
  function selectBrandList(){    //查询商品分类列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goodsBrand/list',
      params:  _this.params3
    }).then((res) => {
      if (res.status == 200) {
        _this.brandList=res.data.rows;
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params3.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params3.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }
  function selectgoodsCategoryList(){    //查询商品分类列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goodsCategory/list',
      params:  _this.params4
    }).then((res) => {
      if (res.status == 200) {
        _this.goodsCateGoryList=res.data.rows;
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params4.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params4.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  //商品类别
  function initCategory() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/attr/findGoodsCategory',
    }).then((res) => {
      _this.selectGoodsCategory=res.data
    })
  }
  //停用属性分组
  function stopAttrGroup(groupId) {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/attrGroup/update',
      params:  {groupId:groupId,status:2}
    }).then((res) => {
      if (res.status == 200) {

      }
    })
  }
</script>
