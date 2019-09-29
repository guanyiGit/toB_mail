<template>

  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增商品分类</p>
        <router-link href="###" class="db fr fs18 cor0" :to="{name:'platformGoods'}">返回</router-link>
      </div>
      <div class="mgt20 bor-d pdt30 pdb120">
        <div class="w600 c-b">
          <form action="#">
            <div class="ovh">
              <div class="fl w90 fs16 cor0 h40 lh40">类别名称：</div>
              <div class="mgl90"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="goodsCategory.cName"></div>
            </div>

           <!-- <div class="ovh mgt20">
              <div class="fl w90 fs16 cor0 h40 lh40">上级分类：</div>
              <div class="mgl90">
                <select class="db ww100 h40 lh40 pdl15 pdr15 ios0 bor-d">
                  <option value="1">请选择</option>
                  <option value="1">请选择2</option>
                </select>
              </div>
            </div>-->

            <div class="block ovh mgt20">
              <div class="demonstration fl w90 fs16 cor0 h40 lh40">上级分类：</div>
                <el-cascader
                  :options="options"
                  change-on-select
                  @change="handleChange"
                ></el-cascader>
              <em v-if="isTrue1">不选择则默认为第一级分类</em>
              <em v-if="isTrue2" style="color: red">系统暂只支持三级分类</em>
            </div>
            <div class="ovh mgt20">
              <div class="fl w90 fs16 cor0 h40 lh40">级别：</div>
              <div class="mgl90"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="goodsCategory.lev" readonly></div>
            </div>

<!--            <div class="ovh mgt20">
              <div class="fl w90 fs16 cor0 h40 lh40">类别描述：</div>
              <div class="mgl90"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w90 fs16 cor0 h40 lh40">排序：</div>
              <div class="mgl90"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
            </div>-->
            <div class="mgt40 t-c">
              <input type="button" class="w100 btn btn-primary" value="保存" @click="save">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="cancel">
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
    name: "addGoodsCategory",
    data() {
      return {
        goodsCategory:{
          cName:'',
          lev:1,
          hasLeaf:2,
          parentCid:0,
          status:1
        },
        options:[],
        isTrue1:true,
        isTrue2:false,
      }
    },
    created() {
      initCategory();
    },beforeCreate(){
      _this = this;
    },
    methods: {
      save:function () {
          if(_this.isTrue2){
              return false
          }
          if(_this.goodsCategory.cName==''){
            _this.$alert("类别名称不能为空");
            return false
          }
        saveGoodsCategory();
        //initCategory();
      },
      cancel:function () {
        _this.$router.push({name: 'platformGoods',query:{status:4}});
      },
      handleChange:function (e) {
       _this.goodsCategory.parentCid= e[(e.length-1)];
       if(e.length==1){
         _this.goodsCategory.lev=2;
         _this.isTrue2=false;
         _this.isTrue1=true;
       }else if(e.length==2){
         _this.goodsCategory.lev=3;
         _this.goodsCategory.hasLeaf=1;
         _this.isTrue2=false;
         _this.isTrue1=true;
       }else{
           _this.isTrue2=true;
           _this.isTrue1=false;
       }
      }
    },
  }

  //商品类别
  function initCategory() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/goodsCategory/findAll',
    }).then((res) => {
      _this.options=res.data.options
    })
  }

  //添加商品分类
  function saveGoodsCategory() {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goodsCategory/save',
      params: _this.goodsCategory
    }).then((res) => {
      if(res.status==200){
        _this.$router.push({name: 'platformGoods',query:{status:4}});
        _this.$alert("添加成功");
      }
    })
  }
</script>
