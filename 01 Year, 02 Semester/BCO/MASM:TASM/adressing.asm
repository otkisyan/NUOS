; 1) Сформировать массив данных объемом в 10 байт. 
; 2) Сформировать пустой массив объемом в 10 байт. 
; 3) Выполнить перенос данных из первого массива во второй, используя методы непрямой адресации (базовой, индексной и базово-индексной). 
; 4) С помощью команды XLAT определить i-й элемент второго массива (i-й элемент - последняя цифра порядкового номера в списке группы (9)).

dat1 segment
mas1 db 4, 6, 7, 32, 8, 51, 34, 3, 5, 9
mas2 db 10 dup (?)
dat1 ends
program Segment
assume ds:dat1, cs:program, ss:dat1
begin:
mov ax,dat1
mov ds,ax
mov ax,dat1
mov ss,ax
xor ax,ax

mov bx,offset mas1
mov al, [bx]
mov bx,offset mas2
mov [bx], al

mov bx, offset mas1
mov si,bx
mov bx, offset mass mas2
mov di, bx
mov al,1[si]
mov ah,2[si]
mov cl,3[si]
mov ch,4[si]
mov 1[di],al
mov 2[di],ah
mov 3[di],cl
mov 4[di],ch

mov bx,offset mas1
mov al, 5[bx]
mov ah, 6[bx]
mov bx, offset mas2
mov [bx+5], al
mov [bx+6], ah

mov bx, offset mas1
mov si,7
mov ah,[bx+si]
inc si
mov al,[bx+si]
mov ch,[bx+si+1]
mov bx, offset mas2
mov si,7
mov [bx+si],ah
inc si
mov [bx+si],al
mov [bx+si+1],ch

xor ax,ax
mov bx, offset mas2
mov al, 8
xlat

int 21h
mov ah, 4Ch
int 21h

program endS
end begin