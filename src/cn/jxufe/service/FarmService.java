package cn.jxufe.service;

import javax.servlet.http.HttpSession;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;

public interface FarmService {
	Message setCurUser(HttpSession session,User user);
}
