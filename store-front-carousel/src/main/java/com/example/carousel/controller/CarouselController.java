package com.example.carousel.controller;

import com.example.carousel.service.CarouselService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;
    /**
     * 查询轮播图数据只要优先级最高的6条
     * @return
     */
    @GetMapping("list")
    public R list(){
        System.out.println("okok");

        return carouselService.list();
    }
}
