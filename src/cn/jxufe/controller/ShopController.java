package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * �������ӹ���
 */
@Controller
@RequestMapping(value = "shop")
public class ShopController {
	 @RequestMapping(value = "grid")
	 public String grid() {
		 return "shop/showProducts";
	 }
	 
}
