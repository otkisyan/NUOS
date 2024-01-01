#include <stdio.h>
#include <windows.h>
#include "getopt.h"

typedef double (*calculateSurfaceAreaFunction)(double, ...);
typedef double (*calculatePerimeterFunction)(double, ...);
typedef double (*calculateDiagonalFunction)(double, ...);

HINSTANCE loadLibrary(char *libraryName) {
  HINSTANCE hinstLib = LoadLibrary(libraryName);
  if (hinstLib == NULL) {
    printf("Error loading library: %s\n", libraryName);
    exit(EXIT_FAILURE);
  }
  return hinstLib;
}

void loadPluginFunctions(HINSTANCE hinstLib,
                         calculateSurfaceAreaFunction *surfaceAreaFunc,
                         calculatePerimeterFunction *perimeterFunc,
                         calculateDiagonalFunction *diagonalFunc) {
  *surfaceAreaFunc = (calculateSurfaceAreaFunction)GetProcAddress(hinstLib, "calculateSurfaceArea");
  *perimeterFunc = (calculatePerimeterFunction)GetProcAddress(hinstLib, "calculatePerimeter");
  *diagonalFunc = (calculateDiagonalFunction)GetProcAddress(hinstLib, "calculateDiagonal");

  if (*surfaceAreaFunc == NULL || *perimeterFunc == NULL || *diagonalFunc == NULL) {
    printf("Error loading symbols\n");
    FreeLibrary(hinstLib);
    exit(EXIT_FAILURE);
  }
}

int main(int argc, char *argv[]){

  HINSTANCE hinstLib;
  char *opts = "prw:h:l:";
  int opt;
  double width = 0, length = 0, height = 0;
  calculateSurfaceAreaFunction calculateSurfaceArea;
  calculatePerimeterFunction calculatePerimeter;
  calculateDiagonalFunction calculateDiagonal;

  while ((opt = getopt(argc, argv, opts)) != -1) {

    switch (opt) {

    case 'w': {
      char *width_str = optarg;
      if (width_str) {
        width = atof(width_str);
      }
      break;
    }
    case 'h': {
      char *height_str = optarg;
      if (height_str) {
        height = atof(height_str);
      }
      break;
    }
    case 'l': {
      char *length_str = optarg;
      if (length_str) {
        length = atof(length_str);
      }
      break;
    }
    case 'p': {

      if (!width || !height || !length) {
        printf("Prism: Missing arguments\n");
        printf("Usage: -w <width> -h <height> -l <length> -p \n");
        exit(1);
      }

      hinstLib = loadLibrary("libprism.dll");

      printf("Rectangular Prism\n");
      printf("Length: %f\n", length);
      printf("Width: %f\n", width);
      printf("Height: %f\n", height);

      loadPluginFunctions(hinstLib, &calculateSurfaceArea, &calculatePerimeter,
                          &calculateDiagonal);

      double surfaceArea = calculateSurfaceArea(length, width, height);
      double perimeter = calculatePerimeter(length, width);
      double diagonal = calculateDiagonal(length, width, height);

      printf("Base Perimeter: %f\n", perimeter);
      printf("Surface Area: %f\n", surfaceArea);
      printf("Diagonal: %f\n", diagonal);
      printf("\n");

      break;
    }

    case 'r': {

      if (!width || !length) {
        printf("Rectangular: Missing arguments\n");
        printf("Usage: -w <width> -l <length> -r \n");
        exit(1);
      }
      
      hinstLib = loadLibrary("librectangular.dll");

      printf("Rectangular\n");
      printf("Length: %f\n", length);
      printf("Width: %f\n", width);

      loadPluginFunctions(hinstLib, &calculateSurfaceArea, &calculatePerimeter,
                          &calculateDiagonal);

      double surfaceArea = calculateSurfaceArea(length, width);
      double perimeter = calculatePerimeter(length, width);
      double diagonal = calculateDiagonal(length, width);

      printf("Perimeter: %f\n", perimeter);
      printf("Surface Area: %f\n", surfaceArea);
      printf("Diagonal: %f\n", diagonal);
      printf("\n");

      break;
    }
    }
  }

    FreeLibrary(hinstLib);

    return 0;
}
