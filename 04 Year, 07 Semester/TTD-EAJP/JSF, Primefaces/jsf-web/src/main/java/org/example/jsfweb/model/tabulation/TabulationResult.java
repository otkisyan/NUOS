package org.example.jsfweb.model.tabulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabulationResult {
  private double minElement;
  private double minElementArgument;
  private double maxElement;
  private double maxElementArgument;
  private double average;
  private double sum;
  private List<Point> points;
}
