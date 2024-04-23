package com.bxt.fm.controller;

import com.bxt.fm.service.EvaluationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Resource(name = "evaluationService")
    private EvaluationService evaluationService;
}
