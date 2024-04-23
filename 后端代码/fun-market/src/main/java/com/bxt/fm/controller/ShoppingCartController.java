package com.bxt.fm.controller;

import com.bxt.fm.service.ShoppingCartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Resource(name = "shoppingCartService")
    private ShoppingCartService shoppingCartService;
}
