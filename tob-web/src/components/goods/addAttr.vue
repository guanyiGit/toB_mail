<template>

  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增属性</p>
        <router-link href="###" class="db fr fs18 cor0" :to="{name:'platformGoods'}">返回</router-link>
      </div>
      <div class="mgt20 bor-d pdt30 pdb120">
        <div class="w600 c-b">
          <form action="#">
            <div class="ovh">
              <div class="fl w100 fs16 cor0 h40 lh40">商品类别：</div>
              <div class="block">
                <el-cascader
                  placeholder="试试搜索"
                  :options="options"
                  filterable
                  @change="handleChange"
                ></el-cascader>
              </div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w100 fs16 cor0 h40 lh40">属性分组：</div>
              <div class="block">
                <el-select v-model="attr.groupId" placeholder="请选择">
                  <el-option
                    v-for="item in options2"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                  </el-option>
                </el-select>
              </div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w100 fs16 cor0 h40 lh40">属性名称：</div>
              <div class="mgl100"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="attr.attrName"></div>
            </div>
            <div class="ovh">
              <div class="fl w100 fs16 cor0 h40 lh40 pdt13">是否必填：</div>
              <div class="mgl100 pdt10">
                <p class="fl mgt10 w140"><label class="lab2"><input type="radio" v-model="attr.ismust" value="1"><i></i>是</label></p>
                <p class="fl mgt10 w140"><label class="lab2"><input type="radio"  v-model="attr.ismust" value="2"><i></i>否</label></p>
              </div>
            </div>
            <div class="ovh">
              <div class="fl w100 fs16 cor0 h40 lh40 pdt13">是否sku属性：</div>
              <div class="mgl100 pdt10">
                <p class="fl mgt10 w140"><label class="lab2"><input type="radio" v-model="attr.issku" value="1"><i></i>是</label></p>
                <p class="fl mgt10 w140"><label class="lab2"><input type="radio"  v-model="attr.issku" value="2"><i></i>否</label></p>
              </div>
            </div>
<!--            <div class="ovh mgt20">
              <div class="fl w100 fs16 cor0 h40 lh40">类别排序吗：</div>
              <div class="mgl100"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w100 fs16 cor0 h40 lh40">组内排序：</div>
              <div class="mgl100"><input class="db ww100 h40 lh40 bor-d" type="text"></div>
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
    name: "addAttr",
    data() {
      return {
        attr:{
          status:1,
          cid:-1,
          groupId:'',
          attrName:'',
          ismust:1,
          issku:2,
        },
        options:[],
        options2:[]
      }
    },
    created() {
      initCategory();
    },beforeCreate(){
      _this = this;
    },
    methods: {
      save:function () {

        if(_this.attr.cid==-1){
          _this.$alert("请选择商品分类");
          return false
        }

        if(_this.attr.groupId==''){
          _this.$alert("请选择属性分组");
          return false
        }
        if(_this.attr.attrName==''){
          _this.$alert("属性名称不能为空");
          return false
        }
        saveAttr();
      },
      cancel:function () {
        _this.$router.push({name: 'platformGoods',query:{status:2}});
      },
      handleChange:function (e) {
        _this.attr.cid= e[(e.length-1)];
        initAttrGroup();
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

  //属性分组
  function initAttrGroup() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-goods/biz/attrGroup/findAttrGroupByCate',
      params: {cId:_this.attr.cid}
    }).then((res) => {
      _this.options2=res.data;
    })
  }

  //添加商品属性
  function saveAttr() {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/attr/save',
      params: _this.attr
    }).then((res) => {
      if(res.status==200){
        _this.attr={};
        _this.$alert("添加成功");
        _this.$router.push({name: 'platformGoods',query:{status:2}});
      }
    })
  }
</script>
