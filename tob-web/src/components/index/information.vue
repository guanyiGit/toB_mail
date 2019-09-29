;<template>
  <!--动态资讯-->
  <div class="w1200 c-b pdt30 pdb40 ovh">
    <div class="fl w75"><img src="/static/images/4.png" alt=""></div>
    <div class="mgl115 scroll-wrap">
      <table class="scroll-content" :style="{ top }" width="100%" border="1">
        <tbody>
        <tr v-for="(item,index) in infos">
          <td class="ww33 h37" v-for="(e,i) in item">
            <router-link :to="{name:'infoDetails',params:{id:e.informationId,type:4}}" class="db pdl13 pc1" style="font-size: 16px">{{e.infoTitile.length<20?e.infoTitile:e.infoTitile.substring(0,19)}}</router-link>
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
    data: {TypeNumber: 1, status: 1, pageNum: 1,pageSize:100},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
  }
  export default {
    name: "information",
    data() {
      return {
        infos: [],//资讯列表
        activeIndex: 0,
      }
    },
    computed: {
      top() {
        return - this.activeIndex * 40 + 'px';
      }
    },
    created(){
      this.initmet();
      setInterval(_ => {
        if (this.activeIndex < this.infos.length-1) {
          this.activeIndex += 1;
        } else {
          this.activeIndex = 0;
        }
      }, 2000);
      console.log(this.infos);
    },
    methods:{
      initmet(){
        //查询资讯列表
        this.$axios({
          method: 'get',
          url: '/biz/information/getInformations',
          params: params.data
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
  }
</script>

<style scoped>
  .scroll-wrap{
    height: 70px;
    overflow: hidden;
  }
  .scroll-content {
    position: relative;
    transition: top 0.5s;
  }
</style>
