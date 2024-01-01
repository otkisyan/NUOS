#include "prism.h"
#include <math.h>

double calculatePrismDiagonal(double length, double width, double height) {
  return sqrt((length * length) + (width * width) + (height * height));
}
