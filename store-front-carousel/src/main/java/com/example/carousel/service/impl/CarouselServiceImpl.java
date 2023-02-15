package com.example.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.carousel.mapper.CarouselMapper;
import com.example.pojo.Carousel;
import com.example.carousel.service.CarouselService;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;

    /**
     * 使用jdk 1.8的stream流对内存数据进行切割
     * @return
     */
    @Override
    public R list() {

        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        System.out.println("hello");
        queryWrapper.orderByAsc("priority");
        List<Carousel> list = carouselMapper.selectList(queryWrapper);
        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());//在内存中进行切割

        log.info("CarouselServiceImpl.list():{}",collect);
        return R.ok(collect);
    }
}
