package com.xiaoxiongwang.service;

import com.xiaoxiongwang.util.MessageUtil;

public interface CallLogService {

	/**
	 * 获取通话记录
	 * @param userId
	 * @param page
	 * @return
	 */
	MessageUtil getCallLog(int userId,int page);
	
}
