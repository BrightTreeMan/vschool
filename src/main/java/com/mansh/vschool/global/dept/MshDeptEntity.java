package com.mansh.vschool.global.dept;

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
@TableName("msh_dept")
public class MshDeptEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("dept_id")
    private Integer deptId;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 父级部门ID
     */
    @TableField("parent_dept_id")
    private int parentDeptId;

    /**
     * 排序
     */
    @TableField("list_order")
    private Integer listOrder;

    /**
     * 状态 0正常
     */
    @TableField("state")
    @ApiModelProperty(hidden = true)
    private String state;

    /**
     * 
     */
    @TableField("add_user_name")
    @ApiModelProperty(hidden = true)
    private String addUserName;

    /**
     * 
     */
    @TableField("add_date")
    @ApiModelProperty(hidden = true)
    private Long addDate;

    /**
     * 
     */
    @TableField("mod_user_name")
    @ApiModelProperty(hidden = true)
    private String modUserName;

    /**
     * 
     */
    @TableField("mod_date")
    @ApiModelProperty(hidden = true)
    private Long modDate;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String parentDeptName;

}