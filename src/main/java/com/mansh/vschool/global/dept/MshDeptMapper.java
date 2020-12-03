package com.mansh.vschool.global.dept;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
*
* @author mansh
* @since 2020-11-26
*/
public interface MshDeptMapper extends BaseMapper<MshDeptEntity> {
    IPage<MshDeptEntity> deptPage(IPage page, @Param("deptSearch") MshDeptSearch deptSearch);
    int deptOrder(@Param("deptId")int deptId);
}