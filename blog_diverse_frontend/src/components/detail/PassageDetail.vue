<template>
  <div>   
    <el-button type="primary" @click="deletePassage()">删除</el-button>
    &nbsp;
    <el-link type="primary" :href="'/#/detail/PassageModify?id=' + msg.pnumber">
        <el-button type="warning">
            修改<i class="el-icon-view el-icon--right"></i>
        </el-button>
    </el-link>
    
    <p>{{ msg.pnumber }}</p>
    <p>{{ msg.ptitle }}</p>
    <p>{{ msg.ptime }}</p>
    <p>{{ msg.plabel }}</p>
    <p v-html="msg.pcount"></p>
    <p>{{ msg.pcoming }}</p>
    <p>{{ msg.pcomingUrl }}</p>
    <p>{{ msg.pimage }}</p>
    <p>{{ msg.pkind }}</p>
    <p>{{ msg.pbelong }}</p>
    <p>{{ msg.pdescribe }}</p>
  </div>
</template>

<script>
export default {
    
  name: 'PassageDetail',
  mounted(){
    this.getData(); //获取数据前先取数据
  },
  data () {
    return {
      msg: {}
    }
  },
  methods: {
    deletePassage(){

        this.$confirm('确认删除？')
            .then(_ => {
                this.sendDelete();
                this.$router.replace({path: '/'});
            })
            .catch(_ => {});

    },
    sendDelete(){
        var _this=this;
        //需要处理异步请求的问题
        this.axios.get('/passage/passDelete?id=' + this.msg.pnumber)
            .then(function (response) {
                //将response获得的数据进行处理
                //将获取到的数据以数组形式传递出去
                var dataList=response.data;
                _this.msg=dataList;
                _this.$message({
                    message: '删除成功',
                    type: 'success'
                    });

            })
            .catch(function (error) {
                console.log(error);
                alert("网络连接错误,无法获取服务器数据，请检查后刷新页面");
            })
    },
    getData(){
        var _this=this;
        //需要处理异步请求的问题
        this.axios.get('/passage/passDetail?id=' + this.$route.query.id)
            .then(function (response) {
                //将response获得的数据进行处理
                //将获取到的数据以数组形式传递出去
                var dataList=response.data;
                _this.msg=dataList;
                _this.$message({
                    message: '查询成功',
                    type: 'success'
                    });

            })
            .catch(function (error) {
                console.log(error);
                alert("网络连接错误,无法获取服务器数据，请检查后刷新页面");
            })
    }
        
  }
}
</script>