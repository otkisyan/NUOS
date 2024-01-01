# System Calls

System call is a programmatic way for a process to request a service from the kernel. The kernel is the core component of the operating system that manages system resources and provides essential services. System calls act as an interface between user-space applications and the kernel.


## Table of contents
- [Intro](#intro)
- [1. Read 10 characters from the keyboard, display them on the screen, and write them to a file](#1-read-10-characters-from-the-keyboard-display-them-on-the-screen-and-write-them-to-a-file)
- [2. Read the first 10 characters from a file, display them on the screen, and write them to another file](#2-read-the-first-10-characters-from-a-file-display-them-on-the-screen-and-write-them-to-another-file)

## Intro
The list and numbers of system calls can be found in the file `usr/include/x86_64-linux-gnu/asm/unistd_32.h`:

```c
#ifndef _ASM_UNISTD_32_H
#define _ASM_UNISTD_32_H

#define __NR_restart_syscall 0
#define __NR_exit 1
#define __NR_fork 2
#define __NR_read 3
#define __NR_write 4
#define __NR_open 5
#define __NR_close 6
// ...
```

The `errno.h` header file defines the integer variable `errno`, which is set by system calls and some library functions in the event of an error to indicate what went wrong.

On Linux, a failed system call using the syscall assembly instruction will return the value `-errno` in the `eax` register or `rax` if it's a x86_64

### Octal representation of flags when opening a file using the [open](https://man7.org/linux/man-pages/man2/open.2.html) system call
```c
#include <fcntl.h>
#include <stdio.h>

int main() {
    int flags = O_CREAT | O_WRONLY;
    // RU
    // Побитовая операция "ИЛИ" объединяет биты двух значений.
    // Если хотя бы один из соответствующих битов равен 1,
    // то бит в результирующем значении также будет равен 1.
    // В данном случае, это используется для комбинирования флагов открытия файла.
    // ENG
    // The bitwise OR operation combines bits of two values.
    // If at least one of the corresponding bits is 1,
    // then the bit in the resulting value will also be 1.
    // In this case, this is used to combine the file open flags.

    printf("Numeric (octal) representation of flags: %o\n", flags);
    return 0;
}
```
### Output
> Numeric (octal) representation of flags: 1001

In the `fcntl.h` header file you can find out hexadecimal representations of flags.

## 1. Read 10 characters from the keyboard, display them on the screen, and write them to a file

Listing of [src/first.asm:](./src/first.asm)

```assembly
format ELF executable
segment readable executable
    entry $        ; точка входу

    ; введення символів з клавіатури
    mov eax, 3     ; системний виклик для зчитування зі стандартного вводу
    mov ebx, 0     ; файловий дескриптор стандартного вводу (stdin)
    mov ecx, buffer ; адреса буфера для збереження введених символів
    mov edx, 10    ; кількість байт, яку слід зчитати
    int 0x80        ; виклик системи

    ; виведення введених символів на екран
    mov eax, 4     ; системний виклик для запису в стандартний вивід
    mov ebx, 1     ; файловий дескриптор стандартного виводу (stdout)
    mov ecx, buffer ; адреса буфера для виводу
    mov edx, 10    ; кількість байт для виводу
    int 0x80       ; виклик системи

    ; відкриття файлу для запису
    mov eax, 8     ; системний виклик для відкриття файлу
    mov ebx, filename ; адреса рядка з ім'ям файлу
    mov ecx, 0777     ; права доступу
    int 0x80        ; виклик системи

    ; запис вмісту буфера у файл
    mov edx, 10    ; кількість байт для запису
    mov ecx, buffer ; адреса буфера для запису
    mov ebx, eax   ; файловий дескриптор, отриманий при відкритті
    mov eax, 4     ; системний виклик для запису в файл
    int 0x80        ; виклик системи

    ; закриття файлу
    mov eax, 6     ; системний виклик для закриття файлу
    mov ebx, eax     ; файловий дескриптор для закриття
    int 0x80        ; виклик системи

    ; вихід з програми
    mov eax, 1     ; системний виклик для завершення програми
    xor ebx, ebx   ; код завершення 0
    int 0x80        ; виклик системи

segment readable writeable
    buffer db 10   ; буфер для збереження введених символів

segment readable
    filename db "output.txt", 0 ; ім'я файлу
```

### Assembly

```bash
fasm first.asm
```

### Run

```
./first
```

### Input

```
helloworld
```

### Output

> helloworld

### Check check the contents of the `output.txt` file

```
cat output.txt
```

### Output

> helloworld

## 2. Read the first 10 characters from a file, display them on the screen, and write them to another file.

Listing of [src/second.asm:](./src/second.asm)

```assembly
format ELF executable
segment readable executable
    entry $        ; точка входу

    ; відкриття файлу для зчитування
    mov eax, 5     ; системний виклик для відкриття файлу
    mov ebx, filename ; адреса рядка з ім'ям файлу
    mov ecx, 0     ; режим доступу (read-only)
    mov edx, 0777; права доступу
    int 0x80        ; виклик системи

    ; зчитати файл
    mov edx, 10    ; кількість байт для запису
    mov ecx, buffer ; адреса буфера для запису
    mov ebx, eax   ; файловий дескриптор, отриманий при відкритті
    mov eax, 3     ; системний виклик для запису в файл
    int 0x80        ; виклик системи

    ; закриття файлу
    mov eax, 6     ; системний виклик для закриття файлу
    mov ebx, eax     ; файловий дескриптор для закриття
    int 0x80        ; виклик системи

    ; виведення зчитаних символів на екран
    mov eax, 4     ; системний виклик для запису в стандартний вивід
    mov ebx, 1     ; файловий дескриптор стандартного виводу (stdout)
    mov ecx, buffer ; адреса буфера для виводу
    mov edx, 10    ; кількість байт для виводу
    int 0x80       ; виклик системи

    ; відкриття файлу для запису
    mov eax, 8     ; системний виклик для відкриття файлу
    mov ebx, filename2 ; адреса рядка з ім'ям файлу
    mov ecx, 0777     ; права доступу
    int 0x80        ; виклик системи

    ; запис вмісту буфера у файл
    mov edx, 10    ; кількість байт для запису
    mov ecx, buffer ; адреса буфера для запису
    mov ebx, eax   ; файловий дескриптор, отриманий при відкритті
    mov eax, 4     ; системний виклик для запису в файл
    int 0x80        ; виклик системи

    ; закриття файлу
    mov eax, 6     ; системний виклик для закриття файлу
    mov ebx, eax     ; файловий дескриптор для закриття
    int 0x80        ; виклик системи

    ; вихід з програми
    mov eax, 1     ; системний виклик для завершення програми exit
    xor ebx, ebx   ; код завершення 0
    int 0x80        ; виклик системи

segment readable writeable
    buffer db 10   ; буфер для збереження введених символів

segment readable
    filename db "output.txt", 0 ; ім'я першого файлу з символами
    filename2 db "output2.txt", 0; ім'я другого файлу в який необхідно записати символи з першого файлу
```

### Assembly

```bash
fasm second.asm
```

### Run

```
./second
```

### Output

> helloworld

### Check check the contents of the `output2.txt` file

```
cat output2.txt
```

### Output

> helloworld



## Sources

1. [UMBC: System Calls](https://redirect.cs.umbc.edu/courses/undergraduate/313/fall04/burt_katz/lectures/Lect07/systemCalls.html)
2. [Tutorialspoint: Assembly - File management](https://www.tutorialspoint.com/assembly_programming/assembly_file_management.htm)
3. [Redhat: Linux file permissions explained](https://www.redhat.com/sysadmin/linux-file-permissions-explained)
4. [Stackoverflow: Linux syscalls and errno](https://stackoverflow.com/questions/37167141/linux-syscalls-and-errno#:~:text=On%20Linux%2C%20a%20failed%20system,errno%20in%20the%20rax%20register.)
