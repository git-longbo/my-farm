package cn.jxufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.GroundCrop;
import cn.jxufe.entity.User;
import cn.jxufe.service.GroundCropService;
/**
 * 种植界面的土地作物信息
 * @author 86173
 *
 */

@Controller
@RequestMapping("ground")
public class GroundCropController {
	
	@Autowired
	GroundCropService groundCropService;
	@Autowired
	UserDAO userDAO;
    @RequestMapping("farm")
	public String list(Model model,HttpSession session) {
    	User user = (User)session.getAttribute("user");
    	//当没有登录时，跳转到登录界面
    	if(user == null) {
    		return "user/login";
    	}
		return "cropsGrow/ground";
	}
    
    @RequestMapping("data")
    @ResponseBody
    public List<GroundCrop> findAll(HttpSession session){
    	User user = (User)session.getAttribute("user");
    	return groundCropService.finAll(user.getId());
    }
    
    //播种
    @RequestMapping("plant")
    @ResponseBody
    public Message actionPlant(int groundId, int sId, HttpSession session) {
    	return groundCropService.plant(groundId,sId);
    }

    //除虫
    @RequestMapping("killWorm")
    @ResponseBody
	public Message actionKillWorm(int groundId, HttpSession session) {
		User user = (User) session.getAttribute("user");
    	GroundCrop ground = groundCropService.findByUIdAndGroundId(user.getId(),groundId);
		return groundCropService.killWorm(ground);
	}

	//收货
	@RequestMapping("receive")
    @ResponseBody
	public Message actionHarvest(int groundId, HttpSession session) {
		User user = (User) session.getAttribute("user");
    	GroundCrop ground = groundCropService.findByUIdAndGroundId(user.getId(),groundId);
		return groundCropService.receive(ground);
	}

	
	//除草
	@RequestMapping("cleanGlass")
	@ResponseBody
	public Message actionCleanGlass(int groundId, HttpSession session) {
		User user = (User) session.getAttribute("user");
    	GroundCrop ground = groundCropService.findByUIdAndGroundId(user.getId(),groundId);
		return groundCropService.cleanLand(ground);
	}
	@RequestMapping("check")
	@ResponseBody
	public GroundCrop canReceive(int groundId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		GroundCrop ground = groundCropService.findByUIdAndGroundId(user.getId(), groundId);
		return ground;
	}
	

}
