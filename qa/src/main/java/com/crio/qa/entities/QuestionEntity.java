package com.crio.qa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {

  @Id
  private String id;

  @NotNull
  @Field("questionId")
  private String questionId;

  @Field("moduleId")
  private String moduleId = "1";

  @NotNull
  private String title;

  @NotNull
  private String description;

  @NotNull
  private String type;

  @JsonProperty("options")
  private Map<String, String> options;

  @NotNull
  @Field("correctAnswer")
  private List<String> correctAnswer;
}
