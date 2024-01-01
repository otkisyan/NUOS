#include "rectangular.h"
#include <math.h>

double calculateDiagonal(double length, double width){
    return sqrt((length * length) + (width * width));
}