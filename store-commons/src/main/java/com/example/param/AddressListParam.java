package com.example.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 通过userId查询其中的地址列表
 */
@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id") //双向注解 前台接收的是user_id的数据
    private Integer userId;
}
