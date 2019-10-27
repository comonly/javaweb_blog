<template>
  <div class="hello">
    <el-link type="primary" href="/#/detail/PassageModify">
        <el-button  icon="el-icon-edit">
            添加新文章
        </el-button>
    </el-link>

    <MainLeft></MainLeft>

  </div>
</template>

<script>
import MainLeft from '@/components/index/MainLeft'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  components: {
    MainLeft
  },
  methods: {
    toDetail () {
      this.$router.push({name: 'PassageDetail', params: { val: 'row' }})
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
