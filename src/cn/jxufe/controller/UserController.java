package cn.jxufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;
/**
 * 用户的各项操作
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="grid")
	public String grid() {
		return "user/userManage";
	}
	 @RequestMapping(value = "getUsers")
	    @ResponseBody
	    public EasyUIData<?> getusers(EasyUIDataPageRequest easyUIDataPageRequest,@RequestParam(defaultValue="") String username,Model model){
	    	
	    	return userService.listPage(username,easyUIDataPageRequest);
	    }
	    @RequestMapping(value = "save")
	    @ResponseBody
	    public Message save(User user,Model model) {
	    	Message message = userService.save(user);
	    	model.addAttribute("message",message);
	    	return message;
	    }
	    @RequestMapping(value = "delete")
	    @ResponseBody
	    public Message delete(User user) {
	    	return userService.delete(user);
	    }
	    @RequestMapping(value = "getUserList")
	    @ResponseBody
	    public List<User> getUserList(){
	    	return userService.findAll();
	    }
	    //购买种子
	    @RequestMapping(value = "buySeed")
	    @ResponseBody
	    public Message buySeed(int seedId,HttpSession session) {
	    	
	    	return userService.buySeed(seedId,session);
	    	
	    }
	    @RequestMapping(value = "mySeed")
	    public String mySeed() {
	    	return "user/mySeed";
	    }
	    @RequestMapping(value = "seedList")
	    @ResponseBody
	    public Page<SeedBag> seedList(HttpSession session,@RequestParam(defaultValue="0") int pageNum){ 	
	    	return userService.findMySeeds(session,pageNum);
	    }
	   
}
