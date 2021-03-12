package com.crio.qa.services;

import com.crio.qa.entities.QuestionEntity;
import com.crio.qa.exchanges.GetModuleResponse;
import com.crio.qa.exchanges.QuestionGetResponse;
import com.crio.qa.exchanges.QuestionSubmitResponse;
import com.crio.qa.exchanges.SubmitEach;
import com.crio.qa.exchanges.SubmitRequest;
import com.crio.qa.exchanges.SubmitResponse;
import com.crio.qa.exchanges.Summary;
import com.crio.qa.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizService {
  @Autowired
  QuestionRepository questionRepository;

  public GetModuleResponse getQuestions(String moduleId) {

    List<QuestionEntity> questionEntities = questionRepository
        .findAll()
        .stream()
        .filter(e -> {
          if (e.getModuleId().equals(moduleId)) {
            return true;
          }
          if (moduleId.equals("1") && e.getModuleId() == null) {

            return true;
          }
          return false;
        })
        .collect(Collectors.toList());
    List<QuestionGetResponse> questions = questionEntities
        .stream()
        .map(questionEntity -> {
          QuestionGetResponse questionGetResponse =
              new QuestionGetResponse(questionEntity);
          return questionGetResponse;
        })
        .sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getQuestionId())))
        .collect(Collectors.toList());
    return new GetModuleResponse(questions);
  }


  public SubmitResponse getEvaluation(String moduleId, SubmitRequest submitRequest) {

    List<QuestionEntity> questionEntities = questionRepository
        .findAll()
        .stream()
        .filter(e -> {
          if (e.getModuleId().equals(moduleId)) {
            return true;
          }
          if (moduleId.equals("1") && e.getModuleId() == null) {
            return true;
          }
          return false;
        })
        .sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getQuestionId())))
        .collect(Collectors.toList());

    List<SubmitEach> submitEachList = submitRequest
        .getResponses()
        .stream()
        .sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getQuestionId())))
        .collect(Collectors.toList());


    int score = 0;

    List<QuestionSubmitResponse> questionSubmitResponseList = new ArrayList<>();

    for (int i = 0; i < questionEntities.size(); ++i) {

      QuestionSubmitResponse questionSubmitResponse =
          new QuestionSubmitResponse(questionEntities.get(i));

      final List<String> userResponse =
          submitEachList
              .get(i)
              .getUserResponse()
              .stream()
              .sorted()
              .collect(Collectors.toList());
      final List<String> correctAnswer =
          questionEntities
              .get(i)
              .getCorrectAnswer()
              .stream()
              .sorted()
              .collect(Collectors.toList());
      Boolean isCorrectResponse = userResponse.equals(correctAnswer);

      if (isCorrectResponse) {
        ++score;
      }

      questionSubmitResponse.setAnswerCorrect(isCorrectResponse);
      questionSubmitResponse.setCorrect(correctAnswer);
      questionSubmitResponse.setUserAnswer(userResponse);
      questionSubmitResponseList.add(questionSubmitResponse);
    }

    return new SubmitResponse(questionSubmitResponseList, new Summary(score));
  }

  @Transactional
  public void insertQuestions(String moduleId, List<QuestionEntity> insertQuestionList) {
    questionRepository.saveAll(insertQuestionList);
  }
}
