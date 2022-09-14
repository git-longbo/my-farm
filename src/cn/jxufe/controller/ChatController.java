package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Message;
import cn.jxufe.bean.Talk;
import cn.jxufe.service.ChatService;

@Controller
@RequestMapping("chat")
public class ChatController {

	@Autowired
	ChatService chatService;
	
	@RequestMapping(value="talk",produces=MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Message talk(HttpSession session, @RequestBody Talk talk){	
		return chatService.talk(session,talk);
	}
}
