import Vue from 'vue'
import Router from 'vue-router'
//导入其他js文件的声明
import passage from './passage.js'

Vue.use(Router)

//passage[0].children=passage[0].children;//.concat(addDev,manageDev,repairDev,scrapDev,repaiInfor);

var routerInfo = [].concat(passage)

export default new Router({
  routes: [].concat(routerInfo)
})

