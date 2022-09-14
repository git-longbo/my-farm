package cn.jxufe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.SeedType;
import cn.jxufe.service.SeedTypeService;
/**
 * 种子类型管理
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "seedType")
public class SeedTypeController {
	@Autowired
	private SeedTypeService seedTypeService;
	  @RequestMapping(value = "getSeedType")
	   @ResponseBody
	   public List<SeedType> getSeedType(){
		  return seedTypeService.getAll();
	  }
	  @RequestMapping(value = "getSeedTypeByCode")
	   @ResponseBody
	   public String getSeedTypeByCode(@RequestParam int code) {
		 String type = seedTypeService.getSeedTypeByCode(code).getTypeName();
		  return type ;
	  }
}
