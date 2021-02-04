package com.lz.bmp.service.achieve;

import com.alibaba.fastjson.JSONObject;
import com.lz.bmp.service.Achieve;
import com.lz.bmp.utils.CommonUtils;
import com.lz.bmp.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author rocky
 * @ClassName AchieveJsonObject
 * @Description
 * @Create by rocky
 * @Date 2020/11/16 下午3:09
 */
@Service
public class AchieveJsonObject implements Achieve {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object achieveData(String url, String parameter, JSONObject param, String accessMode) {
        ResponseEntity forEntity = null;
        if(Constants.GET.equals(accessMode)){
            // todo 生成 url
            String requestParam = CommonUtils.getRequestParam(parameter);
            url += requestParam;
            forEntity = restTemplate.getForEntity(url, JSONObject.class, param);
        }else {
            forEntity = restTemplate.postForEntity(url, param, JSONObject.class, new Object());
        }
        return  forEntity.getBody();
    }
}
