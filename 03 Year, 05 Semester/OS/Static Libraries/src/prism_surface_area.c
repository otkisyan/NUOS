#include "prism.h"

double calculatePrismSurfaceArea(double length, double width, double height) {
  return 2 * (length * width) + (2 * length + 2 * width) * height;
}
