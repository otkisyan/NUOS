# Plugins

In C programming, a plugin typically refers to a dynamically loadable shared library (also known as a dynamic link library or DLL on Windows systems) that can be loaded and executed by a C program at runtime. Plugins allow developers to extend the functionality of an application without modifying its core code.

How to create your own shared library on Linux and Windows can be found here: [Shared Libraries](../Shared%20Libraries/).

The key difference between a dynamic library and a plugin is that a dynamic library is loaded by the linker, while a plugin is loaded by the program itself.

## Table of contents

- [Linux](#linux)
- [Windows](#windows)

## Linux

The [linux/libprism.so](./src/linux/libprism.so) library implements the following functions:

```c
#include "prism.h"
#include <math.h>

double calculatePrismPerimeter(double length, double width) {
  return 2 * (length + width);
}

double calculatePrismSurfaceArea(double length, double width, double height) {
  return 2 * (length * width) + (2 * length + 2 * width) * height;
}

double calculatePrismDiagonal(double length, double width, double height) {
  return sqrt((length * length) + (width * width) + (height * height));
}

```

Using [linux/main.c](./src/linux/main.c)

### Including necessary headers

```c
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
```

### Loading the shared library

```c
void *handle;
handle = dlopen("./libprism.so", RTLD_LAZY);
```

The function `dlopen()` loads the dynamic shared object (shared library) file named by the null-terminated string filename and returns an opaque "handle" for the loaded object. This handle is employed with other functions in the dlopen API, such as `dlsym`, `dladdr`, `dlinfo`, and `dlclose()`.

`RTLD_LAZY` Perform lazy binding. Resolve symbols only as the code that references them is executed. If the symbol is never referenced, then it is never resolved.

### Error handling

```c
if (!handle) {
  fprintf(stderr, "%s\n", dlerror());
  exit(EXIT_FAILURE);
}
```

If `dlopen` fails to load the shared library (for example, if the library file doesn't exist or if there are unresolved symbols), it returns NULL. In this case, the code inside the if block is executed.

The function `dlerror()` returns a human readable string describing the most recent error that occurred from `dlopen()`, `dlsym()` or `dlclose()` since the last call to `dlerror()`. It returns NULL if no errors have occurred since initialization or since it was last called.

### Resolving function pointers

```c
typedef double (*calculatePrismSurfaceAreaFunction)(double, double, double);
typedef double (*calculatePrismPerimeterFunction)(double, double);
typedef double (*calculatePrismDiagonalFunction)(double, double, double);

calculatePrismSurfaceAreaFunction calculatePrismSurfaceArea =
    (calculatePrismSurfaceAreaFunction)dlsym(handle, "calculatePrismSurfaceArea");

calculatePrismPerimeterFunction calculatePrismPerimeter =
    (calculatePrismPerimeterFunction)dlsym(handle, "calculatePrismPerimeter");

calculatePrismDiagonalFunction calculatePrismDiagonal =
    (calculatePrismDiagonalFunction)dlsym(handle, "calculatePrismDiagonal");

```

`typedef` is used to create custom data types by assigning new names to existing data types.

The function `dlsym()` takes a "handle" of a dynamic library returned by `dlopen()` and the null-terminated symbol name, returning the address where that symbol is loaded into memory. It is used to obtain the addresses of the functions `calculatePrismSurfaceArea`, `calculatePrismPerimeter`, and `calculatePrismDiagonal` from the shared library.

### Error handling

```c
char *error;
error = dlerror();
if (error != NULL) {
    fprintf(stderr, "%s\n", error);
    exit(EXIT_FAILURE);
}
```

### Calling functions

```c
double width = 3, length = 2, height = 5;
double prismSurfaceArea = (*calculatePrismSurfaceArea)(length, width, height);
double prismPerimeter = (*calculatePrismPerimeter)(length, width);
double prismDiagonal = (*calculatePrismDiagonal)(length, width, height);
```

### Unloading the shared library

```c
dlclose(handle);
```

The `dlclose()` function shall inform the system that the symbol table handle specified by `handle` is no longer needed by the application. Once a symbol table handle has been closed, an application should assume that any symbols (function identifiers and data object identifiers) made visible using `handle`, are no longer available to the process.

### Printing results

```c
printf("Rectangular Prism\n");
printf("Length: %f\n", length);
printf("Width: %f\n", width);
printf("Height: %f\n", height);

printf("Base Perimeter: %f\n", prismPerimeter);
printf("Surface Area: %f\n", prismSurfaceArea);
printf("Diagonal: %f\n", prismDiagonal);
```

### Compile and run the program

```sh
gcc main.c
```

```
./a.out
```

### Output

> Rectangular Prism
>
> Length: 2.000000
>
> Width: 3.000000
>
> Height: 5.000000
>
> Base Perimeter: 10.000000
>
> Surface Area: 62.000000
>
> Diagonal: 6.164414

## Windows

The [windows/libprism.dll](./src/windows/libprism.dll) library implements the following functions:

```c
#include "prism.h"
#include <math.h>

double calculatePrismPerimeter(double length, double width) {
  return 2 * (length + width);
}

double calculatePrismSurfaceArea(double length, double width, double height) {
  return 2 * (length * width) + (2 * length + 2 * width) * height;
}

double calculatePrismDiagonal(double length, double width, double height) {
  return sqrt((length * length) + (width * width) + (height * height));
}

```

Using [windows/main.c](./src/windows/main.c)

### Including necessary headings

```c
#include <stdio.h>
#include <windows.h>
```

### Loading the dynamic-link library

```c
HINSTANCE hinstLib;
BOOL fFreeResult, fRunTimeLinkSuccess = FALSE;
// Get a handle to the DLL module.
hinstLib = LoadLibrary(TEXT("libprism.dll"));
```

`HINSTANCE` is a handle to an instance of a module (such as an application or a DLL).

`LoadLibrary` loads the specified module into the address space of the calling process.

### Define function pointers and declare variables

```c
typedef double (*calculatePrismPerimeterFunction)(double, double);
typedef double (*calculatePrismSurfaceAreaFunction)(double, double, double);
typedef double (*calculatePrismDiagonalFunction)(double, double, double);

calculatePrismPerimeterFunction calculatePrismPerimeter;
calculatePrismSurfaceAreaFunction calculatePrismSurfaceArea;
calculatePrismDiagonalFunction calculatePrismDiagonal;
```

### Get Function Adresses

```c
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
```

`LoadLibrary` Retrieves the address of an exported function or variable from the specified dynamic-link library (DLL).

### Calling functions

```c
double width = 3, length = 2, height = 5;
double prismPerimeter = calculatePrismPerimeter(length, width);
double prismSurfaceArea = calculatePrismSurfaceArea(length, width, height);
double prismDiagonal = calculatePrismDiagonal(length, width, height);
```

### Unloading the dynamic-link library

```c
fFreeResult = FreeLibrary(hinstLib);
```

`FreeLibrary` frees the loaded dynamic-link library (DLL) module and, if necessary, decrements its reference count.

### Printing results

```c
printf("Rectangular Prism\n");
printf("Length: %f\n", length);
printf("Width: %f\n", width);
printf("Height: %f\n", height);
printf("Base Perimeter: %f\n", prismPerimeter);
printf("Surface Area: %f\n", prismSurfaceArea);
printf("Diagonal: %f\n", prismDiagonal);
```

### Compile and run the program

```sh
gcc main.c
```

```
a.exe
```

### Output

> Rectangular Prism
>
> Length: 2.000000
>
> Width: 3.000000
>
> Height: 5.000000
>
> Base Perimeter: 10.000000
>
> Surface Area: 62.000000
>
> Diagonal: 6.164414

## Sources

1. [Man7: dlopen](https://man7.org/linux/man-pages/man3/dlopen.3.html)
2. [Linux.die.net: dlsym](https://linux.die.net/man/3/dlsym)
3. [Microsoft: Using Run-Time Dynamic Linking](https://learn.microsoft.com/en-us/windows/win32/dlls/using-run-time-dynamic-linking)
4. [Stackoverflow: Dynamically load a function from a DLL](https://stackoverflow.com/questions/8696653/dynamically-load-a-function-from-a-dll)
5. [Stackoverflow: Usage of dlsym()/dlopen()](https://stackoverflow.com/questions/23654120/usage-of-dlsym-dlopen)
