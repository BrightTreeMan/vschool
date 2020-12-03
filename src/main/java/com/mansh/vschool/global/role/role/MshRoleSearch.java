package com.mansh.vschool.global.role.role;

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
@ApiModel(value="MshRoleEntity查询条件")
public class MshRoleSearch extends BaseSearch {
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

}