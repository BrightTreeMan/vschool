package com.mansh.vschool.global.log.sys_log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;

/**
*
*
* @author mansh
* @since 2020-11-26
*/
@Data
@TableName("msh_sys_log")
public class MshSysLogEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "sys_log_id", type = IdType.AUTO)
    private Integer sysLogId;

    /**
     * 操作IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 操作人ID
     */
    @TableField("user_name")
    private String userName;

    /**
     * 操作描述
     */
    @TableField("operation")
    private String operation;

    /**
     * 具体描述
     */
    @TableField("operation_desc")
    private String operationDesc;

    /**
     * 操作结果
     */
    @TableField("operation_result")
    private String operationResult;

    /**
     * 操作时间
     */
    @TableField("date")
    private Long date;


}