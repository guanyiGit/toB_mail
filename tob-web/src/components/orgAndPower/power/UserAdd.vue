<template>
  <div class="ww100 pdl15 pdr15 pdb130">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增用户</p>
        <a href="###" class="db fr fs18 cor0">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w600 c-b">
          <form  @submit.prevent="submit">
            <!--<div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">-->
              <!--<div class="fs16 cor0" style="width: 20%">用户编号：</div>-->
              <!--<div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="form.userNo"></div>-->
            <!--</div>-->

            <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">用户名：</div>
              <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="form.username" @blur="usernameBlur"></div>
            </div>

            <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">姓名：</div>
              <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="form.name"></div>
            </div>

            <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">性别：</div>
              <div style="width:80%">
                <select class="db bor-d ww100 h40 lh40 ios0"  v-model="form.sex">
                    <option v-for="(item,index) in sexArray" :value="item.value">{{ item.label }}</option>
                 </select>
              </div>
            </div>
             <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">手机号：</div>
              <div style="width:80%"><input type="text" class="db ww100 h40 lh40 bor-d" v-model="form.mobile"></div>
            </div>

            <div class="mgt30" style="display: flex;align-items: center;border-radius: 5px" v-if="!isplatformAdmin">
              <div class="fs16 cor0" style="width: 20%">所属组织：</div>
              <div style="width:80%">
                <treeselect v-model="form.orgId"  :options="selectOrgList" placeholder="请选择所属组织" :normalizer="normalizer" openDirection="bottom" noChildrenText="暂无子组织"/>
              </div>
            </div>

            <div class="ovh mgt30" style="display: flex;align-items: center;border-radius: 5px">
              <div class="fs16 cor0" style="width: 20%">所属角色：</div>
              <div style="width:80%">
                <p class="fl mgt10 mgr30" v-for="(item,index) in checkBoxRoles"><label class="lab2"><input type="radio" :value="item.roleId"  v-model="checkRole" ><i></i>{{ item.roleName }}
                </label></p>
              </div>
            </div>

            <div class="mgt30" style="display: flex;align-items: center;border-radius: 5px" v-if="isplatformAdmin && isShowOrgSelect">
              <div class="fs16 cor0" style="width: 20%">所属组织：</div>
              <div style="width:80%">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 ios0"  v-model="form.orgId">
                  <option v-for="(item,index) in selectOrgList" :value="item.id">{{ item.orgName }}</option>
                </select>
              </div>
            </div>

            <div class="mgt100 t-c">
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
      name: "UserAdd",
      data(){
       return {
         checkRole:0,
         isShowOrgSelect:false,
         value:null,
         isplatformAdmin:true,
         selectOrgList:[],  //组织下拉列表
         normalizer(node){
           return {
             id: node.value,
             label: node.label,
             children: node.children,
           }
         },
         checkBoxRoles:[],  //角色复选框
         sexArray:[{
           value:0,
           label:'男'
         },{
             value:1,
             label:'女'
         }],
         form:{
           username:"",
           sex:0,
           orgInfoDO:{
             id:0
           },
           roleIds:[]
         },
       }
      },

      watch:{
        checkRole(newVal,oldVal){
          //如果是总部管理员，显示组织选择下拉框
          if(newVal == 3){
            this.isShowOrgSelect = true
            this.form.roleIds  = []
            this.form.roleIds.push(newVal)
          }else{
            this.isShowOrgSelect = false
            this.form.roleIds  = []
            this.form.roleIds.push(newVal)
          }
        }
      },

      created(){
        //获取用户信息
        this.configJS.getUserInfo(this);
        //判断当前用户是否是平台管理员
        var  isplatformAdmin = this.configJS.isplatformAdmin();
        this.isplatformAdmin = isplatformAdmin;
        this.initOrgSelect();
        this.initRoleCheckBox();
      },

      methods:{
        usernameBlur(){
          console.log("xxxx");
        },

        initOrgSelect(){
          if (this.isplatformAdmin){
            initplatformOrgSelect(this);
          } else{
            initOrgSelect(this);
          }
        },

        initRoleCheckBox(){
          initRoleCheckBox(this);
        },

        /**
         * 提交
         */
        submit(){
          console.log(this.form);
          var that = this;
          var form = this.form;
          if (!form.username){
            that.$alert("用户名不能为空");
            return;
          }
          if (!form.name){
            that.$alert("姓名不能为空");
            return;
          }
          if (form.mobile){
              if(! /^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(form.mobile) ){
                that.$alert("请输入正确的手机号码");
                return;
              }
          }else{
            that.$alert("手机号码不能为空");
            return;
          }
          if (!form.roleIds){
            that.$alert("请选择角色");
            return;
          }else {
            if (form.roleIds[0] != 2){
                if (!form.orgId){
                  that.$alert("请选择组织");
                  return;
                }
            }
          }
          form.userIdCreate = this.configJS.userInfo.userId;
          var loading =  this.$loading({
            lock: true,
            text: '加载中',
          })
          this.$axios({
            url:'/biz/tob-user/system/userInfo/save',
            headers:{
              "Content-Type":'application/json; charset=UTF-8'
            },
            method:'post',
            data: JSON.stringify(form)
          }).then((res) => {
            console.log(res);
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
          }).finally( res => {
            loading.close();
          } )
        },

        /**
         * 取消，返回列表页
         */
        cancel(){
          this.$router.push({name:'platformAdmin'})
        },

      }
    };

    /**
     * 平台管理员实例化组织下拉
     * @param that
     */
    function initplatformOrgSelect(that) {
      that.$axios({
        // url:'/shiro/system/orgInfo/initOrgDropDown',
        url:'/biz/tob-user/system/orgInfo/initOrgDropDown',
      }).then((res) => {
        console.log("平台管理员",res);
        if(res.data.code==200){
          var initOrgDropDown = res.data.obj.orgList;
          that.selectOrgList  = initOrgDropDown;
        }
      })
    }

    /**
     * 加载组织下拉框
     * 适用于管理员
     */
    // function initOrgSelect(that) {
    //   var orgId = that.configJS.userInfo.orgId
    //   that.$axios({
    //     // url: '/shiro/system/orgInfo/list',
    //     url: '/biz/tob-user/system/orgInfo/list',
    //     params: {
    //       orgId:orgId
    //     }
    //   }).then((res) => {
    //     console.log("list",res);
    //     if(res.data.code == 200){
    //       that.selectOrgList = res.data.obj.orgInfoList;
    //     }
    //   })
    // }
    /**
     * 加载组织下拉框
     * 适用于管理员
     */
    function initOrgSelect(that) {
      that.$axios({
        url: '/biz/tob-user/system/orgInfo/initOrgSelect',
        params:{
          orgId:that.configJS.userInfo.orgId,
          pid:that.configJS.userInfo.orgInfoDO.pid,
          type:2,
        }
      }).then((res) => {
        console.log("initOrgSelect",res);
        if(res.status == 200){
          that.selectOrgList = res.data;
        }
      })
    }


    function initRoleCheckBox(that) {
      that.$axios({
        // url:'/shiro/system/roleInfo/initRoleCheckBox',
        url:'/biz/tob-user/system/roleInfo/initRoleCheckBox',
        params:{
          roleId:that.configJS.userInfo.roles[0].roleId
        }
      }).then((res) => {
        console.log(res);
        if(res.data.code==200){
          var checkBoxRoles = res.data.obj.roles;
          that.checkBoxRoles  = checkBoxRoles;
        }
      })
    }


</script>

<style scoped>

</style>
