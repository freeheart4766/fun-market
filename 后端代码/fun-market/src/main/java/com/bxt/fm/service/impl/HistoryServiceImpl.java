package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.HistoryMapper;
import com.bxt.fm.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Resource(name = "historyMapper")
    private HistoryMapper historyMapper;
}
