var express = require('express');
var request= require('request');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
/*正式数据*/
  request('http://localhost:8080/blog_spring_backend/passage/passIndex',function(error,response,body){
    /*判断请求是否成功*/
    if (!error && response.statusCode == 200) {
      /*把字符串转换为json*/
      var data=JSON.parse('{"list":'+body+'}');
      /*渲染模板*/
      res.render('index', data);
    }
  });
});

//查看文章详情
router.get('/detail', function(req, res, next) {
  /*正式数据*/
    request('http://localhost:8080/blog_spring_backend/passage/passDetail?id='+req.param('id'),function(error,response,body){
      /*判断请求是否成功*/
      if (!error && response.statusCode == 200) {
        /*把字符串转换为json*/
        var data=JSON.parse(body);
        /*渲染模板*/
        res.render('detail', data);
      }
    });
  });

module.exports = router;
