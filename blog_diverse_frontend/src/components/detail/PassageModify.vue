<template>
  <div>
    <el-button type="primary" @click="submitPassage()">提交<i class="el-icon-view el-icon--right"></i></el-button>
    
<div style="margin: 20px;"></div>
<el-form :label-position="labelPosition" label-width="80px" :model="msg">
  <el-form-item label="编号">
    <el-input v-model="msg.pnumber" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="标题">
    <el-input v-model="msg.ptitle"></el-input>
  </el-form-item>
  <el-form-item label="时间">
    <el-input v-model="msg.ptime" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="标签">
    <el-input v-model="msg.plabel"></el-input>
  </el-form-item>
  <el-form-item label="内容">
    <el-input v-model="msg.pcount"></el-input>
  </el-form-item>
  <el-form-item label="来源">
    <el-input v-model="msg.pcoming"></el-input>
  </el-form-item>
  <el-form-item label="来源地址">
    <el-input v-model="msg.pcomingUrl"></el-input>
  </el-form-item>
  <el-form-item label="描述">
    <el-input v-model="msg.pdescribe"></el-input>
  </el-form-item>
</el-form>

  </div>
</template>

<script>
export default {
    
  name: 'PassageDetail',
  mounted(){
    if(!(this.$route.query.id > 0))return;
    this.getData(); //获取数据前先取数据
  },
  data () {
    return {
      labelPosition: 'right',
      msg: {}
    }
  },
  methods: {
    modifyPassage(){
        var _this=this;
        //需要处理异步请求的问题
        this.axios({
                method:'post',
                url:'/passage/passChangeSubmit',
                data:_this.msg,
            })
            .then(function (response) {
                //将response获得的数据进行处理
                //将获取到的数据以数组形式传递出去
                var dataList=response.data;
                _this.msg=dataList;
                _this.$message({
                    message: '修改成功',
                    type: 'success'
                    });

            })
            .catch(function (error) {
                console.log(error);
                alert("网络连接错误,无法获取服务器数据，请检查后刷新页面");
            });
    },
    addPassage(){
        var _this=this;
        //需要处理异步请求的问题
        this.axios({
                method:'post',
                url:'/passage/passAddSubmit',
                data:_this.msg,
            })
            .then(function (response) {
                //将response获得的数据进行处理
                //将获取到的数据以数组形式传递出去
                var dataList=response.data;
                _this.msg=dataList;
                _this.$message({
                    message: '添加成功',
                    type: 'success'
                    });

            })
            .catch(function (error) {
                console.log(error);
                alert("网络连接错误,无法获取服务器数据，请检查后刷新页面");
            })
    },
    submitPassage(){
        if(this.msg.pnumber)
        {
            this.modifyPassage();
            this.getData();
        }
        else{
            this.addPassage();
            this.$router.replace({path: '/'});
        }
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