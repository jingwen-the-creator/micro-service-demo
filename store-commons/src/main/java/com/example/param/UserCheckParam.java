package com.example.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class UserCheckParam {

    @NotBlank
    private String userName; // 等于前段传递的json key名称
}
