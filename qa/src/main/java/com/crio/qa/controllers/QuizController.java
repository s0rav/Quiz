package com.crio.qa.controllers;

import com.crio.qa.entities.QuestionEntity;
import com.crio.qa.exchanges.GetModuleResponse;
import com.crio.qa.exchanges.SubmitRequest;
import com.crio.qa.exchanges.SubmitResponse;
import com.crio.qa.services.QuizService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(QuizController.ENDPOINT)
public class QuizController {
  public static final String ENDPOINT = "/quiz/{moduleId}";

  @Autowired
  QuizService quizService;

  @GetMapping
  ResponseEntity<GetModuleResponse> getModule(@PathVariable String moduleId) {
    return ResponseEntity.ok(quizService.getQuestions(moduleId));
  }

  @PostMapping
  ResponseEntity<SubmitResponse> submit(@PathVariable String moduleId,
                                        @RequestBody SubmitRequest submitRequest) {
    return ResponseEntity.ok(quizService.getEvaluation(moduleId, submitRequest));
  }

  @PutMapping
  public ResponseEntity insert(@PathVariable String moduleId,
                                @RequestBody List<QuestionEntity> insertQuestionList) {
    quizService.insertQuestions(moduleId, insertQuestionList);
    return ResponseEntity.ok().build();
  }
}
