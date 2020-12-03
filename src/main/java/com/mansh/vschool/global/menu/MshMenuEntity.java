package com.mansh.vschool.global.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
*
*
* @author mansh
* @since 2020-11-25
*/
@Data
@TableName("msh_menu")
@ApiModel(value="MshMenuEntity对象", description="菜单表")
public class MshMenuEntity implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单等级")
    private String menuLevel;

    @ApiModelProperty(value = "父级菜单ID")
    private Integer parentMenuId;

    @ApiModelProperty(value = "字体图标")
    private String iconType;

    @ApiModelProperty(value = "状态 0正常")
    private String state;

    @ApiModelProperty(value = "排序")
    private String listOrder;

    @ApiModelProperty(value = "路径")
    private String url;


}