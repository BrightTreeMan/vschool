package com.mansh.vschool.global.role.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
*
*
* @author mansh
* @since 2020-11-26
*/
@Data
@TableName("msh_role")
public class MshRoleEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("role_desc")
    private String roleDesc;

    /**
     * 新增人
     */
    @TableField("add_user_name")
    @ApiModelProperty(hidden = true)
    private String addUserName;

    /**
     * 新增时间
     */
    @TableField("add_date")
    @ApiModelProperty(hidden = true)
    private Long addDate;

    /**
     * 修改人
     */
    @TableField("mod_user_name")
    @ApiModelProperty(hidden = true)
    private String modUserName;

    /**
     * 修改时间
     */
    @TableField("mod_date")
    @ApiModelProperty(hidden = true)
    private Long modDate;

    /**
     * 状态 0正常
     */
    @TableField("state")
    @ApiModelProperty(hidden = true)
    private String state;

    /**
     * 状态名称
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String stateName;

    /**
     * 角色-菜单ID
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private int[] roleMenuId;
}