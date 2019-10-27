<template>
  <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="pnumber"
      label="编号"
      width="180">
      <template slot-scope="scope">
        <el-link type="primary" :href="'/#/detail/PassageDetail?id=' + scope.row.pnumber">查看<i class="el-icon-view el-icon--right"></i> </el-link>
      </template>
    </el-table-column>
    <el-table-column
      prop="ptitle"
      label="标题">
    </el-table-column>
    <el-table-column
      prop="ptime"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="plabel"
      label="关键字"
      width="180">
    </el-table-column>
    <el-table-column
      prop="pcoming"
      label="来源">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'MainLeft',
  mounted(){
    this.getData(); //获取数据前先取数据
  },
  data () {
    return {
      tableData: []
    }
  },
  methods: {
    getData(){
        var _this=this;
        //需要处理异步请求的问题
        this.axios.get('passage/passIndex')
            .then(function (response) {
                //将response获得的数据进行处理
                //将获取到的数据以数组形式传递出去
                var dataList=response.data;
                _this.tableData=dataList;
                _this.$message({
                    message: '查询成功',
                    type: 'success'
                    })
            })
            .catch(function (error) {
                console.log(error);
                alert("网络连接错误,无法获取服务器数据，请检查后刷新页面");
            })
    }  
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  color: #42b983;
}
</style>
