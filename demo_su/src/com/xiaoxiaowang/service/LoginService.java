package com.xiaoxiaowang.service;

import javax.servlet.http.HttpServletRequest;

import com.xiaoxiaowang.util.MessageUtil;

public interface LoginService {
	
	/**
	 * 后台登录
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	public MessageUtil login(String userName,String password,HttpServletRequest request);
 
 
	
	 
	
	
 

}
