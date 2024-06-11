// Удалить из списка все элементы со значением n

let rec removeElement n lst =
    match lst with
    | [] -> []
    | head::tail when head = n -> removeElement n tail
    | head::tail -> head :: removeElement n tail

let myList = [4; 3; 2; 1; 4]
let result = removeElement 4 myList
printfn "%A" result
