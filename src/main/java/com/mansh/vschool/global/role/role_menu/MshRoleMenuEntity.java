package com.mansh.vschool.global.role.role_menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
*
*
* @author mansh
* @since 2020-11-27
*/
@Data
@TableName("msh_role_menu")
public class MshRoleMenuEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_menu_id", type = IdType.AUTO)
    private Integer roleMenuId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;


}