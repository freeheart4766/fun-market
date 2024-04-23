package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.RefundProgressMapper;
import com.bxt.fm.service.RefundProgressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("refundProgressService")
public class RefundProgressServiceImpl implements RefundProgressService {
    @Resource(name = "refundProgressMapper")
    private RefundProgressMapper refundProgressMapper;
}
