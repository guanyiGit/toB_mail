<template>
  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增商品</p>
        <a href="###" class="db fr fs18 cor0">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w600 c-b">
          <form action="#">
            <div class="fl  fs16 cor0 h40 lh40">选择商品类别：</div>
            <div class="block">
              <el-cascader
                placeholder="试试搜索"
                :options="options"
                filterable
                @change="handleChange"
              ></el-cascader>
            </div>



            <div class="mgt40 t-c">
              <input type="button" class="w100 btn btn-primary" value="下一步" @click="next">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消">
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
    name: "addGoods1",
    data() {
      return {
        options:[]
      }
    },
    created() {
      initCategory();
    },beforeCreate(){
      _this = this;
    },
    methods: {
      handleChange:function (e) {
        _this.attrGroup.cid= e[(e.length-1)];
      },
      next:function () {
        _this.$router.push({name: 'addGoods2'});
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


</script>
