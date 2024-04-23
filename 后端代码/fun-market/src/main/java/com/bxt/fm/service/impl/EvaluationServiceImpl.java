package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.EvaluationMapper;
import com.bxt.fm.service.EvaluationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService {
    @Resource(name = "evaluationMapper")
    private EvaluationMapper evaluationMapper;
}
