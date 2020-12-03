package com.mansh.vschool.global.role.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
*
* @author mansh
* @since 2020-11-26
*/
public interface MshRoleMapper extends BaseMapper<MshRoleEntity> {
    Page rolePage(IPage page, @Param("roleSearch")MshRoleSearch roleSearch);
}