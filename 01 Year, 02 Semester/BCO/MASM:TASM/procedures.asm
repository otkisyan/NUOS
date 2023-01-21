# Выполнить задание лабораторной работы, используя команды работы с процедурами. 
# Сформировать матрицу 10x10 и поменять местами строки со столбцами
# 9 рядок и 9 столбец

include 'emu8086.inc'
data segment
a1 db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 0,1,2,3,4,5,6,7,8,9
db 3,4,2,3,4,1,8,2,6,7
db 0,1,2,3,4,5,6,7,8,9
ends

code segment
Transp proc
circle:
mov al,[bx+di+8]
mov ah,[bx+si+80]
mov [bx+si+80],al
mov [bx+di+8],ah
inc si
add di,10
loop circle
ret
transp endp

start:
mov ax, data
mov ds, ax
mov es, ax
xor ax, ax
call transp
code ends
end start