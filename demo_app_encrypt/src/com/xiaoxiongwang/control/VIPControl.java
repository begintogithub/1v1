package com.xiaoxiongwang.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoxiongwang.service.VIPService;
import com.xiaoxiongwang.util.BaseUtil;
import com.xiaoxiongwang.util.MessageUtil;
import com.xiaoxiongwang.util.RSACoderUtil;

import net.sf.json.JSONObject;

/**
 * VIP相关控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("app")
public class VIPControl {

	@Autowired
	private VIPService vIPService;

	/**
	 * 获取VIP套餐列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getVIPSetMealList", method = RequestMethod.POST)
	@ResponseBody
	public MessageUtil getVIPSetMealList(HttpServletRequest req) {
		// 解密参数
		JSONObject param = RSACoderUtil.privateDecrypt(req);

		if (!BaseUtil.params(param.getOrDefault("userId", 0))) {
			// 返回数据
			return new MessageUtil(-500, "服务器拒绝执行请求!");
		}
		return this.vIPService.getVIPSetMealList();
	}
}
