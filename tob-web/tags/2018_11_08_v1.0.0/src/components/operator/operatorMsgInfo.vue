<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link style="cursor:pointer;" :to="{name:'operator_msgAuditList'}">信息审核></router-link></li>
        <li>审核</li>
      </ul>
    </div>
    <form action="#">
      <div class="ovh k3 mgt20">
        <div class="pdt23 pdb23 bgf1 t-c">
          <p class="fs20 coadsr0">{{info?info.childTitile?info.childTitile:'':''}}</p>
          <p class="cor9 mgt15">类型：<span>{{info?info.informationTypeName?info.informationTypeName:'':''}}</span> 发布单位：<span>{{info?info.pubishOrg?info.pubishOrg:'':''}}</span> 发布时间：<span v-if="info&&info.creationTime">{{info.creationTime | formatDate('yyyy-MM-dd')}}</span></p>
        </div>
        <div class="pdt50 pdb150 pdl30 pdr30" v-html="info?info.infoContent?info.infoContent:'':''">
          <p class="fs16 cor0 fb">一、办理条件</p>
          <p class="cor9 lh28 mgt15">
            1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>
            2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>
            3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶的归宿，领养时犬只需收取保证金300-1000元
          </p>
          <p class="fs16 cor0 fb mgt20">二、办理材料</p>
          <p class="cor9 lh28 mgt15">
            1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>
            2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>
            3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶领养时犬只需收取保证金300-1000元<br>
          </p>
          <p class="fs16 cor0 fb mgt20">三、办理流程</p>
          <p class="cor9 lh28 mgt15">
            1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>
            2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>
            3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是稳定的归宿，领养时犬只需收取保证金300-1000元
          </p>
          <p class="fs16 cor0 fb mgt20">四、办理地点</p>
          <p class="cor9 lh28 mgt15">
            1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>
            2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>
            3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是定的归宿，领养时犬只需收取保证金300-1000元
          </p>
          <p class="fs16 cor0 fb mgt20">五、办理时间及费用</p>
          <p class="cor9 lh28 mgt15">
            1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>
            2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>
            3infoTitile、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是定的归宿，领养时犬只需收取保证金300-1000元
          </p>
        </div>
      </div>
      <div class="t-c mgt40 mgb50">
        <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 mgl5" @click="showw">审核不通过</a>
        <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="through">审核通过</a>
        <router-link style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click="cancel" :to="{name:'operator_msgAuditList'}">取消</router-link>
      </div>
    </form>
    <!--弹窗2-->
    <div class="T" id="bbb" style="display: none">
      <div class="tan3 bgw rel">
        <div class="h60 lh60 bgf1 fs16 cor0 pdl30 fb">审批不通过原因</div>
        <form action="#">
          <div class="pdt10 pdl85 pdr85 ovh">
            <textarea id="whyy" rows="15" style="width: 100%" placeholder="请输入原因：" v-model="why"></textarea>
          </div>
          <div class="t-c mgt40 mgb50">
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="notThrough">提交</a>
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click="cancell">取消</a>
          </div>
        </form>
        <a style="cursor:pointer;" class="db abs close w28 h28"><img src="../../../static/images/50.png" alt="" @click="cancell"></a>
      </div>
    </div>
    <!--弹窗2-->
  </div>
  <!--内容区END-->
</template>

<script>
  import $ from 'jquery'
    export default {
        name: "operatorMsgInfo",
      data(){
          return{
            info:null,
            why:null
          }
      },
      created(){
        this.$axios({
          url: '/biz/information/getInformation',
          method: 'post',
          params: {informationId: this.$route.params.id}
        }).then(res => {
          console.log(res)
          if(res.data.status == 200){
            this.info = res.data.data;
          }
        })
      },
      methods:{
          showw(){
            $("#bbb").show();
          },
        cancell(){
          $("#whyy").val("");
          $("#bbb").hide();
        },
        notThrough(){
          this.$axios({
            url: '/biz/information/examine',
            method: 'post',
            params: {status: 2,informationId: this.$route.params.id,failReason:this.why}
          }).then(res => {
            console.log(res)
            console.log('111')
            if(res.data.status == 200){
              this.$alert("提交成功！");
              this.$router.push({name:'operator_msgAuditList'});//其中operatorMsgAuditLis是你定义的一个路由模块
            }
          })
        },
        through(){
          this.$axios({
            url: '/biz/information/examine',
            method: 'post',
            params: {status: 1,informationId: this.$route.params.id}
          }).then(res => {
            console.log(res)
            console.log('111')
            if(res.data.status == 200){
              this.$alert("提交成功！");
              this.$router.push({name:'operator_msgAuditList'});//其中operatorMsgAuditLis是你定义的一个路由模块
            }
          })
        },
        cancel(){
          this.info=null;
          // this.$router.push('operator_msgAuditList');//其中operatorMsgAuditLis是你定义的一个路由模块
        }
      }
    }
</script>

<style scoped>

</style>
