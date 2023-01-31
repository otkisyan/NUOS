; Сформировать массив данных и обработать его
; 4) заменить прописные буквы на заглавные

String db "Hello",0ah,0dh,'$'
strlen = $ - String

.code
start: mov ax,@data
mov ds,ax
mov es,ax

mov bx,offset String
mov cx,strlen
mov di,0
L1:
cmp byte ptr [bx+di],'a'
cmp byte ptr [bx+di],'z'
and byte ptr [di],11011111b
inc di
Loop L1
mov ah,4Ch
int 21h
end start