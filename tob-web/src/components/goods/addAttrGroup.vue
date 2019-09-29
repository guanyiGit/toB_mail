<template>
    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="w1200 c-b mm100 mgt30">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">新增属性分组</p>
          <router-link href="###" class="db fr fs18 cor0" :to="{name:'platformGoods'}">返回</router-link>
        </div>
        <div class="mgt20 bor-d pdt30 pdb120">
          <div class="w600 c-b">
            <form action="#">

              <!--<div class="ovh mgt20">
                <div class="fl w116 fs16 cor0 h40 lh40">商城显示名称：</div>
                <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>-->
              <div class="ovh ">
                <div class="fl w116 fs16 cor0 h40 lh40">所属商品分类：</div>


                <div class="mgl116">

                  <div class="block">
                    <el-cascader
                      placeholder="试试搜索"
                      :options="options"
                      filterable
                      @change="handleChange"
                    ></el-cascader>
                  </div>
               <!--   <select class="db ww100 h40 lh40 pdl15 pdr15 ios0 bor-d" v-model="attrGroup.cid">
                    <option  :value="item.id" v-for="(item,index) in selectGoodsCategory">{{item.name}}</option>
                  </select>-->
                </div>
              </div>

              <div class="ovh mgt20">
                <div class="fl w116 fs16 cor0 h40 lh40">分组名称：</div>
                <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="attrGroup.groupName"></div>
              </div>
            <!--  <div class="ovh mgt20">
                <div class="fl w116 fs16 cor0 h40 lh40">分组说明：</div>
                <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w116 fs16 cor0 h40 lh40">排序：</div>
                <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>-->
              <div class="mgt40 t-c">
                <input type="button" class="w100 btn btn-primary" value="保存" @click="save">
                <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="cancle">
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>
</template>

<style>

</style>

<script>
  var _this = '';
  export default {
    name: "addAttrGroup",
    data() {
      return {
        attrGroup:{
          groupName:'',
          status:1,
          cid:-1
        },
        selectGoodsCategory:[],
        options:[],
      }
    },
    created() {
      initCategory();
    },beforeCreate(){
      _this = this;
    },
    methods: {
      save:function () {
          if(_this.attrGroup.groupName==''){
            _this.$alert("分组名称不能为空");
            return false
          }
        if(_this.attrGroup.cid==-1){
          _this.$alert("请选择商品分类");
          return false
        }
        saveAttrGroup();
      },
      cancle:function () {
        _this.$router.push({name: 'platformGoods',query:{status:1}});
      },
      handleChange:function (e) {
        _this.attrGroup.cid= e[(e.length-1)];
      }
    },
  }



  //商品类别
  function initCategory() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goodsCategory/findAll',
    }).then((res) => {
      _this.options=res.data.options;
    })
  }


/*  //商品类别
  function initCategory() {
    _this.$axios({
      method: 'get',
      url: '/biz/attr/findGoodsCategory',
    }).then((res) => {
      _this.selectGoodsCategory=res.data
    })
  }*/

  //添加商品属性分组
  function saveAttrGroup() {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/attrGroup/save',
      params: _this.attrGroup
    }).then((res) => {
      if(res.status==200){
        _this.attrGroup={};
        _this.$alert("添加成功");
        _this.$router.push({name: 'platformGoods',query:{status:1}});
      }
    })
  }
</script>
