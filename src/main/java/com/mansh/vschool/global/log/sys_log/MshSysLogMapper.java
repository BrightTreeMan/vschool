package com.mansh.vschool.global.log.sys_log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
* @author mansh
* @since 2020-11-26
*/
public interface MshSysLogMapper extends BaseMapper<MshSysLogEntity> {
    Page sysLogPage(IPage page, @Param("sysLogSearch") MshSysLogSearch sysLogSearch);
    List<MshSysLogEntity> operationList();
}