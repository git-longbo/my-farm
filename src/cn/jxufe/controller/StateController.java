package cn.jxufe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.State;
import cn.jxufe.service.StateService;
/**
 * 植物的生长状态
 */
@Controller
@RequestMapping(value = "state")
public class StateController {
	@Autowired
	private StateService stateService;
	 @RequestMapping(value = "getState")
	   @ResponseBody
	   public List<State> getState(){
		 return stateService.getAll();
	 }
	 @RequestMapping(value = "getStateByCode")
	   @ResponseBody
	   public String getStateByCode(@RequestParam int code) {
		 String state = stateService.getStateByCode(code).getState();
		 return state;
	 }
}
