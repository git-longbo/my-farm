package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
/**
 * �û�ģ���¼
 * @author 86173
 *
 */
@Controller
@RequestMapping(value = "user")
public class LoginController {
	@Autowired
	private FarmService farmService;
	@RequestMapping(value = "login")
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="setCurUser",produces=MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public Message setCurUser(HttpSession session, @RequestBody User user){ 
		return farmService.setCurUser(session, user);
	}
}
