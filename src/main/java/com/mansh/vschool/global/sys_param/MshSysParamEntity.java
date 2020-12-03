package com.mansh.vschool.global.sys_param;

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
* @since 2020-11-26
*/
@Data
@TableName("msh_sys_param")
public class MshSysParamEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "param_id", type = IdType.AUTO)
    private Integer paramId;

    /**
     * 码值代码
     */
    @TableField("param_name")
    private String paramName;

    /**
     * 码值名称
     */
    @TableField("param_val")
    private String paramVal;

    /**
     * 码值对应的字段
     */
    @TableField("param_item")
    private String paramItem;

    /**
     * 排序
     */
    @TableField("list_order")
    private Integer listOrder;


}