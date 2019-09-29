// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import 'babel-polyfill'

import MainTop from './components/common/MainTop'
import MainBottom from './components/common/MainBottom'
import LinkExchange from './components/common/LinkExchange'
import pageVue from './components/common/pageVue'
import imgUpload from './components/common/imgUpload'
import imgUpl from './components/common/imgUpl'
import page from './components/common/page'

import configJS from './common/config'
import axios from 'axios';

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import iView from 'iview';
import 'iview/dist/styles/iview.css';

import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

Vue.use(iView);

Vue.config.productionTip = false

Vue.use(ElementUI);

//axios
axios.defaults.withCredentials = true//表示跨域请求时是否需要使用凭证
axios.defaults.timeout = 5000 * 100;// 在超时前，所有请求都会等待 5 秒
// axios.defaults.timeout = 5000 * 100;// 在超时前，所有请求都会等待 5 秒
// axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';// 配置请求头
axios.defaults.headers['Access-Control-Allow-Origin'] = configJS.basicUrl;// 配置请求头
Vue.prototype.configJS = configJS;
Vue.prototype.$axios = axios;// 将axios配置成vue的原型

//公共组件
Vue.component("MainBottom", MainBottom)
Vue.component("MainTop", MainTop)
Vue.component("LinkExchange", LinkExchange)
Vue.component("pageVue", pageVue)
Vue.component("imgUpload", imgUpload)
Vue.component("imgUpl", imgUpl)
Vue.component("page", page)
Vue.component("Treeselect",Treeselect)

//页面跳转定位到最上
router.beforeEach((to, from, next) => {
  // chrome
  document.body.scrollTop = 0
  // firefox
  document.documentElement.scrollTop = 0
  // safari
  window.pageYOffset = 0
  next()
})
// 添加请求拦截器 判断用户是否登录
axios.interceptors.request.use(function (config) {
  let authorization = configJS.Authorization
  if(authorization){
    config.headers.Authorization = authorization;
  }else{
    authorization = localStorage.getItem("authorization");
  }

  if(authorization){
    configJS.Authorization = authorization;
    config.headers.Authorization = authorization;
  }
  if (config.isloading) {
    let target = ".wrap"
    if ("string" == typeof config.isloading) {
      target = config.isloading;
    }
    config.isloading = ElementUI.Loading.service({
      lock: true,
      text: '正在处理，请稍后...',
      background: 'rgba(0, 0, 0, 0.3)',
      target
    });
  }
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

// axios.interceptors.response.use(
//   response => {
//
//   },
//   error => {
//     console.log(error);
//   }
// )
// 添加响应拦截器
axios.interceptors.response.use(function (res) {
  // console.log("interceptors",res);
  if (res.config.isloading) {
    res.config.isloading.close();
  }
  if (res.headers) {
    //没有登录跳过验证
    // 用户session过期，需要去登陆
    if (res.headers.statuscode == '401' || res.headers.statusCode == '401') {
      if (configJS.userInfo && configJS.userInfo.userId) {
        configJS.userInfo = "";
        configJS.authorization = "";
        //登录失效，删除本地用户信息和authorization
        localStorage.removeItem("userInfo");
        localStorage.removeItem("authorization");
        ElementUI.MessageBox.confirm("登录信息过期了,是否去登录？", "登录超时提示", {
          confirmButtonText: '去登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(_ => {
          //跳转到登录页
          router.replace({name:"login"});
          // this.$router.push({name:"login"})
          // router.replace({name: 'login'})
        }).catch(() => {
          // router.replace({name: 'index'})
           router.replace({name:"login"})
        }).finally(_ => {
        })
        return;
      }
    }
  }
  return res;
}, function (error) {
  console.log(error);
  if (error.config && error.config.isloading) {
    error.config.isloading.close();
  }
  // if (error.response) {
  //   switch (error.response.status) {
  //     case 401:
  //       // 返回 401 清除token信息并跳转到登录页面
  //       router.replace({
  //         path: 'login',
  //       })
  //   }
  // }
  ElementUI.Message("服务器繁忙！")
  // 对响应错误做点什么
  return Promise.reject(error);
});


//global filter 时间日期格式化
Vue.filter("formatDate", function (date, fmt = "yyyy-MM-dd HH:mm:ss") {
  date = date == undefined ? new Date() : date;
  date = typeof date == 'number' ? new Date(date) : date;
  fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
  var obj =
    {
      'y': date.getFullYear(), // 年份，注意必须用getFullYear
      'M': date.getMonth() + 1, // 月份，注意是从0-11
      'd': date.getDate(), // 日期
      'q': Math.floor((date.getMonth() + 3) / 3), // 季度
      'w': date.getDay(), // 星期，注意是0-6
      'H': date.getHours(), // 24小时制
      'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
      'm': date.getMinutes(), // 分钟
      's': date.getSeconds(), // 秒
      'S': date.getMilliseconds() // 毫秒
    };
  var week = ['天', '一', '二', '三', '四', '五', '六'];
  for (var i in obj) {
    fmt = fmt.replace(new RegExp(i + '+', 'g'), function (m) {
      var val = obj[i] + '';
      if (i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
      for (var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
      return m.length == 1 ? val : val.substring(val.length - m.length);
    });
  }
  return fmt;
})

Vue.filter("numFilter",(val,n=2) => Number(val).toFixed(n))



/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: {App},
  template: '<App/>',
  router,
  store
})

