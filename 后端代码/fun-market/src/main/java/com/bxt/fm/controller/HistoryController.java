package com.bxt.fm.controller;

import com.bxt.fm.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("history")
public class HistoryController {
    @Resource(name = "historyService")
    private HistoryService historyService;
}
