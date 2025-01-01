package org.example.jsfweb.model.sinh;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinhResult {
  private double sumN;
  private double exactSinh;
  private SinhResultWithE sinhResultWithE;
}
