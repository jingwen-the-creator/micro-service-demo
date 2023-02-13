package com.example.user.service;

import com.example.param.AddressRemoveParam;
import com.example.pojo.Address;
import com.example.utils.R;

public interface AddressService {

    R list(Integer userId);

    R save(Address address);

    R remove(Integer addressId);
}
