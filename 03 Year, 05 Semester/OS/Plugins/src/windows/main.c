#include <stdio.h>
#include <windows.h>

int main()
{

  HINSTANCE hinstLib;
  BOOL fFreeResult, fRunTimeLinkSuccess = FALSE;

  // Get a handle to the DLL module.
  hinstLib = LoadLibrary(TEXT("libprism.dll"));

  typedef double (*calculatePrismPerimeterFunction)(double, double);
  typedef double (*calculatePrismSurfaceAreaFunction)(double, double, double);
  typedef double (*calculatePrismDiagonalFunction)(double, double, double);
  calculatePrismPerimeterFunction calculatePrismPerimeter;
  calculatePrismSurfaceAreaFunction calculatePrismSurfaceArea;
  calculatePrismDiagonalFunction calculatePrismDiagonal;

  // If the handle is valid, try to get the function address.
  if (hinstLib != NULL)
  {
    calculatePrismPerimeter = (calculatePrismPerimeterFunction) GetProcAddress(hinstLib, "calculatePrismPerimeter");
    calculatePrismSurfaceArea = (calculatePrismSurfaceAreaFunction) GetProcAddress(hinstLib, "calculatePrismSurfaceArea");
    calculatePrismDiagonal = (calculatePrismDiagonalFunction) GetProcAddress(hinstLib, "calculatePrismDiagonal");

    if (calculatePrismPerimeter != NULL && calculatePrismSurfaceArea != NULL && calculatePrismDiagonal != NULL)
    {
      fRunTimeLinkSuccess = TRUE;
    }
  }

  if (!fRunTimeLinkSuccess)
  {
    printf("Error\n");
    return 1;
  }

  double width = 3, length = 2, height = 5;
  double prismPerimeter = calculatePrismPerimeter(length, width);
  double prismSurfaceArea = calculatePrismSurfaceArea(length, width, height);
  double prismDiagonal = calculatePrismDiagonal(length, width, height);

  fFreeResult = FreeLibrary(hinstLib);

  printf("Rectangular Prism\n");
  printf("Length: %f\n", length);
  printf("Width: %f\n", width);
  printf("Height: %f\n", height);
  printf("Base Perimeter: %f\n", prismPerimeter);
  printf("Surface Area: %f\n", prismSurfaceArea);
  printf("Diagonal: %f\n", prismDiagonal);

  return 0;
}
