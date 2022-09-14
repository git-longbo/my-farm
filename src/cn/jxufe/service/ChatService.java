package cn.jxufe.service;

import javax.servlet.http.HttpSession;

import cn.jxufe.bean.Message;
import cn.jxufe.bean.Talk;

public interface ChatService {

	public Message talk(HttpSession session,Talk talk);
}
