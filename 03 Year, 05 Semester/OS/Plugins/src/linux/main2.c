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
