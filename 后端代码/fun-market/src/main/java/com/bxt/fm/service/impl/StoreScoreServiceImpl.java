package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.StoreScoreMapper;
import com.bxt.fm.service.StoreScoreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("storeScoreService")
public class StoreScoreServiceImpl implements StoreScoreService {
    @Resource(name = "storeScoreMapper")
    private StoreScoreMapper storeScoreMapper;
}
