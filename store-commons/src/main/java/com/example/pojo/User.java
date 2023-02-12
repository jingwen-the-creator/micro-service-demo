package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId( type = IdType.AUTO) //主键自动生成
    private Integer userID;
    private String userName;
    private String password;
    private String userPhoneNumber;
}