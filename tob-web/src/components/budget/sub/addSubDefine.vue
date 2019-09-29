<template>
  <div class="wrap ww100 pdt100">
    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="c-b mm100 mgt30" style="width: 100%">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">新增科目</p>
          <a href="###" class="db fr fs18 cor0"  @click.prevent="cancel">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb50">
          <div class="w600 c-b">
            <form @submit.prevent="submit">
              <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
                <div class="fs16 cor0" style="width: 20%">年份:</div>
                <div style="width:80%">
                  <el-date-picker
                    v-model="forms.year"
                    type="year"
                    format="yyyy 年"
                    value-format="yyyy"
                    placeholder="请选择年份">
                  </el-date-picker>
                </div>
              </div>
              <div style="display: flex;align-items: center" class="mgt30">
                <div class="fs16 cor0" style="width: 20%">科目定义名称:</div>
                <div style="width:80%">
                    <input type="text" class="db ww100 h40 lh40 bor-d" v-model="forms.subDefineName" />
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
    export default {
        name: "addSubDefine",
      data(){
          return {
            forms:{}
          }
      },

      created(){

      },

      methods:{

        cancel(){
          this.$router.push({name:"subDefineList"})
        },

        submit(){
          var that = this;
          var forms = that.forms;
          forms.orgId = that.configJS.userInfo.orgId;
          that.$axios({
            url:"/biz/tob-budget/subject/addSubDefine",
            method:"post",
            headers:{
              'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
            },
            data:JSON.stringify(forms)
          }).then( (res) => {
            console.log(res);
            if (res.data.code == 200){
              that.$message({
                type: 'success',
                message: '添加成功!',
                duration: 1000,
                onClose(){
                  that.$router.push({name:'subDefineList'})
                }
              })
            }
          })
        }
      }
    }
</script>

<style scoped>

</style>
