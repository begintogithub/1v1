package com.xiaoxiaowang.service;

import com.xiaoxiaowang.util.MessageUtil;

import net.sf.json.JSONObject;

public interface EnrollService {
   /**
    * 获取注册红包列表
    * @param page
    * @return
    */
   public JSONObject getEnrollList(int page);
   
   /**
    * 添加注册红包记录
    * @param t_sex
    * @param t_gold
    * @return
    */
   public MessageUtil addEnroll(int t_sex,int t_gold);
   
   /**
    * 删除数据
    * @param t_id
    * @return
    */
   public MessageUtil delEnroll(int t_id);
}
