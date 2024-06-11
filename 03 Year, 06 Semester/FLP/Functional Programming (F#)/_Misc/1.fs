// Найти элемент списка с указанным индексом. Значение индекса - с 0.

let rec getElementAt index list =
    match index, list with
    | _, [] -> failwith "Empty list"
    | 0, head::_ -> head
    | i, _::tail -> getElementAt (i - 1) tail

let myList = [4; 3; 2; 1; 6]
let index = 4
let element = getElementAt index myList
printfn "Елемент з індексом %d: %d" index element