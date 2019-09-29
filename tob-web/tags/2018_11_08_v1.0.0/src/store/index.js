import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navIndex: 0
  },
  mutations: {
    changeIndex(state,index){
      state.navIndex = index;
    }
  },
  getters:{
    getNavIndex(state){
      return state.navIndex;
    }
  }
})
