; Разработать программу на языке Ассемблер с линейной структурой. 
; Входные данные (переменные X и Y) выбрать самостоятельно таким образом, чтобы сами переменные и любое решение были отличны от 0 и 1. 
; Расчеты выполнить для типов данных байт и слово. Операнды и результаты работы программы разместить в памяти.
; 19) z = 36/(x-y)+36*(x+y)

# Текст программы 1:
dat1 segment
x db 7
y db 3
z dw ?
dat1 ends
cod1 segment
assume ds:dat1, cs:cod1
start:
mov ax, dat1
mov ds, ax
xor ax, ax
mov bl,x
mov bh,y
sub bl,bh
mov ax,36
div bl
mov z,ax

mov bl,x
mov bh,y
add bl,bh
mov al,bl
mov ah,36
mul ah
add z,ax
mov ah, 4ch
int 21h
cod1 ends
end start

# -----------------
# Текст программы 2:
dat1 segment
x dw 7
y dw 3
z dw ?
dat1 ends
cod1 segment
assume ds:dat1, cs:cod1
start:
mov ax, dat1
mov ds, ax
xor ax, ax
mov bx,x
mov cx,y
sub bx,cx
mov ax,36
idiv bx
mov z,ax
mov bx,x

mov cx,y
add bx,cx
mov ax,bx
mov cx,36
mul cx
add z,ax
mov ah, 4ch
int 21h
cod1 ends
end start