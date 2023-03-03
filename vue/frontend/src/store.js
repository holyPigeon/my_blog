import { createStore } from 'vuex'

const store = createStore({
  state() {
    return {
      // 메인페이지 현재 페이지
      count: 0,
    }
  },
  mutations: {
    increment(state) {
      state.count++;
    },
  }
})

export default store;