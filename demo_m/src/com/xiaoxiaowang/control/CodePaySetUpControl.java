package com.xiaoxiaowang.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoxiaowang.service.CodePaySetUpService;
import com.xiaoxiaowang.util.MessageUtil;
import com.xiaoxiaowang.util.PrintUtil;

/**
 * 码支付设置control
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class CodePaySetUpControl {

	@Autowired
	private CodePaySetUpService codePaySetUpService;
	/**
	 * 获取码支付设置列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getCodePaySetUpList")
	@ResponseBody
	public void getCodePaySetUpList(int page, HttpServletResponse response){
		
		PrintUtil.printWri(this.codePaySetUpService.getCodePaySetUpList(page), response);
		
	}
	
	/**
	 * 修改或者删除
	 */
	@RequestMapping("addOrUpCodePaySetUp")
	@ResponseBody
	public void addOrUpCodePaySetUp(Integer t_id,String code_id,String code_key,String notify_url,String token,
			HttpServletResponse response){
		
		MessageUtil mu = this.codePaySetUpService.addOrUpCodePaySetUp(t_id, code_id, code_key, notify_url, token);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delCodePaySetUp")
	@ResponseBody
	public void delCodePaySetUp(int t_id ,HttpServletResponse response){
		
		MessageUtil mu = this.codePaySetUpService.delCodePaySetUp(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
}
