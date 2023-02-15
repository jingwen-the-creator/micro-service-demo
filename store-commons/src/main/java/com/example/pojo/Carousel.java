package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("carousel")
@Data
public class Carousel implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty("carousel_id")
    @TableId(type = IdType.AUTO)
    private Integer carouselId;

    @NotBlank
    private String imgPath;
    @NotNull
    private String describes;

    @JsonProperty("product_id")
    private Integer productId;

    private Integer priority;
}
