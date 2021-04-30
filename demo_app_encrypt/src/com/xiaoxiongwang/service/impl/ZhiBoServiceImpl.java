package com.xiaoxiongwang.service.impl;

import com.xiaoxiongwang.service.ZhiBoService;
import com.xiaoxiongwang.util.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import com.tls.tls_sigature.*;

import org.apache.commons.codec.digest.HmacUtils;

/**
 * 登录相关服务实现
 * 
 * @author Administrator
 * 
 */
@Service("ZhiBoService")
public class ZhiBoServiceImpl extends ICommServiceImpl implements ZhiBoService {

	private MessageUtil mu = null;

	/*
	 * 获取登陆设置列表(non-Javadoc)
	 *
	 * @see com.xiaoxiongwang.service.LoginService#getLongSetUpList()
	 */
	@Override
	public MessageUtil getLongSetUpList() {

		try {

			String sql = "SELECT t_app_id,t_app_secret,t_type FROM t_login_setup WHERE t_state = 0 ";

			List<Map<String, Object>> setUpList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(setUpList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取登陆设置列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}

		return mu;
	}

	public String readToString(String fileName) {
		String encoding = "UTF-8";
		try {
			fileName=URLDecoder.decode(fileName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取用户签名
	 * @param json
	 * @return
	 */
	@Override
	public MessageUtil ZhiBoSig(JSONObject json) {
		Long sdkAppID=Long.parseLong(SystemConfig.getValue("IM_SDKAppID"));
		String accountType="0";
		String userID=json.getString("userid");
		String userSig="";
		String Bucket=SystemConfig.getValue("BUCKET");
        String Region=SystemConfig.getValue("REGION");
        String Appid=SystemConfig.getValue("APPID");
        String SecretId=SystemConfig.getValue("SECRETID");

		String fileName = this.getClass().getClassLoader().getResource("private_key.txt").getPath();//获取文件路径
		System.out.println("签名-->"+readToString(fileName));
        //获取签名
		try {
			tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(sdkAppID, userID,readToString(fileName), 24*3600*180);
			userSig=result.urlSig;
			System.out.println("userSig-->"+result.errMessage);
		}catch (Exception e){
          e.printStackTrace();
		}
		JSONObject roomservice_sign=new JSONObject();
		roomservice_sign.put("sdkAppID",sdkAppID);
		roomservice_sign.put("accountType",accountType);
		roomservice_sign.put("userID",userID);
		roomservice_sign.put("userSig",userSig);

		JSONObject cos_info=new JSONObject();
		cos_info.put("Bucket",Bucket);
		cos_info.put("Region",Region);
		cos_info.put("Appid",Appid);
		cos_info.put("SecretId",SecretId);

		JSONObject data=new JSONObject();
		data.put("roomservice_sign",roomservice_sign);
		data.put("cos_info",cos_info);
		mu = new MessageUtil(200, "请求成功");
		mu.setM_object(data);
		return mu;
	}

	/**
	 * 获取cos签名
	 */
	@Override
	public MessageUtil ZhiBoCosSig(JSONObject json) {
		JSONObject data=new JSONObject();
		String currenttime=String.valueOf(System.currentTimeMillis());
		String newCurrentTime= currenttime.substring(0,currenttime.length() -3);
		String keyTime=newCurrentTime+";"+String.valueOf(Long.parseLong(newCurrentTime)+Long.parseLong(json.getString("expires")));
		String signKey = HmacUtils.hmacSha1Hex(SystemConfig.getValue("SECRETKEY"), keyTime);
		data.put("signKey",signKey);
		data.put("keyTime",keyTime);
		mu = new MessageUtil(200, "请求成功");
		mu.setM_object(data);
		return mu;
	}

	/**
	 * 更新房间消息
	 * @param json
	 * @return
	 */
	@Override
	public MessageUtil ZhiBoUploadRoom(JSONObject json) {
		String userid=json.getString("userId");
		String title= null;
		try {
			title = new String(json.getString("title").getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String frontcover=json.getString("frontCover");
		String location=json.getString("location");
		System.out.println("userId="+userid+"title="+title+"frontcover="+frontcover+"location="+location);
		String sql = "INSERT INTO tb_room (userid, title, frontcover, location) VALUES (?, ?, ?, ?)";
		this.getFinalDao().getIEntitySQLDAO().saveData(sql, userid, title, frontcover,location);
		mu = new MessageUtil(200, "请求成功");
		return mu;
	}


}
