## бібліотека libprism

### prism.h
```c
#ifndef PRISM_H
#define PRISM_H

double calculateSurfaceArea(double length, double width, double height);
double calculatePerimeter(double length, double width);
double calculateDiagonal(double length, double width, double height);

#endif // PRISM_H
```
### prism_diagonal.c
```c
#include "prism.h"
#include <math.h>

double calculateDiagonal(double length, double width, double height) {
  return sqrt((length * length) + (width * width) + (height * height));
}
```
### prism_perimeter.c
```c
#include "prism.h"

double calculatePerimeter(double length, double width) {
  return 2 * (length + width);
}
```

### prism_surface_area.c
```c
#include "prism.h"

double calculateSurfaceArea(double length, double width, double height) {
  return 2 * (length * width) + (2 * length + 2 * width) * height;
}
```

## бібліотека librectangular
### rectangular.h
```c
#ifndef RECTANGULAR_H
#define RECTANGULAR_H

double calculateSurfaceArea(double length, double width);
double calculatePerimeter(double length, double width);
double calculateDiagonal(double length, double width);

#endif // RECTANGULAR_H
```
### rectangular_diagonal.c
```c
#include "rectangular.h"
#include <math.h>

double calculateDiagonal(double length, double width){
    return sqrt((length * length) + (width * width));
}
```
### rectangular_perimeter.c
```c
#include "rectangular.h"

double calculatePerimeter(double length, double width){
    return 2 * (length + width);
}
```
### rectangular_surface_area.c
```c
#include "rectangular.h"

double calculateSurfaceArea(double length, double width){
    return length * width;
}
```

## Linux

```c
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

typedef double (*calculateSurfaceAreaFunction)(double, ...);
typedef double (*calculatePerimeterFunction)(double, ...);
typedef double (*calculateDiagonalFunction)(double, ...);

void *loadLibrary(const char *libraryName) {
  void *handle = dlopen(libraryName, RTLD_LAZY);
  if (!handle) {
    printf("%s\n", dlerror());
    exit(EXIT_FAILURE);
  }

  dlerror(); /* Clear any existing error */
  return handle;
}

void loadPluginFunctions(void *handle,
                         calculateSurfaceAreaFunction *surfaceAreaFunc,
                         calculatePerimeterFunction *perimeterFunc,
                         calculateDiagonalFunction *diagonalFunc) {
  *surfaceAreaFunc =
      (calculateSurfaceAreaFunction)dlsym(handle, "calculateSurfaceArea");
  *perimeterFunc =
      (calculatePerimeterFunction)dlsym(handle, "calculatePerimeter");
  *diagonalFunc = (calculateDiagonalFunction)dlsym(handle, "calculateDiagonal");

  const char *error = dlerror();
  if (error != NULL) {
    printf("Error loading symbols: %s\n", error);
    dlclose(handle);
    exit(EXIT_FAILURE);
  }
}

int main(int argc, char *argv[]) {

  void *handle;
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

      handle = loadLibrary("./libprism.so");

      printf("Rectangular Prism\n");
      printf("Length: %f\n", length);
      printf("Width: %f\n", width);
      printf("Height: %f\n", height);

      loadPluginFunctions(handle, &calculateSurfaceArea, &calculatePerimeter,
                          &calculateDiagonal);

      double surfaceArea = (*calculateSurfaceArea)(length, width, height);
      double perimeter = (*calculatePerimeter)(length, width);
      double diagonal = (*calculateDiagonal)(length, width, height);

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

      handle = loadLibrary("./librectangular.so");

      printf("Rectangular\n");
      printf("Length: %f\n", length);
      printf("Width: %f\n", width);

      loadPluginFunctions(handle, &calculateSurfaceArea, &calculatePerimeter,
                          &calculateDiagonal);

      double surfaceArea = (*calculateSurfaceArea)(length, width);
      double perimeter = (*calculatePerimeter)(length, width);
      double diagonal = (*calculateDiagonal)(length, width);

      printf("Perimeter: %f\n", perimeter);
      printf("Surface Area: %f\n", surfaceArea);
      printf("Diagonal: %f\n", diagonal);
      printf("\n");

      break;
    }
    }
  }

  dlclose(handle);
  return 0;
}
```

## Windows

```c
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
```

## Запуск програми (випадок виклику обчислення прямокутника та прізми)

```
./a.out -l 2 -w 3 -h 5 -r -p
```

## Вивід

```
Rectangular
Length: 2.000000
Width: 3.000000
Perimeter: 10.000000
Surface Area: 6.000000
Diagonal: 3.605551

Rectangular Prism
Length: 2.000000
Width: 3.000000
Height: 5.000000
Base Perimeter: 10.000000
Surface Area: 62.000000
Diagonal: 6.164414
```

## Запуск програми (випадок виклику обчислення прямокутника та прізми, при недостатній кількості параметрів для призми)

```
./a.out -l 2 -w 3 -r -p
```

## Вивід

```
Rectangular
Length: 2.000000
Width: 3.000000
Perimeter: 10.000000
Surface Area: 6.000000
Diagonal: 3.605551

Prism: Missing arguments
Usage: -w <width> -h <height> -l <length> -p
```

## Запуск програми (випадок виклику обчислення тільки прямокутника)

```
./a.out -l 2 -w 3 -r
```

## Вивід

```
Rectangular
Length: 2.000000
Width: 3.000000
Perimeter: 10.000000
Surface Area: 6.000000
Diagonal: 3.605551
```
