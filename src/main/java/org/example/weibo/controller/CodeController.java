package org.example.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

    @Controller
    public class CodeController {
        //短信平台相关参数
        //这个不用改
        private String apiUrl = "https://sms_developer.zhenzikj.com";
        //榛子云系统上获取
        private String appId = "110638";
        private String appSecret = "88809d16-f8fd-448d-bf0e-a7f778681c93";

        @ResponseBody
        @GetMapping("/fitness/code")
        public boolean getCode(@RequestParam("memPhone") String memPhone, HttpSession httpSession){
            try {
                JSONObject json = null;
                //随机生成验证码
                //将验证码通过榛子云接口发送至手机
                ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
                String code = String.valueOf(new Random().nextInt(899999) + 100000);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("number", memPhone);
                params.put("templateId", "7751");
                //这个参数就是短信模板上那两个参数
                String[] templateParams = new String[2];
                templateParams[0] = code;
                templateParams[1] = "2分钟";
                params.put("templateParams", templateParams);
                String result = client.send(params);
                System.out.println(result);
                json = JSONObject.parseObject(result);
                if (json.getIntValue("code")!=0){//发送短信失败
                    System.out.println("发信息失败");
                    return  false;
                }
                //将验证码存到session中,同时存入创建时间
                //以json存放，这里使用的是阿里的fastjson
                json = new JSONObject();
                json.put("memPhone",memPhone);
                json.put("code",code);
                json.put("createTime",System.currentTimeMillis());
                // 将认证码存入SESSION
                httpSession.setAttribute("code",json);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
