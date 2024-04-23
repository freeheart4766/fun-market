package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.ShoppingCartMapper;
import com.bxt.fm.service.ShoppingCartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource(name = "shoppingCartMapper")
    private ShoppingCartMapper shoppingCartMapper;
}
