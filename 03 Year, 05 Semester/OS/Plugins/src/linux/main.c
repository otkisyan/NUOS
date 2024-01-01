#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>

int main() {

  void *handle;
  typedef double (*calculatePrismSurfaceAreaFunction)(double, double, double);
  typedef double (*calculatePrismPerimeterFunction)(double, double);
  typedef double (*calculatePrismDiagonalFunction)(double, double, double);

  char *error;

  handle = dlopen("./libprism.so", RTLD_LAZY);
  if (!handle) {
    fprintf(stderr, "%s\n", dlerror());
    exit(EXIT_FAILURE);
  }

  dlerror(); /* Clear any existing error */

  calculatePrismSurfaceAreaFunction calculatePrismSurfaceArea =
      (calculatePrismSurfaceAreaFunction)dlsym(handle,
                                               "calculatePrismSurfaceArea");

  calculatePrismPerimeterFunction calculatePrismPerimeter =
      (calculatePrismPerimeterFunction)dlsym(handle, "calculatePrismPerimeter");

  calculatePrismDiagonalFunction calculatePrismDiagonal =
      (calculatePrismDiagonalFunction)dlsym(handle, "calculatePrismDiagonal");

  error = dlerror();

  if (error != NULL) {
    fprintf(stderr, "%s\n", error);
    exit(EXIT_FAILURE);
  }

  double width = 3, length = 2, height = 5;
  double prismSurfaceArea = (*calculatePrismSurfaceArea)(length, width, height);
  double prismPerimeter = (*calculatePrismPerimeter)(length, width);
  double prismDiagonal = (*calculatePrismDiagonal)(length, width, height);

  dlclose(handle);

  printf("Rectangular Prism\n");
  printf("Length: %f\n", length);
  printf("Width: %f\n", width);
  printf("Height: %f\n", height);

  printf("Base Perimeter: %f\n", prismPerimeter);
  printf("Surface Area: %f\n", prismSurfaceArea);
  printf("Diagonal: %f\n", prismDiagonal);

  return 0;
}
