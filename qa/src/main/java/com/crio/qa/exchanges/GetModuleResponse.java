package com.crio.qa.exchanges;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetModuleResponse {
  private List<QuestionGetResponse> questions;
}
