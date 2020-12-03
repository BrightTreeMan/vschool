package com.mansh.vschool.global.user.user;

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
* @since 2020-10-12
*/
@Data
@TableName("msh_user")
public class MshUserEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer userId;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;

    /**
     * 账户ID
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码 密文
     */
    @TableField("password")
    private String password;

    /**
     * 加密信息
     */
    @TableField("salt")
    @ApiModelProperty(hidden = true)
    private String salt;

    /**
     * 状态 0 正常 1冻结
     */
    @TableField("state")
    @ApiModelProperty(hidden = true)
    private String state;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 增加人
     */
    @TableField("add_user_name")
    @ApiModelProperty(hidden = true)
    private String addUserName;

    /**
     * 增加日期
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
     * 修改人姓名
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String stateName;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String deptName;

    /**
     * 角色数组
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private int[] userRoleId;

}