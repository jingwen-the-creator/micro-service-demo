package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.param.AddressRemoveParam;
import com.example.pojo.Address;
import com.example.user.mapper.AddressMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.service.AddressService;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public R list(Integer userId) {

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

//        Address address = addressMapper.selectOne(queryWrapper);
        List<Address> addresses = addressMapper.selectList(queryWrapper);
        log.info("AddressServiceImpl.list(), result:{}", "succeed");
        return R.ok("OK 查询成功", addresses);
    }

    @Override
    public R save(Address address) {
        int insert = addressMapper.insert(address);
        if (insert > 0) {
            log.info("AddressServiceImpl.save(), result:{}", "succeed");
//            return R.ok("保存地址成功", );
            return list(address.getUserId());// 复用查询业务
        }
        log.info("AddressServiceImpl.save(), result:{}", "failed");
        return R.fail("保存地址失败");
    }

    @Override
    public R remove(Integer addressId) {
        int i = addressMapper.deleteById(addressId);
        if(i != 0){
            log.info("AddressServiceImpl.remove(), result:{}", "succeed");
            return R.ok("删除地址成功");
        }
        log.info("AddressServiceImpl.remove(), result:{}", "failed");
        return R.fail("删除地址失败");
    }
}
