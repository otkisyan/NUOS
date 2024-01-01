# Static Libraries

### Compile library codes into object files `(*.o)`

Using [prism_perimeter.c](./src/prism_perimeter.c), [prism_surface_area.c](./src/prism_surface_area.c), [prism_diagonal.c](./src/prism_diagonal.c) and [prism.h](./src/prism.h)

```bash
gcc -c prism_perimeter.c prism_surface_area.c prism_diagonal.c
```

### Flags description:

- `-c` Compile and assemble, but do not link. This option tells the gcc compiler to create only object files without performing the linking step. Object files contain machine code, but are not yet linked into an executable or library.

> [Linking](https://csapp.cs.cmu.edu/2e/ch7-preview.pdf) is the process of collecting and combining various pieces of code and data into a single file that can be loaded (copied) into memory and executed.

Once we have object file(s), we can now bundle all object files into one static library.

To create a static library, we have to use the GNU [ar](https://man7.org/linux/man-pages/man1/ar.1.html) (archiver) program. We can use a command like this:

```bash
ar -rcs libprism.a prism_perimeter.o prism_surface_area.o prism_diagonal.o
```

### Flags description:

- `-r` Insert the files member... into archive (with replacement);
- `-c` Create the archive;
- `-s` Add an index to the archive, or update it if it already exists.

### Now our static library `libprism.a` is ready to be used. Link the test program to the static library.

Using [main.c](./src/main.c)

```bash
gcc main.c -I. -L. -lprism
```

### Flags description:

- `-I.` Specifies the directories where the compiler should look for header files (`.` referring to the current directory);

- `-L` Specifies the path to the given libraries;

- `-l` Specifies the library name without the "lib" prefix and the ".a" suffix, because the linker attaches these parts back to the name of the library to create a name of a file to look for.

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
> Perimeter: 10.000000
>
> Surface Area: 62.000000
>
> Diagonal: 6.164414

### The process of creating and using a static library on Windows is similar, with the only change being the extension of the executable `.exe` rather than `.out`

## Sources

1. [DEV.to: All you need to know about C Static libraries](https://dev.to/iamkhalil42/all-you-need-to-know-about-c-static-libraries-1o0b)
2. [Stackoverflow: What is name mangling, and how does it work?](https://stackoverflow.com/questions/1314743/what-is-name-mangling-and-how-does-it-work)
3. [Stackoverflow: What is the effect of extern "C" in C++?](https://stackoverflow.com/questions/1041866/what-is-the-effect-of-extern-c-in-c)