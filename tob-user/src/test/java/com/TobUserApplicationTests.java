package com;

import com.alibaba.fastjson.JSONObject;
import com.orgmanagement.domain.OrgInfoDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.UserInfoService;
import com.utils.R;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TobUserApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    private OrgInfoService orgInfoService;

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "13800138000");
        UserDO userDO = userInfoService.list(map).get(0);
        System.out.println(JSONObject.toJSONString(userDO));
    }


    @Test
    public void test(){
        R result = restTemplate.getForObject( "http://tob-budget/subject/subListByOrgId/{orgId}", R.class,1);

//        R result = restTemplate.getForObject(budgetUrl + "/subject/subListByOrgId/{orgId}", R.class,1);
        ArrayList<LinkedHashMap<String, Object>>  obj = (ArrayList<LinkedHashMap<String, Object>> ) result.get("obj");
//        orgInfoListMap = (ArrayList<LinkedHashMap<String, Object>>)obj.get("subjects");
        obj.stream().forEach(item->{
            System.out.println(item.get("subName"));
        });
    }

    @Test
    public void test2(){
        List<Map<String,Object>> subRefOrgs = new ArrayList<>();
        for (Integer i =0;i<10; i++){
            Map<String,Object> subRefOrg = new HashMap<>();
            subRefOrg.put("orgId",1);
            subRefOrg.put("subId",4);
            subRefOrg.put("createDate",new Date());
            subRefOrgs.add(subRefOrg);
        }
        R result2 = restTemplate.postForObject( "http://tob-budget/subject/addSubRefOrgs",subRefOrgs, R.class );
        System.out.println(result2);
    }


    @Test
    public void test1(){
        List<OrgInfoDO> bottomOrgList = orgInfoService.findBottomOrgList(1);
        System.out.println(JSONObject.toJSONString(bottomOrgList));
    }

}

