package com.mansh.vschool.global.user.user_role;

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
@TableName("msh_user_role")
public class MshUserRoleEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Integer userRoleId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;


}