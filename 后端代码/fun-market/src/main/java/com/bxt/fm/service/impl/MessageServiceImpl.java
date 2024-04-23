package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.MessageMapper;
import com.bxt.fm.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource(name = "messageMapper")
    private MessageMapper messageMapper;
}
