package cn.jxufe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.Ground;
import cn.jxufe.service.GroundService;
/**
 * 土地类型
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "ground")
public class GroundController {
	@Autowired
	private GroundService groundService;
	/**
	 * 获取所有的土地类型
	 * @return
	 */
	  @RequestMapping(value = "getGroundType")
	   @ResponseBody
	   public List<Ground> getGroundType(){
		  return groundService.getAll();
	  }
	  @RequestMapping(value = "getGroundTypeByCode")
	   @ResponseBody
	   public String getGroundTypeByCode(@RequestParam int code) {
			
			Ground ground = groundService.getGroundTypeByCode(code);
			String groundName = ground.getGroundName();
			  return groundName;
		  }
	  @RequestMapping(value = "openseedBag")
	  public String openBag() {
		  return "user/mySeed";
	  }
}
