package com.mansh.vschool.global.menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
* @author mansh
* @since 2020-11-25
*/
public interface MshMenuMapper extends BaseMapper<MshMenuEntity> {
    List<MshMenuEntity> selectListByUserName(@Param("userName")String userName);
}