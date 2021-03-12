package com.crio.qa.exchanges;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
  @NotNull
  private int score;

  @NotNull
  private int total;

  public Summary(int score) {
    this.score = score;
    this.total = 2 * score;
  }
}
