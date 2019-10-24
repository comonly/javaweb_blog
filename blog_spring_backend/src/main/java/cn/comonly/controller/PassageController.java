package cn.comonly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.comonly.pojo.Passage;
import cn.comonly.service.PassageService;

@Controller
@RequestMapping(value = "/passage")
public class PassageController {
	
	@Autowired 
	private PassageService passageService;
	
	//获取文章列表
	@RequestMapping(value = "/passIndex", method = RequestMethod.GET)
    public @ResponseBody List<Passage> passIndex() {
    	return passageService.passIndex();
    }
	
	//获取单个文章
	@RequestMapping(value = "/passDetail", method = RequestMethod.GET)
    public @ResponseBody Passage passDetail(int id) {
    	return passageService.passDetail(id);
    }

	//提交单个文章修改
	@RequestMapping(value = "/passChangeSubmit", method = RequestMethod.POST)
    public @ResponseBody int passChangeSubmit(@RequestBody Passage passage) {
    	return passageService.passChangeSubmit(passage);
    }

	//添加单个文章
	@RequestMapping(value = "/passAddSubmit", method = RequestMethod.POST)
    public @ResponseBody int passAddSubmit(@RequestBody Passage passage) {
    	return passageService.passAddSubmit(passage);
    }
	
	//添加单个文章
	@RequestMapping(value = "/passDelete", method = RequestMethod.GET)
    public @ResponseBody int passAddSubmit(int id) {
    	return passageService.passDelete(id);
    }
	
	//@RequestMapping(value = "/passChange", method = RequestMethod.GET)
	//直接获取passDetail即可

	//@RequestMapping(value = "/passAdd", method = RequestMethod.GET)
	//获取分类列表即可

}
