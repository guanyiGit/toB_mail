<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <router-link :to="{name:'dogManager_BanList'}">禁养犬种></router-link>
        </li>
        <li>新增禁养犬种</li>
      </ul>
    </div>

    <div class="mgt20 k3 pdt10 pdl30 pdr30 pdb120">

      <div class="mgt10 ww100">
        <span class="db w90 fl h40 lh40 cor0">犬种名称<font color="red"> *</font></span>
        <div class="mgl90">
          <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="banName">
        </div>
      </div>
      <div class="mgt20">
        <span class="db w90 fl h40 lh40 cor0">上传照片<font color="red"> *</font></span>
        <div class="mgl90 ovh">
          <img-upload ref="imgs"></img-upload>
          <!--<div class="file fl rel w202 h160">-->
          <!--<input type="file" class="abs z10 w202 h160 left0 top0">-->
          <!--<img class="rel z1" src="images/28-8.png">-->
          <!--<p class="abs cor9 ww100 z8 left0 top90 t-c">上传照片</p>-->
          <!--</div>-->
        </div>
      </div>
      <div class="mgt20">
        <span class="db w90 fl h40 lh40 cor0">备注</span>
        <div class="mgl90">
          <textarea class="ww100 h180 pdl20 borf1 ras4" v-model="remrks"></textarea>
        </div>
      </div>
    </div>
    <div class="t-c mgt40 mgb50">
      <input type="submit" @click.prevent="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort cupo"
             value="提交">
      <a href="###" @click.prevent="reset" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
    </div>
  </div>
</template>

<script>
  export default {
    name: "dogManagerBanAdd",
    data() {
      return {
        banName: '',//禁养犬主名称
        remrks: '',//备注信息
        datePattern: "yyyy年MM月dd日",
        urls: [],//图片
      }
    },
    watch: {},
    created() {
    },
    methods: {
      reset() {
        this.banName = '';
        this.remrks = '';
        this.urls = [];
      }
      ,
      submit() {
        if (this.banName && this.banName.trim().length > 0) {
          this.$axios({
            url: '/biz/ban/checkd',
            params: {'name': this.banName}
          }).then(res => {
            console.log(res)
            if (res.data.status == 200 && res.data.data) {
              this.$alert("该禁养品种已存在！")
            } else {
              let params = {
                banName: this.banName.trim()
              }
              if (this.remrks) params.remrks = this.remrks;
              this.$axios({
                url: '/biz/ban/save',
                params,
                isloading: true
              }).then(res => {
                if (res.data.status == 200) {
                  this.$alert("添加成功！")
                  debugger
                  //图片上传
                  let imgs = this.$refs.imgs;
                  imgs.upload({
                    type: 7,
                    id: res.data.data
                  })
                  return;
                } else {
                  this.$alert("添加失败！")
                }
              })
            }
          })


        } else {
          this.$message("禁养犬种名称没有填写！")
        }
      }
    }
  }


</script>

<style scoped>
  select {
    height: 39px;
  }

  .dataSelect {
    width: 100%;
  }

  .ww160 {
    width: 160px;
  }
</style>
