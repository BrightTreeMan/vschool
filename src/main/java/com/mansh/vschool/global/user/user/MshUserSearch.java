package com.mansh.vschool.global.user.user;

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
public class MshUserSearch extends BaseSearch {
    @ApiModelProperty(value = "账户ID")
    private String userName;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

}