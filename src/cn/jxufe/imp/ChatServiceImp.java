package cn.jxufe.imp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.bean.Message;
import cn.jxufe.bean.Talk;
import cn.jxufe.entity.User;
import cn.jxufe.service.ChatService;
import cn.jxufe.websocket.FarmActionHandler;
import net.sf.json.JSONObject;

@Service
public class ChatServiceImp implements ChatService{

	@Autowired
	FarmActionHandler farmActionHandler;
	
	@Override
	public Message talk(HttpSession session,Talk talk) {
		Message result = new Message();
		User chatUser = (User)session.getAttribute("user");
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userName", chatUser.getNicheng());
			jsonObject.put("uId", chatUser.getId());
			jsonObject.put("headPhoto", chatUser.getHeadImg());
			jsonObject.put("message",talk.getMessage());
			farmActionHandler.sendMessageToUser(talk.getTo(),new TextMessage(jsonObject.toString()));
		}catch(Exception e) {
			result.setCode(-1);
			result.setMsg("消息发送失败");
			return result;
		}
		result.setCode(0);
	    result.setMsg("消息发送成功");
		return result;
	}
}
