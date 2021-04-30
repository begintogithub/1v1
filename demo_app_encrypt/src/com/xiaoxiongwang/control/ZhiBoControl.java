package com.xiaoxiongwang.control;

import com.xiaoxiongwang.service.ZhiBoService;
import com.xiaoxiongwang.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录相关接口
 * 
 * @author Administrator
 * 
 */

@Controller
@RequestMapping("app")
public class ZhiBoControl {

	//protected static final String APP_KEY =SystemConfig.getValue("app_key");

	@Autowired
	private ZhiBoService zhiBoService;
	/**
	 * 获取用户签名
	 *
	 * @param phone
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "ZhiBoSig" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil ZhiBoSig(HttpServletRequest req) {

		// 解密参数
		JSONObject param = RequestJson.get(req);

		// 验证传递的参数
//		if (!BaseUtil.params(param.getOrDefault("phone", ""))) {
//			// 返回数据
//			return new MessageUtil(-500, "服务器拒绝执行请求!");
//		}

		return this.zhiBoService.ZhiBoSig(param);
	}

	/**
	 * 获取用户签名
	 *
	 * @param phone
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "ZhiBoCosSig" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil ZhiBoCosSig(HttpServletRequest req) {

		// 解密参数
		JSONObject param = RequestJson.get(req);

		// 验证传递的参数
//		if (!BaseUtil.params(param.getOrDefault("phone", ""))) {
//			// 返回数据
//			return new MessageUtil(-500, "服务器拒绝执行请求!");
//		}

		return this.zhiBoService.ZhiBoCosSig(param);
	}


	/**
	 * 获取用户签名
	 *
	 * @param phone
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "ZhiBoUploadRoom" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil ZhiBoUploadRoom(HttpServletRequest req) {

		//

		JSONObject param = RequestJson.get(req);

		// 验证传递的参数
//		if (!BaseUtil.params(param.getOrDefault("phone", ""))) {
//			// 返回数据
//			return new MessageUtil(-500, "服务器拒绝执行请求!");
//		}

		return this.zhiBoService.ZhiBoUploadRoom(param);
	}



}
