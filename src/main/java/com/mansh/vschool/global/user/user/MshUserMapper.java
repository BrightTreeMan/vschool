package com.mansh.vschool.global.user.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
*
* @author mansh
* @since 2020-10-12
*/
public interface MshUserMapper extends BaseMapper<MshUserEntity> {
    IPage<MshUserEntity> userPage(IPage page, @Param("userSearch") MshUserSearch userSearch);
}