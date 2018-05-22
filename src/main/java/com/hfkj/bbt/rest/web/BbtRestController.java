package com.hfkj.bbt.rest.web;

import com.alibaba.fastjson.JSON;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.HttpClientApi;
import com.hfkj.bbt.rest.service.IRestService;

import com.hfkj.bbt.rest.web.vo.DataVo;
import com.hfkj.bbt.rest.web.vo.Header;
import com.hfkj.bbt.rest.web.vo.RunTime;
import com.hfkj.bbt.systemanage.service.IUserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017-12-27.
 */
@RestController
@RequestMapping(value = "rest")
public class BbtRestController {

    private static final Logger log=Logger.getLogger(BbtRestController.class);
    @Autowired
    private IRestService restService;



    /**
     * 接收网关的请求
     *
     * @param sendData
     * @return
     */
    @RequestMapping(value = "sendStatus")
    @ResponseBody
    public String sendStatus( String sendData) {
        log.info("收到来自设备的数据："+sendData);
        if (!ComUtil.stringIsNotNull(sendData)){

            return "数据为空";
        }
        return restService.doAnalysisData(sendData);
    }





}















