package com.crio.qa.exchanges;

import com.crio.qa.entities.QuestionEntity;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSubmitResponse {
  @NotNull
  private String questionId;

  @NotNull
  private String title;

  @NotNull
  private String description;

  @NotNull
  private String type;

  private Map<String, String> options;

  private List<String> userAnswer;

  @NotNull
  private List<String> correct;

  private String explanation;

  private Boolean answerCorrect;

  public QuestionSubmitResponse(QuestionEntity questionEntity) {
    this.questionId = questionEntity.getQuestionId();
    this.title = questionEntity.getTitle();
    this.type = questionEntity.getType();
    this.options = questionEntity.getOptions();
    this.correct = questionEntity.getCorrectAnswer();
    this.description = questionEntity.getDescription();
  }
}
