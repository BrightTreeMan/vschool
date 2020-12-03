package com.mansh.vschool.global.menu;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mansh.vschool.custom.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
*
* @author mansh
* @since 2020-11-25
*/
@Service
public class MshMenuService{
    @Resource
    private MshMenuMapper menuMapper;

    public ResultData menuList(String userName) {
        JSONArray jsonArray = new JSONArray();
        List<MshMenuEntity> list = menuMapper.selectListByUserName(userName);
        Map<Integer,List<MshMenuEntity>> map = new LinkedHashMap<>();
        for(MshMenuEntity menu:list){
            if(map.containsKey(menu.getParentMenuId())){
                map.get(menu.getParentMenuId()).add(menu);
            }else{
                List<MshMenuEntity> temp = new ArrayList();
                temp.add(menu);
                map.put(menu.getParentMenuId(),temp);
            }
        }

        for (Map.Entry<Integer, List<MshMenuEntity>> m : map.entrySet()) {
            MshMenuEntity secondMenu = menuMapper.selectById(m.getKey());
            JSONObject json = new JSONObject();
            json.put("menuId",secondMenu.getMenuId());
            json.put("menuName",secondMenu.getMenuName());
            json.put("iconType",secondMenu.getIconType());
            json.put("level3MenuList",m.getValue());
            jsonArray.add(json);

        }

        return new ResultData(jsonArray).setSuccess(true);
    }

    public ResultData menuListForChoose() {
        JSONArray jsonArray = new JSONArray();
        QueryWrapper<MshMenuEntity> query = new QueryWrapper<MshMenuEntity>();
        query.eq("menu_level","2")
                .orderByAsc("list_order");

        List<MshMenuEntity> level2MenuList = menuMapper.selectList(query);
        for(MshMenuEntity level2Menu:level2MenuList){
            JSONObject json = new JSONObject();
            json.put("value",level2Menu.getMenuId());
            json.put("label",level2Menu.getMenuName());

            QueryWrapper<MshMenuEntity> query3 = new QueryWrapper<MshMenuEntity>();
            query3.eq("menu_level","3")
                    .eq("parent_menu_id",level2Menu.getMenuId())
                    .orderByAsc("list_order");
            List<MshMenuEntity> level3MenuList = menuMapper.selectList(query3);
            JSONArray childrenArr = new JSONArray();
            for(MshMenuEntity level3Menu:level3MenuList){
                JSONObject json3 = new JSONObject();
                json3.put("value",level3Menu.getMenuId());
                json3.put("label",level3Menu.getMenuName());
                childrenArr.add(json3);
            }
            json.put("children",childrenArr);
            json.put("chooseMenuId","");
            jsonArray.add(json);
        }

        return new ResultData(jsonArray).setSuccess(true);
    }
}