package com.xcy.controller;

import com.xcy.mapper.AreaMapper;
import com.xcy.pojo.Area;
import com.xcy.utils.JedisClient;
import com.xcy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AreaController {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private JedisClient jedisClient;
    @RequestMapping("/getProvince")
    @ResponseBody
    public List<Area> getProvince(){
        System.out.println("测试");
        boolean isExiists =jedisClient.exists("PROVINCEALL");
        List<Area> list = null;
        if (isExiists){
            String jsonStr = jedisClient.get("PROVINCEALL");
            list = JsonUtils.jsonToList(jsonStr,Area.class);
        } else{
            list = areaMapper.getProvince();
            jedisClient.set("PROVINCEALL",JsonUtils.objectToJson(list));
        }
        return list;
    }

    @RequestMapping("/getCity")
    @ResponseBody
    public List<Area> getCity(int id){
        return areaMapper.getCity(id);
    }

    @RequestMapping("/getCountry")
    @ResponseBody
    public List<Area> getCountry(int id){
        return areaMapper.getCountry(id);
    }
}
