package com.xiaoxiaowang.service;

import com.xiaoxiaowang.util.MessageUtil;

import net.sf.json.JSONObject;

public interface CodePaySetUpService {
	
	/**
	 * 码支付设置
	 * @param page
	 * @return
	 */
	JSONObject getCodePaySetUpList(int page);
	
	/**
	 * 新增或者修改
	 * @param t_id
	 * @param appId
	 * @param t_mchid
	 * @param t_mchid_key
	 * @param t_certificate_url
	 * @return
	 */
	MessageUtil addOrUpCodePaySetUp(Integer t_id,String code_id,String code_key,String notify_url,String token);

	/**
	 * 删除数据
	 * @param t_id
	 * @return
	 */
	MessageUtil delCodePaySetUp(int t_id);
	
}
