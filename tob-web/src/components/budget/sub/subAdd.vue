<template>
  <div class="wrap ww100 pdt100">

    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="c-b mm100 mgt30" style="width: 100%">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">新增科目</p>
          <a href="###" class="db fr fs18 cor0"  @click.prevent="returnLastPage">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb50">

          <div class="w600 c-b">
            <form @submit.prevent="submit">
              <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
                <div class="fs16 cor0" style="width: 20%">科目编码</div>
                <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="forms.subCode"></div>
              </div>
              <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
                <div class="fs16 cor0" style="width: 20%">科目名称</div>
                <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="forms.subName"></div>
              </div>
             <div style="display: flex;align-items: center" class="mgt30">
               <div class="fs16 cor0" style="width: 20%">上级科目</div>
               <div style="width:80%"><treeselect v-model="forms.pId"  :options="options" placeholder="请选择上级科目" :normalizer="normalizer" noChildrenText="无子科目"/></div>
             </div>

              <div class="mgt30" style="display: flex;align-items: center;border-radius: 5px">
                <div class="fs16 cor0" style="width: 20%">预算类型</div>
                <div style="width:80%">
                  <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 ios0"  v-model="forms.type">
                    <option v-for="(item,index) in types" :value="item.id">{{ item.value }}</option>
                  </select>
                </div>
              </div>

              <div class="mgt40 t-c">
                <input type="submit" class="w100 btn btn-primary" value="提交">
                <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="cancel">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <div class="footer bg333 h80 lh80 t-c fs14 corw">
      Copyright © 2010-2020 XXXXXXXXX公司  XXXXXX.com  保留一切权利。客服热线：400-888-8888
    </div>

  </div>
</template>

<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'

    export default {
        components:{Treeselect},
        name: "subAdd",
      data() {
        return {
          forms:{
            subName:"",
            subDefineId:this.$route.query.subDefineId,
          },
          value: null,
          options: [ ],
          selectOrgList:[],
          types:[
            {
              id:1,
              value:"年度预算"
            },{
              id:2,
              value:"月度预算"
            }
          ],
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
        this.configJS.getUserInfo(this);
        initSubTreeSelect(this);
      },

      methods:{
        submit(){
          debugger
          var that  = this;
          var forms = that.forms;
          forms.createBy = this.configJS.userInfo.userId;
          forms.orgId = this.configJS.userInfo.orgId;
          console.log(forms);
          var loading =  this.$loading({
            lock: true,
            text: '加载中',
          })
          that.$axios({
            url:"/biz/tob-budget/subject/addSub1",
            headers:{
              'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
            },
            method:"post",
            data:JSON.stringify(forms)
          }).then( (res) =>{
            console.log(res);
            if(res.data.code ==200){
              that.$message({
                type: 'success',
                message: '添加成功!',
                duration: 1000,
                onClose(){
                  var subDefineId = that.$route.query.subDefineId;
                  that.$router.push({name:'subList',query:{subDefineId:subDefineId}})
                }
              })
            }
          } ).finally( res => {
            loading.close();
          })
        },

        cancel(){
          var subDefineId = this.$route.query.subDefineId;
          this.$router.push({name:"subList",query:{subDefineId,subDefineId}})
        },

        returnLastPage(){
          var subDefineId = this.$route.query.subDefineId;
          this.$router.push({name:"subList",query:{subDefineId,subDefineId}})
        }

      }
    }

    /**
     * initGoodsTypeSelect
     * 加载上级科目下拉树
     */
    function initSubTreeSelect(that) {
      var orgId  = that.configJS.userInfo.orgId;
      var subId = 0;
      that.$axios({
        url: '/biz/tob-budget/subject/initSubTree/'+orgId + "/"+subId,
      }).then((res) =>{
        console.log(res)
        if(res.status == 200){
          that.options = res.data;
        }
      })
    }

</script>

<style scoped>

</style>
