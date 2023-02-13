package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("address")
public class Address implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    @TableField(value = "linkman")//后台 数据库
    @JsonProperty("linkman") //前台 后台
    private String contactName;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @NotBlank
    private String address;

    @NotNull
    private Integer userId;

}
