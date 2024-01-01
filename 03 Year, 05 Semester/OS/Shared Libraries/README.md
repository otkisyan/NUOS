# Shared Libraries (Dynamic-link Libraries)

Shared Library (Object) `(.so)` is the terminology used by Unix and Linux. Dynamic-link Library `(.dll)` is the terminology used by Microsoft Windows.

## Table of contents

- [Linux](#linux)
- [Windows](#windows)
- [Name mangling](#name-mangling)
- [Utilities to scan for connected dynamic libraries](#utilities-to-scan-for-connected-dynamic-libraries)
- [Summarize](#summarize)

Also see [Static Libraries](../Static%20Libraries)

## Linux

Using [prism_perimeter.c](./src/prism_perimeter.c), [prism_surface_area.c](./src/prism_surface_area.c), [prism_diagonal.c](./src/prism_diagonal.c) and [prism.h](./src/prism.h)

### Create the object modules

```bash
gcc -fpic -c prism_perimeter.c prism_surface_area.c prism_diagonal.c
```

### Flags description:

- `-c` Compile and assemble, but do not link. This option tells the gcc compiler to create only object files without performing the linking step. Object files contain machine code, but are not yet linked into an executable or library;

- `-fpic` (Position Independent Code) causes compiler to generate code that can be loaded anywhere in virtual memory at run-time.

> [Linking](https://csapp.cs.cmu.edu/2e/ch7-preview.pdf) is the process of collecting and combining various pieces of code and data into a single file that can be loaded (copied) into memory and executed.

### Create the shared library

```bash
gcc -shared -o libprism.so prism_perimeter.o prism_surface_area.o prism_diagonal.o
```

### Flags description:

- `-shared` Produce a shared object which can then be linked with other objects to form an executable;
- `-o` Specifies the output file name.

### Link program against library

Using [main.c](./src/main.c)

```bash
gcc main.c -I. -L. -lprism
```

### Flags description:

- `-I.` Specifies the directories where the compiler should look for header files (`.` referring to the current directory);

- `-L` Specifies the path to the given libraries;

- `-l` Specifies the library name without the "lib" prefix and the ".so" suffix, because the linker attaches these parts back to the name of the library to create a name of a file to look for.

### Run the program

```bash
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

### Create the object modules

```bash
gcc -fpic -c prism_perimeter.c prism_surface_area.c prism_diagonal.c
```

### Create the dynamic-link library

```bash
gcc -shared -o libprism.dll prism_perimeter.o prism_surface_area.o prism_diagonal.o
```

### Link program against library

```bash
gcc main.c -I. -L. -lprism
```

### Run the program

```bash
a.exe
```

## Name mangling

The `extern "C"` syntax is used to indicate to the compiler that the specified function or variable should be compiled using C linkage instead of C++ linkage. This is important when you are trying to use C code (functions and variables) in a C++ program.

Since C++ has overloading of function names and C does not, the C++ compiler cannot just use the function name as a unique id to link to, so it mangles the name by adding information about the arguments. A C compiler does not need to mangle the name since you can not overload function names in C.

When you are trying to use a C library in a C++ program, and the library header file includes C function declarations, you should use the `extern "C"` syntax to prevent the C++ compiler from applying name mangling to those function names. This ensures that the function names in the library are treated as-is, without any modification, allowing the C++ code to correctly link to the functions defined in the C library.

See [main.cpp](src/main.cpp)

```cpp
extern "C" {
    #include "prism.h"
}
```

## Utilities to scan for connected dynamic libraries

### Windows

- Dependency Walker
- Process Explorer

### Linux

- ldd
- pmap

### Mac OS

- otool
- vmmap

## Summarize

### Static Libraries

- Get copied directly into your executable;
- The executable is not updated automatically when there is a change in the library;
- Larger executable files.

### Dynamic Libraries

- Only the address of the library is referenced in the executable;
- Executable gets updated when a new version of the library is encountered;
- Smaller executable files.

## Sources

1. [Man7: Creating and using a shared library](https://man7.org/conf/lca2006/shared_libraries/slide3a.html)
2. [Stackoverflow: Building a shared library using gcc on Linux and MinGW on Windows](https://stackoverflow.com/questions/17601949/building-a-shared-library-using-gcc-on-linux-and-mingw-on-windows)
3. [Stackoverflow: How do import libraries work and why doesn't MinGW need them?](https://stackoverflow.com/questions/27824102/how-do-import-libraries-work-and-why-doesnt-mingw-need-them)
4. [Stackoverflow: Difference between shared objects (.so), static libraries (.a), and DLL's (.so)?](https://stackoverflow.com/questions/9688200/difference-between-shared-objects-so-static-libraries-a-and-dlls-so)
5. [Stackoverflow: What is the difference between shared and dynamic libraries in C?](https://stackoverflow.com/questions/56899488/what-is-the-difference-between-shared-and-dynamic-libraries-in-c)
6. [Stackoverflow: What is name mangling, and how does it work?](https://stackoverflow.com/questions/1314743/what-is-name-mangling-and-how-does-it-work)
7. [Stackoverflow: What is the effect of extern "C" in C++?](https://stackoverflow.com/questions/1041866/what-is-the-effect-of-extern-c-in-c)
