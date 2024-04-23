package com.bxt.fm.controller;

import com.bxt.fm.service.StoreScoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("storeScore")
public class StoreScoreController {
    @Resource(name = "storeScoreService")
    private StoreScoreService storeScoreService;
}
