package com.xiaoxiaowang.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoxiaowang.service.UserMapService;
import com.xiaoxiaowang.util.MessageUtil;
import com.xiaoxiaowang.util.PrintUtil;

/**
 * 用户分布
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class UserMapControl {

	@Autowired
	private UserMapService userMapService;
	
	@RequestMapping("getOnLineUserMapList")
	@ResponseBody
	public void getOnLineUserMapList(HttpServletResponse response) {
		
		Object onLineUserMapList = this.userMapService.getOnLineUserMapList();
		
		MessageUtil mu = new MessageUtil();
		mu.setM_istatus(1);
		mu.setM_object(onLineUserMapList);
		
		PrintUtil.printWri(mu, response);
	}
	
}
