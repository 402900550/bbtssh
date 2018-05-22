package com.hfkj.bbt.rest.web;

import com.alibaba.fastjson.JSON;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.rest.web.vo.WeixinReone;
import com.hfkj.bbt.rest.web.vo.WeixinRetwo;
import com.hfkj.bbt.systemanage.service.IUserService;
import com.hfkj.bbt.systemanage.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "wxapi")
public class WxController {
    private static String appid ="wxd0322c44022739f7";
    private static String secret = "eacd7abad3511aac456b5663eeb14a0f";

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "getUser")
    @ResponseBody
    public String getUser(String userInfo){
        return userInfo;
    }

    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public String getUserInfo(String userInfo,
                              String signature,
                              String encryptedData,
                              String iv){
        userInfo="近战法师";
        signature="f1314713d58703769cc37fe321735867169af9c2";
        encryptedData="ueTSk8YuzVmaIHr4A0yuQx2gYnEHJpJQ6WrvxEocT/GYfNWRhJXWO4rM7ELE0/XV9oAtMkh0mgsm/xj1+Iw/tJ+/7sP6ootEsGkkYef20KwXMTng7TuwCEsLcrZ4yzaJ3sXKXfrQ4GWRjVkcr5rOjGFkeCpdA4+xspRtTZINiEmrDgvf/ZNGoz0cuUya6RbV3p6kO8lsXA7Ufg0lXbQM1yaAU3QjFSAmqiUA6FhVxCGCyvFRuaKIK5gNP93Pa1bjA3ycEwQcAlT6NHTKEXoevGoy4BgWcqAlq12QnZ3yw/wyxqN5ffAXXLBrd7VjjolJKpKqFOWz6L1zKaJ6S5BdpydRABk+X48q1Xm8Y9tln704wBhoBnfLct+CbGjJUge1STGzz1iNrNgSuGcoNwCM+iA1p8mo45XHj5PatyARXQrzkFOzdzVcH2bLOm90zObmJtu77BF3i7iHr9otymjMaw==";
        iv="gXpGSuAp6W1U33vzb25sgQ==";

        return userInfo;
    }

    @RequestMapping(value = "wxLogin")
    @ResponseBody
    public List  weixinTest2(String code) {
        System.out.println(code);
//第一次交互
        RestTemplate client = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appid +
                "&secret=" +secret +
                "&js_code=" +code +
                "&grant_type=authorization_code";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        System.out.println("第一步code:"+code+",url1:"+url);
        ResponseEntity<String> response1 = client.exchange(url, HttpMethod.GET, null, String.class);

        String body = response1.getBody();
        System.out.println("000000000000000000000"+body);
        WeixinReone WeixinReone = JSON.parseObject(body, WeixinReone.class);
        System.out.println("第一步:"+WeixinReone.toString());
        System.out.println("第三步。UserInfo:"+WeixinReone.toString());
        User user = userService.findUserByOpenid(WeixinReone.getOpenid());
        List list = new ArrayList();
        if(user!=null){
            UserVo userIn=UserUtil.UserToVo(user);
            list.add("1");
            list.add(userIn);
        }else {
            list.add("0");
            list.add("自动登录失败");
        }
        return list;
    }

    /**
     * 微信登陆接口
     * @param userName
     * @return
     */
    @RequestMapping(value = "wxUserLogin")
    @ResponseBody
    public List restLogin(String userName,String password,String code){

        User user =  userService.loadUserByUserName(userName);
        List list = new ArrayList();
        if(user==null){
            list.add("0");
            list.add("用户名错误");
            return list;
        }
        if (!user.getPassword().equals(password)){
            list.add("2");
            list.add("密码错误");
            return list;
        }else {
//第一次交互
            RestTemplate client = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=" + appid +
                    "&secret=" +secret +
                    "&js_code=" +code +
                    "&grant_type=authorization_code";
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            System.out.println("第一步code:"+code+",url1:"+url);
            ResponseEntity<String> response1 = client.exchange(url, HttpMethod.GET, null, String.class);

            String body = response1.getBody();
            WeixinReone WeixinReone = JSON.parseObject(body, WeixinReone.class);
            System.out.println("第一步:"+WeixinReone.toString());
            int r =userService.saveOpenId(userName,WeixinReone.getOpenid());
            if((r+"").equals("1")){
                list.add(r+"");
                list.add(UserUtil.UserToVo(user));
            }else {
                list.add(r+"");
                list.add("网络错误");
            }

            return list;
        }

    }
}
