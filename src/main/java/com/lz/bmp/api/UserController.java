package com.lz.bmp.api;

import com.fpi.simple.result.PlainResult;
import com.lz.bmp.vo.UserApiVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:38
 */

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    @PostMapping("queryAll")
    public Object queryAllSiteCodeToItem() {
        PlainResult<Map<String, UserApiVo>> plainResult = new PlainResult<>();


        return plainResult;
    }
}
