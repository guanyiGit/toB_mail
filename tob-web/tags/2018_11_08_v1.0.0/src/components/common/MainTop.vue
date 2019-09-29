<template>
  <!--头部-->
  <div class="header fixed z1000 ww100 left0 top0 bgw h115">
    <div class=" w1300 c-b h110">
      <div class="fl mgr50">
        <router-link :to="{name:'index'}" class="db h54 mgt28"><img src="/static/images/1.png" alt="网站标识"></router-link>
      </div>
      <div class="fl">
        <ul class="ul01 h110">
          <li class="fl" v-for="(item,index) in memuList" @click.prevent="$store.commit('changeIndex',index)"
              :class="{active:$store.getters.getNavIndex==index}">
            <router-link :to="{name:item.navName}" class="db cor3c3c3c h110 lh110 pdl35 pdr35">{{item.name}}
            </router-link>
          </li>
        </ul>
      </div>
      <div class="fr pdt40 ovh">
        <!--登录前-->
        <router-link :to="{name:'login'}" class="db h30 lh30 hov8 fs16 cor030303" v-if="!userInfo || !userInfo.userId">
          <img class="mgr5 v-b" src="/static/images/2-1.png" alt="登录按钮"> 登录
        </router-link>
        <!--登陆后-->
        <div v-else>
          <a href="###" class="fl mgr15"><img src="/static/images/2-2.png" alt="我的消息" @click.prevent="toMyMsg"></a>

          <router-link :to="{name:toIndex}" class="fl mgr15 cor3c3c3c" alt="用户名"><img class="mgr5"
                                                                                      src="/static/images/2-3.png">
            {{userInfo.name}}
          </router-link>
          <router-link to="#" class="fl" alt="退出"><img src="/static/images/2-4.png" @click="logout"></router-link>
        </div>
      </div>
    </div>
  </div>

  <!--头部END-->
</template>

<script>
  import configJS from "../../common/config"

  export default {
    name: "MainTop",
    data() {
      return {
        memuList: configJS.mainMemuList,//头部菜单
        userInfo: configJS.userInfo//用户信息
      }
    },
    methods: {
      //退出登陆MainLeft
      logout() {
        this.$alert("您确认要退出登录吗？", "退出登录",
          {showCancelButton: true})
          .then(res => {
            this.$axios({url: "/biz/logout", isloading: true})
              .then(res => {
                if (res.data.status == 200) {
                  configJS.userInfo = {};
                  configJS.resource = [];
                  // sessionStorage.removeItem("resource_info_token")
                  // sessionStorage.removeItem("user_info_token")
                  this.$router.push({name: 'login'})
                }
              })
          })
      },
      toMyMsg() {
        let roleId = configJS.userInfo.tRoles[0].roleId;
        let perfix = '';
        if (roleId == 1) {//犬只办
          perfix = 'dogManager_';
        } else if (roleId == 2) {//收容所
          perfix = 'hospice_';
        } else if (roleId == 4) {//宠物医院
          perfix = 'hospital_';
        } else if (roleId == 5) {//运营方
          perfix = 'operator_';
        }
        this.$router.push({name: perfix + "message"});
      }
    },
    computed: {
      //根据用户跳到其首页
      toIndex() {
        let link = null;//跳转的页面
        configJS.resource.memu.map(item => {
          if (item && item.seq == 1 && item.level == 2) {
            link = item.link;
          }
        })
        return link;
      }
    }
  }
</script>

<style scoped>
  .w1300 {
    width: 1300px;
  }
</style>
