package com.xiaoxiongwang.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.net.URLDecoder;

public class RequestJson {

    public static JSONObject get(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("UTF-8");
            String cipher_param = "";
            BufferedReader br = req.getReader();
            String str = "";
            while ((str = br.readLine()) != null) {
                cipher_param += str;
            }
            cipher_param = URLDecoder.decode(cipher_param,"UTF-8") ;
            System.out.println("req->"+cipher_param);
            if (StringUtils.isBlank(cipher_param)) {
                return null;
            }
            return JSONObject.fromObject(cipher_param);
        } catch (Exception e) {
            throw new RuntimeException("数据异常", e);
        }
    }
}
