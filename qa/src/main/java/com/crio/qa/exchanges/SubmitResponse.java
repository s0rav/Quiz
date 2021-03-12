package com.crio.qa.exchanges;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitResponse {
  @NotNull
  private List<QuestionSubmitResponse> questions = new ArrayList<>();

  @NotNull
  private Summary summary;

}
