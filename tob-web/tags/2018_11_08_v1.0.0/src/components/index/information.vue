<template>
  <!--动态资讯-->
  <div class="w1200 c-b pdt30 pdb40 ovh">
    <div class="fl w75"><img src="/static/images/4.png" alt=""></div>
    <div class="mgl115">
      <table width="100%" border="1">
        <tbody>
        <tr v-for="(item,index) in infos" v-if="index<2">
          <td class="ww33 h37" v-for="(e,i) in item">
            <router-link :to="{name:'Information'}" class="db pdl13 pc1">{{e.infoTitile}}</router-link>
          </td>
        </tr>
        </tbody>
      </table>

    </div>
  </div>
  <!--动态资讯END-->
</template>

<script>

  //请求参数
  let params = {
    data: {TypeNumber: null, status: null, pageNum: 1},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
  }
  export default {
    name: "information",
    data() {
      return {
        infos: [],//资讯列表
      }
    },
    beforeCreate() {
      //查询资讯列表
      this.$axios({
        method: 'post',
        url: '/biz/information/getInformations',
        data: params.data
      }).then((res) => {
        if (res.data.status == 200) {
          let tempArr = [];
          res.data.data.list.map((item, index) => {
            if (index != 0 && index % 3 == 0) {
              this.infos.push(tempArr)
              tempArr = [];
            }
            tempArr.push(item)
          })
          if (tempArr.length > 0) {
            this.infos.push(tempArr)
          }
        }
      }).catch((err)=>{
        console.log(err);
      });
    }
  }
</script>

<style scoped>

</style>
