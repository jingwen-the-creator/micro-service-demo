package com.example.user.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.example.param.AddressListParam;
import com.example.param.AddressRemoveParam;
import com.example.pojo.Address;
import com.example.user.service.AddressService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//json数据
@RequestMapping("user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 根据用户id查询地址数据
     *
     * @param param  用户id 已经校验完毕
     * @param result
     * @return 001 004
     */
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam param, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数异常，找不到user id。");
        }
        return addressService.list(param.getUserId());
    }

    @PostMapping("save")
    public R save(@RequestBody @Validated Address address, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数异常,保存失败！");
        }
        return addressService.save(address);
    }

    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressId, BindingResult result){
        if (result.hasErrors()) {
            return R.fail("参数异常,保存失败！");
        }
        return addressService.remove(addressId.getAddressId());
    }
}
