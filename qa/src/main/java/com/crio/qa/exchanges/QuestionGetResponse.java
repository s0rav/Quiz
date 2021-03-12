package com.crio.qa.exchanges;

import com.crio.qa.entities.QuestionEntity;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGetResponse {
  @NotNull
  private String questionId;

  @NotNull
  private String title;

  @NotNull
  private String type;

  private Map<String, String> options;

  public QuestionGetResponse(QuestionEntity questionEntity) {
    this.questionId = questionEntity.getQuestionId();
    this.title = questionEntity.getTitle();
    this.type = questionEntity.getType();
    this.options = questionEntity.getOptions();
  }
}
