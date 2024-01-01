 extern "C" {
    #include "prism.h"
 }

#include <iostream>

int main() {
    std::cout << "Rectangular Prism\n";
    double width = 3, length = 2, height = 5;
    std::cout << "Length: " << length << std::endl;
    std::cout << "Width: " << width << std::endl;
    std::cout << "Height: " << height << std::endl;
    std::cout << "Base Perimeter: " << calculatePrismPerimeter(length, width) << std::endl;
    std::cout << "Surface Area: " << calculatePrismSurfaceArea(length, width, height) << std::endl;
    std::cout << "Diagonal: " << calculatePrismDiagonal(length, width, height) << std::endl;

    return 0;
}
