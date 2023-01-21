# Сформировать матрицу 10x10 и поменять местами строки со столбцами
# 9 рядок и 9 столбец

Dat1 segment
a1 db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
dat1 ends
cod1 Segment
assume ds:Dat1, cs:Cod1, es:Dat1
start: mov ax,dat1
mov ds,ax
mov es,ax

xor ax, ax

mov cx,10
mov di,0
mov si,0
mov bx,offset a1

1:
mov al,[bx+di+8]
mov ah,[bx+si+80]
mov [bx+si+80],al
mov [bx+di+8],ah
inc si
add di,10
LOOP 1

mov ah,4Ch
int 21h
Cod1 ends
end start