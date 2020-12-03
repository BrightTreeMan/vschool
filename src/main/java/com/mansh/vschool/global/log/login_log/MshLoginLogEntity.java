package com.mansh.vschool.global.log.login_log;

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
@TableName("msh_login_log")
public class MshLoginLogEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "login_log_id", type = IdType.AUTO)
    private Integer loginLogId;

    /**
     * 登录时间
     */
    @TableField("login_date")
    private Long loginDate;

    /**
     * 登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 登录详细信息
     */
    @TableField("login_desc")
    private String loginDesc;

    /**
     * 登录结果
     */
    @TableField("login_result")
    private String loginResult;

    /**
     * 登录人
     */
    @TableField("user_name")
    private String userName;


}