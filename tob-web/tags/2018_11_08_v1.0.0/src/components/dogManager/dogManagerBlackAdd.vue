<template>
  <!--内容区-->
  <div class="w980 fr test">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <router-link :to="{name:'dogManager_blacklist'}">黑名单&nbsp;&gt;&nbsp;</router-link>
        </li>
        <li>新增</li>
      </ul>
    </div>

    <div class="mgt10 k3">
      <div class="ovh pdb100">
        <div class="w730 fl">
          <div class="ovh pdt10">
            <div class="mgt10 ww100 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">姓名<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.blackName">
              </div>
            </div>
          </div>

          <div class="ovh pdt10">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">证件类型</span>
              <div class="mgl100">
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                <el-select class="ww100 h40 lh40  borf1 ras4" v-model="blackInfo.cardType" placeholder="请选择">
                  <el-option
                    v-for="(item,index) in cardTypes"
                    :key="index"
                    :label="item.dictionaryDescribe"
                    :value="item.dictionaryValue"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove">
                  </el-option>
                </el-select>


                <!--<select class="ww100 h40 lh40 pdl20 borf1 ras4"  v-model="blackInfo.cardType">-->
                <!--<option value="00" selected="selected">00</option>-->
                <!--<option v-for="(item,index) in cardTypes" :key="index" :value="item.dictionaryValue" >-->
                <!--{{item.dictionaryDescribe}}-->
                <!--</option>-->
                <!--</select>-->
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">证件号码</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.cardNum">
              </div>
            </div>
          </div>

          <div class="ovh pdt10">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">性别<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <!--<select class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.sex">-->
                <!--<option value="0">男</option>-->
                <!--<option value="1">女</option>-->
                <!--</select>-->

                <el-select class="ww100 h40 lh40  borf1 ras4" v-model="blackInfo.sex" placeholder="请选择">
                  <el-option value="0" label="男"></el-option>
                  <el-option value="0" label="女"></el-option>
                </el-select>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">出生日期</span>
              <div class="mgl90">
                <el-date-picker v-model="blackInfo.birthData" type="date" class="ww100 h40 lh40 borf1 ras4 pc6"
                                placeholder="选择出生日期" format="yyyy 年 MM 月 dd 日"></el-date-picker>
                <!--<input id="inp1264" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc6">-->
                <!--<input type="date" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.birthData">-->
              </div>

            </div>
          </div>
          <div class="ovh pdt10">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">现住区县</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.county">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">所属部门</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.dep">
              </div>
            </div>
          </div>

          <div class="ovh pdt10">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">所属社区</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.community">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">邮编</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.postcode">
              </div>
            </div>
          </div>
          <div class="ovh pdt10">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">联系电话<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.phone">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.email">
              </div>
            </div>
          </div>
          <div class="ovh pdt10">
            <div class="mgt10 ww100 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">详细地址</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.address">
              </div>
            </div>
          </div>
          <div class="ovh pdt10">
            <div class="mgt10 ww100 fl">
              <span class="db w135 fl h40 lh40 cor0 pdl30">列入黑名单原因</span>
              <div class="mgl135">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="blackInfo.joinCause">
              </div>
            </div>
          </div>
          <div class="ovh pdt10">
            <div class="mgt10 ww100 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
              <div class="mgl100">
                <textarea class="ww100 h140 borf1 pdl10 pdr10 pdt10 pdb10" v-model="blackInfo.remark"></textarea>
              </div>
            </div>
          </div>
        </div>
        <div class="mgl730">
          <img class="c-b" src="/static/images/b16.png" alt="">
        </div>
      </div>
    </div>
    <div class="t-c mgt40 mgb50">
      <input type="submit" @click.prevent="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort cupo" value="提交">
      <a href="###" @click.prevent="reset" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
    </div>

    <div class="t-c mgt40 mgb50">
      <span>{{alert.msg}}</span>
      <el-dialog
        title="提示"
        :visible.sync="alert.dialogVisible"
        width="30%"
      >
            <span slot="footer" class="dialog-footer">
              <!--<el-button @click="alert.dialogVisible = false">取 消</el-button>-->
              <el-button type="primary" @click="alert.dialogVisible = false">确 定</el-button>
            </span>
      </el-dialog>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "dogManagerBlackAdd",
    data() {
      return {
        blackInfo: {
          blackName: '',//性名
          cardType: '',//证件类型 0身份证1房产证2营业执照3其他 默认选择第一个
          cardNum: '',//证件号码
          sex: '',//性别
          birthData: '',//出生日期
          county: '',//区县
          dep: '',//部门
          community: '',//社区
          postcode: '',//邮编
          phone: '',
          email: '',
          address: '',
          joinCause: '',//加入黑名单原因
          remark: ''//加入原因
        },
        cardTypes: [],//所有证件类型
        dialogImageUrl: '',
        dialogVisible: false,
        alert: {
          msg: ''
        }
      }
    }, created() {
      ininData(this)
    }, mounted() {
      console.log(this.$data)
    }, methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      reset(){
        this.blackInfo = {};
      },
      submit(){
        if(!this.blackInfo.sex){
          this.$alert("性别必须填写！")
          return;
        }
        if(!this.blackInfo.blackName){
          this.$alert("姓名必须填写！")
          return;
        }
        if(!this.blackInfo.phone){
          this.$alert("手机号必须填写！")
          return;
        }
        if(!(/0?(13|14|15|17|18|19)[0-9]{9}/.test(this.blackInfo.phone))){
          this.$alert("手机号格式不正确！")
          return;
        }
        if(this.blackInfo.email && !(/\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/.test(this.blackInfo.email))){
          this.$alert("邮箱格式不正确！")
          return;
        }
        if(this.blackInfo.email && !(/\d{6}/.test(this.blackInfo.postcode))){
          this.$alert("邮编格式不正确！")
          return;
        }


        let loadingInstance = this.$loading({
          lock: true,
          text: '正在处理，请稍后...',
          background: 'rgba(0, 0, 0, 0.3)'
        })
        this.$axios({
          url:'/biz/balck/save',
          method:"post",
          headers:{
            'Content-Type': 'application/json;charset=UTF-8'
          },
          data:JSON.stringify(this.blackInfo)
        }).then(res=>{
          if(res.data.status==200){
            this.$alert("保存成功！")
            this.$emit("reset")
          }else{
            this.$alert("保存失败！")
          }
        }).finally(_=>{
          this.$nextTick(() => loadingInstance.close());
        })
      }
    }
  }

  //查看所有证件类型
  function ininData(_this) {
    _this.$axios({
      url: "/biz/Tdictionary/card_type"
    }).then((res) => {
      if (res.data.meta.success) {
        _this.cardTypes = res.data.data
      }
    }).catch((res) => {
      console.log(res)
    })
  }


</script>
<style scoped>
  .el-date-editor.el-input {
    width: 100%;
  }
  .cupo{
    cursor: pointer;
  }
</style>
