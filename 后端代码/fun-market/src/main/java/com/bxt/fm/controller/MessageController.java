package com.bxt.fm.controller;

import com.bxt.fm.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("message")
public class MessageController {
    @Resource(name = "messageService")
    private MessageService messageService;
}
