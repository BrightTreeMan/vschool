package com.mansh.vschool.global.log.login_log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
*
* @author mansh
* @since 2020-11-26
*/
public interface MshLoginLogMapper extends BaseMapper<MshLoginLogEntity> {
    Page loginLogPage(IPage page, @Param("loginLogSearch")MshLoginLogSearch loginLogSearch);
}