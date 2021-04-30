package com.xiaoxiaowang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoxiaowang.service.CodePaySetUpService;
import com.xiaoxiaowang.util.MessageUtil;

import net.sf.json.JSONObject;

@Service("codePaySetUpService")
public class CodePaySetUpImpl extends ICommServiceImpl implements CodePaySetUpService {

	@Override
	public JSONObject getCodePaySetUpList(int page) {
		try {
			
			String qSql = "SELECT * FROM t_codepay_setup";
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);
			
			JSONObject json = new JSONObject();
			
			
			String cSql = "SELECT COUNT(t_id) AS total FROM t_codepay_setup";
			
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			
			json.put("total", toMap.get("total"));
			json.put("rows", dataMap);
			
			return json;
			
		} catch (Exception e) {
			logger.error("获取码支付设置异常!", e);
		}
		return null;
	}

	
	@Override
	public MessageUtil addOrUpCodePaySetUp(Integer t_id, String code_id,
			String code_key, String notify_url, String token) {
		
		MessageUtil mu = null ;
		try {
			
			if(null == t_id || 0 == t_id){
				String inSql = " INSERT INTO t_codepay_setup (code_id, code_key, notify_url, token) VALUES (?,?,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, code_id,code_key,notify_url,token);
			}else{
				String uSql = " UPDATE t_codepay_setup SET  code_id=?, code_key=?, notify_url=?, token=? WHERE t_id= ? ; ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, code_id,code_key,notify_url,token,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或修改异常!", e);
			mu = new MessageUtil(0, "");
		}
		return mu;
	}


	@Override
	public MessageUtil delCodePaySetUp(int t_id) {
		MessageUtil mu = null ;
		try {
			
			String dSql = " DELETE FROM t_codepay_setup WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除码支付设置异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
