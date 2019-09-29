import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navIndex: 0,
    memuIndex: -1,
    userInfo: {},
    resource: {
      memu: {},
      other: {}
    },
  },
  mutations: {
    changeIndex(state, index) {
      state.navIndex = index;
    },
    changMemuIndex(state, index) {
      state.memuIndex = index;
    },
    changShowWindow(state, isShow) {
      state.isShowWindow = isShow;
    },
    changUserInfo(state, userInfo) {
      state.userInfo = userInfo;
    },
    changResource(state, resource) {
      state.resource = resource;
    }
  },
  getters: {
    getNavIndex(state) {
      return state.navIndex;
    },
    getMemuIndex(state) {
      return state.memuIndex;
    },
    getUserInfo(state) {
      return state.userInfo;
    },
    getResource(state) {
      return state.resource;
    }
  }
})
