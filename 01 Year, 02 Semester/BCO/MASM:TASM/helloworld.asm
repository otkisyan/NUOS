; Вывод "Hello World"
title word
dat1 segment
a db 'Hello world1',10,13
b db 'Hello world2',10,13,'$'
c db '34'
dat1 endS
cod1 segment
assume cs:cod1, ds:dat1
start1: mov ax, dat1
mov ds, ax
xor ax, ax
mov ah, 09h
mov dx, offset a
int 21h
mov ah, 02h
mov dx, 'c'
int 21h
mov ah,4Ch
int 21h
cod1 endS
end Start1