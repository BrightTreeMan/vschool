package com.mansh.vschool.global.log.sys_log;

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
public class MshSysLogSearch extends BaseSearch {
    @ApiModelProperty(value = "账户ID")
    private String userName;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "操作类型")
    private String operation;

}