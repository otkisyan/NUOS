# Environment variables

### Task: Develop a C/C++ program to display values and set environment variables. Check the effect of setting and changing them on other processes. Perform the work in Linux and Windows

## Table of contents

- [Setting and reading environment variables](#setting-and-reading-environment-variables)
  - [Windows](#windows)
  - [Linux](#linux)
- [Investigating the effect of setting environmental variables on the other processes](#investigating-the-effect-of-setting-environmental-variables-on-the-other-processes)
  - [Windows](#windows-1)
  - [Linux](#linux-1)
- [Sources](#sources)

## 1. Setting and reading environment variables

## Windows

Using [process-env-windows.c](./src/windows/process-env-windows.c)

### Compile and run the program

```sh
gcc process-env-windows.c
```

```
a.exe
```

### Output

> TEST_ENV: test

### Try to output `TEST_ENV`

```
echo %TEST_ENV%
```

### Output

> %TEST_ENV%

## Linux

Using [process-env-linux.c](./src/linux/process-env-linux.c)

### Compile and run the program

```sh
gcc process-env-linux.c
```

```
./a.out
```

### Output

> TEST_ENV: test

### Try to output `TEST_ENV`

```
echo $TEST_ENV
```

### Output

> Empty line

_We can conclude that environment variables set in the program exist only while the program is running._

## 2. Investigating the effect of setting environmental variables on the other processes

Setting an environment variable affects only the current process and the processes running from this current process. It does not affect other processes that are already running on the system.

## Windows

Using [fork-process-env-windows.c](./src/windows/fork-process-env-windows.c) and [child-process-env.c](./src/windows/child-process-env.c)

### Compile and run the program

```sh
gcc child-process-env.c -o child.exe
```

```sh
gcc fork-process-env-windows.c
```

```
a.exe
```

### Output

> TEST_ENV: test
>
> TEST_ENV (child): test

## Linux

Using [fork-process-env-linux.c](./src/linux/fork-process-env-linux.c)

### Compile and run the program

```sh
gcc fork-process-env-linux.c
```

```
./a.out
```

### Output

> Parent process
>
> TEST_ENV: test
>
> Child process
>
> TEST_ENV (child): test

## Sources

1. [Microsoft: Create processes](https://learn.microsoft.com/en-us/windows/win32/procthread/creating-processes)
2. [waitpid(3) - Linux man page](https://linux.die.net/man/2/waitpid)
3. [fork(2) - Linux manual page](https://man7.org/linux/man-pages/man2/fork.2.html)
4. [setenv(3) - Linux manual page](https://man7.org/linux/man-pages/man3/setenv.3.html)
5. [Microsoft: SetEnvironmentVariable function](https://learn.microsoft.com/en-us/windows/win32/api/winbase/nf-winbase-setenvironmentvariable)
