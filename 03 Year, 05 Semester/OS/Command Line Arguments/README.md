# Arguments and results of the program

### Task: Develop a C/C++ program to determine the system restrictions on the number of command line arguments passed to the program and the range of values of the program result. Perform in MS Windows and Linux.

## Table of contents

- [Range of values of the program result](#1-range-of-values-of-the-program-result)
  - [Windows](#windows)
  - [Linux](#linux)
- [The system restrictions on the number of command line arguments passed to the program](#2-the-system-restrictions-on-the-number-of-command-line-arguments-passed-to-the-program)
  - [Windows](#windows-1)
  - [Linux](#linux-1)

## 1. Range of values of the program result

## Windows

### Compile with `INT_MIN` return value

```sh
gcc hello.c
```

```sh
a.exe
```

Output:

> argv[0]=a.exe

### Echo ErrorLevel

```sh
echo %ErrorLevel%
```

Output:

> -2147483648

### Compile with `INT_MAX` return value

```sh
gcc hello.c
```

```sh
a.exe
```

Output:

> argv[0]=a.exe

### Echo ErrorLevel

```sh
echo %ErrorLevel%
```

Output:

> 2147483647

## Linux

### Compile with `INT_MIN` return value

```sh
gcc hello.c
```

```sh
./a.out
```

Output:

> argv[0]=./a.out

### Echo the exit status of previous command

```sh
echo $?
```

Output:

> 0

### Compile with `INT_MAX` return value

```sh
gcc hello.c
```

```sh
./a.out
```

Output:

> argv[0]=./a.out

### Echo the exit status of previous command

```sh
echo $?
```

Output:

> 255

## 2. The system restrictions on the number of command line arguments passed to the program

## Windows

Using [max-number-of-arguments.bat](./src/windows/max-number-of-arguments.bat)

### Run script

```sh
max-number-of-arguments.bat
```

Output:

> argv[1636]=test

We are trying to pass 5.000 arguments, but at 1636 the program terminates.

## Linux

Using [max-number-of-arguments.sh](./src/linux/max-number-of-arguments.sh)

### Run script

```sh
./max-number-of-arguments.sh
```

Output:

> ./max-number-of-arguments.sh: line 14: ./a.out: Argument list too long
>
> Number of args: 80502
