package com;

import com.alibaba.fastjson.JSONObject;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.UserInfoService;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TobShiroApplicationTests {

    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "13800138000");
        UserDO userDO = userInfoService.list(map).get(0);
        System.out.println(JSONObject.toJSONString(userDO));
    }

}

