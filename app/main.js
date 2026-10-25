import App from './App'

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'

import uniList from '@/components/uni-list/uni-list.vue';
import uniListItem from '@/components/uni-list-item/uni-list-item.vue';
import uniAd from '@/components/uni-list-ad/uni-list-ad.vue';
import uniChat from '@/components/uni-list-chat/uni-list-chat.vue';
// import store from './store/index.js'
// Vue.prototype.$store = store

import uView from 'uview-ui'
Vue.use(uView)
uni.$u.config.unit = 'rpx'

import store from '@/store/index.js'
Vue.use(uView);
Vue.prototype.$store = store

const app = new Vue({
    ...App,
	store
})
app.$mount()


try {
  function isPromise(obj) {
    return (
      !!obj &&
      (typeof obj === "object" || typeof obj === "function") &&
      typeof obj.then === "function"
    );
  }

  // 统一 vue2 API Promise 化返回格式与 vue3 保持一致
  uni.addInterceptor({
    returnValue(res) {
      if (!isPromise(res)) {
        return res;
      }
      return new Promise((resolve, reject) => {
        res.then((res) => {
          if (res[0]) {
            reject(res[0]);
          } else {
            resolve(res[1]);
          }
        });
      });
    },
  });
} catch (error) { }

// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif