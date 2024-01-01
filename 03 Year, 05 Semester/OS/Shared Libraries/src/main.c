#include "prism.h"
#include <stdio.h>

int main() {

  printf("Rectangular Prism\n");
  double width = 3, length = 2, height = 5;
  printf("Length: %f\n", length);
  printf("Width: %f\n", width);
  printf("Height: %f\n", height);
  printf("Base Perimeter: %f\n", calculatePrismPerimeter(length, width));
  printf("Surface Area: %f\n",
         calculatePrismSurfaceArea(length, width, height));
  printf("Diagonal: %f\n", calculatePrismDiagonal(length, width, height));

  return 0;
}
