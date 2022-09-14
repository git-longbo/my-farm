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
import cn.jxufe.entity.Seed;
import cn.jxufe.service.SeedService;

/**
 * 种子管理
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "seed")
public class SeedController {
	@Autowired
	private SeedService seedService;
	@RequestMapping(value = "turnToStage")
	public String turnToStage(@RequestParam int seedId,Model model) {
		model.addAttribute("seedId",seedId);
		return "seed/growStage";
	}
	 
    @RequestMapping(value = "grid")
    public String grid() {
    	return "seed/grid";
    }
    @RequestMapping(value = "getSeeds")
    @ResponseBody
    public EasyUIData<?> getSeeds(EasyUIDataPageRequest easyUIDataPageRequest,@RequestParam(defaultValue="") String seedName,Model model){
    	
    	return seedService.listPage(seedName,easyUIDataPageRequest);
    }
    @RequestMapping(value = "save")
    @ResponseBody
    public Message save(Seed seed) {
    	return seedService.save(seed);
    }
    @RequestMapping(value = "delete")
    @ResponseBody
    public Message delete(Seed seed) {
    	return seedService.delete(seed);
    }
    
}

