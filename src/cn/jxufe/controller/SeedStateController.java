package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedState;
import cn.jxufe.service.SeedStateService;
/**
 * 种子各个阶段管理
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "seedState")
public class SeedStateController {
	@Autowired
	private SeedStateService seedStateService;
	  @RequestMapping(value = "getSeedStates")
	   @ResponseBody
	   public EasyUIData<?> getSeedStates(EasyUIDataPageRequest easyUIDataPageRequest,@RequestParam int seedId,Model model){
	    	
	    	return seedStateService.listPage(seedId,easyUIDataPageRequest);
	  }
	  @RequestMapping(value = "save")
	   @ResponseBody
	   public Message save(SeedState seedState) {
		return  seedStateService.save(seedState);
	  }
	  @RequestMapping(value = "delete")
	   @ResponseBody
	   public Message delete(SeedState seedState) {
		return  seedStateService.delete(seedState);
	  }
	  @RequestMapping(value = "findBySIdAndStaus")
	  @ResponseBody
	  public SeedState findBySIdAndStaus(@RequestParam int seedId,@RequestParam int sStatus){
		  return seedStateService.findBySIdAndStaus(seedId,sStatus);
	  }
}
