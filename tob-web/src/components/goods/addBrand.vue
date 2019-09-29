<template>
  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增品牌</p>
        <router-link href="###" class="db fr fs18 cor0" :to="{name:'platformGoods'}">返回</router-link>
      </div>
      <div class="mgt20 bor-d pdt30 pdb120">
        <div class="w600 c-b">
          <form action="#">
            <div class="ovh">
              <div class="fl w116 fs16 cor0 h40 lh40">品牌中文名称：</div>
              <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="goodsBrand.brandChName"></div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w116 fs16 cor0 h40 lh40">品牌英文名称：</div>
              <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="goodsBrand.brandEnName"></div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w116 fs16 cor0 h40 lh40">品牌描述：</div>
              <div class="mgl116"><input class="db ww100 h40 lh40 bor-d" type="text" v-model="goodsBrand.description"></div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w116 fs16 cor0 h40 lh40">品牌logo：</div>
              <div class="mgl116">
                <img-upload class="ovh" ref="imgup"></img-upload>
              </div>
            </div>
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
  input[type=file]{
    display: none;
  }
</style>

<script>
  var _this = '';
  export default {
    name: "addBrand",
    data() {
      return {
        goodsBrand:{
          brandStatus:1,
          brandChName:''
        },
        fileList: []
      }
    },
    created() {

    },beforeCreate(){
      _this = this;
    },
    methods: {
      save:function () {
        if(_this.goodsBrand.brandChName==''){
          _this.$alert("中文名称不能为空");
          return false
        }
        saveGoodsBrand();
      },
      cancel:function () {
        _this.$router.push({name: 'platformGoods',query:{status:3}});
      },
      submitUpload() {

        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    },
  }
  //添加商品品牌
  function saveGoodsBrand() {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-goods/biz/goodsBrand/save',
      params: _this.goodsBrand
    }).then((res) => {
      if(res.status==200){
          console.log(res);
        //let brandId= res.data.obj;
       // _this.$refs.imgup.upload({type:2,id:brandId});
        _this.$router.push({name: 'platformGoods',query:{status:3}});
        _this.$alert("添加成功");

      }
    })
  }
</script>
