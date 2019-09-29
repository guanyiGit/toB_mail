<template>

  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新建组织</p>

        <a href="###" class="db fr fs18 cor0">返回>></a>
      </div>

      <div class="mgt20 bor-d pdt30 pdb150">
        <div class="w600 c-b">
          <form  @submit.prevent="submit">
            <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">组织编号：</div>
              <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="forms.orgNo" placeholder="组织编号（可选）"></div>
            </div>
            <div style="display: flex;align-items: center" class="mgt30">
              <div class="fs16 cor0" style="width: 20%">组织名称</div>
              <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="forms.orgName"></div>
            </div>
            <div style="display: flex;align-items: center" class="mgt30" v-if="!isplatformAdmin">
              <div class="fs16 cor0" style="width: 20%">上级组织</div>
              <div style="width:80%"><treeselect v-model="forms.pid"  :options="selectOrgList" placeholder="请选择上级组织" :normalizer="normalizer" openDirection="bottom" noChildrenText="暂无子组织"/></div>
            </div>

            <!--<div class="ovh">-->
              <!--<div class="fl w80 fs16 cor0 h40 lh40">组织编号：</div>-->
              <!--<div class="mgl80"><input class="db bor-d ww100 h40 lh40" type="text" name="orgNo" v-model="forms.orgNo"></div>-->
              <!---->
            <!--</div>-->
            <!--<div class="ovh mgt20">-->
              <!--<div class="fl w80 fs16 cor0 h40 lh40">组织名称：</div>-->
              <!--<div class="mgl80"><input class="db bor-d ww100 h40 lh40" type="text" name="orgName" v-model="forms.orgName"></div>-->
            <!--</div>-->
            <!--<div class="ovh mgt20" v-if="!isplatformAdmin">-->
              <!--<div class="fl w80 fs16 cor0 h40 lh40">上级组织：</div>-->
              <!--<div class="mgl80">-->

                <!--&lt;!&ndash;<select class="db bor-d ww100 h40 lh40 pdl10 pdr10 ios0" v-model="forms.pid">&ndash;&gt;-->
                  <!--&lt;!&ndash;<option v-for="(item,index) in selectOrgList" :value="item.id">{{ item.orgName }}</option>&ndash;&gt;-->
                <!--&lt;!&ndash;</select>&ndash;&gt;-->
              <!--</div>-->
            <!--</div>-->
            <div class="mgt40 t-c">
              <input type="submit" class="w100 btn btn-primary" value="保存">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="cancel">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

    export default {
        name: "platformOrgAdd",
      data(){
          return {
            isplatformAdmin:true,
            forms:{},
            selectOrgList:[],
            normalizer(node){
              return {
                id: node.value,
                label: node.label,
                children: node.children,
              }
            }
          }
      },

      created(){
        //获取用户信息
        this.configJS.getUserInfo(this);
        //判断是否是平台管理员
        var  isplatformAdmin =  this.configJS.isplatformAdmin();
        this.isplatformAdmin = isplatformAdmin;
        if(!this.isplatformAdmin){
          initOrgSelect(this);
        }
      },

      watch:{

      },

      methods:{
        /**
         * 取消，返回列表页
         */
        cancel(){
          this.$router.push({name:'platformAdmin'})
        },
        /**
         * 提交
         */
        submit(){
          console.log(this.forms);
          var that = this;
          var forms  =  this.forms;
          if(!forms.orgName){
            this.$alert("组织名称不能为空");
            return;
          }
          if (!this.isplatformAdmin){
            if(!forms.pid){
              this.$alert("上级组织不能为空");
            }
          }
          forms.createBy = this.configJS.userInfo.userId;
          //loading 加载
          var loading =  this.$loading({
            lock: true,
            text: '加载中',
          })
          that.$axios({
            url:'/biz/tob-user/system/orgInfo/save',
            method:'post',
            params:forms
          }).then((res) => {
            if(res.data.code == 200){
                that.$message({
                  type: 'success',
                  message: '添加成功!',
                  duration: 1000,
                  onClose(){
                    that.$router.push({name:'platformAdmin'})
                  }
                })
            }
          }).catch((err) => {
            console.log(err);
          }).finally( res =>{
            loading.close();
          })
        }
      }
    }

    function initOrgSelect(that) {
      that.$axios({
        url: '/biz/tob-user/system/orgInfo/initOrgSelect',
        params:{
          orgId:that.configJS.userInfo.orgId,
          pid:that.configJS.userInfo.orgInfoDO.pid,
          type:1
        }
      }).then((res) => {
        console.log("initOrgSelect",res);
        if(res.status == 200){
          that.selectOrgList = res.data;
        }
      })
    }


</script>

<style scoped>
  input::-webkit-input-placeholder {
           /* placeholder颜色  */
            color: #aab2bd;
            /* placeholder字体大小  */
            font-size: 12px;
             /* placeholder位置  */
              /*text-align: right;*/
         }
</style>
