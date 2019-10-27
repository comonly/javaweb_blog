// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false

//elm ul组件
Vue.use(ElementUI);

//全局引入axios
//需要npm两个包axios和vue-axios
Vue.use(VueAxios,axios);
//axios相关配置 //更多配置待处理
axios.defaults.baseURL = 'http://localhost:8080/blog_spring_backend/';


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
