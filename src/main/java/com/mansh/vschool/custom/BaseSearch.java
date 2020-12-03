package com.mansh.vschool.custom;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseSearch implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "条数",required = true,example = "1")
    private int current;

    @ApiModelProperty(value = "当前页数",required = true,example = "10")
    private int size;
}
