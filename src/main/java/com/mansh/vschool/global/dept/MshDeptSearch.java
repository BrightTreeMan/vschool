package com.mansh.vschool.global.dept;

import com.mansh.vschool.custom.BaseSearch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*
*
* @author mansh
* @since 2020-10-12
*/
@Data
@ApiModel(value="MshUserEntity查询条件")
public class MshDeptSearch extends BaseSearch {
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

}