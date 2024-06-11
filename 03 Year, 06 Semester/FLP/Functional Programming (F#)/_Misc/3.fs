// Удалить из списка элемент с индексом n

let rec removeAtIndex n list =
    match n, list with
    | _, [] -> []
    | 0, _::tail -> tail
    | n, head::tail -> head :: (removeAtIndex (n - 1) tail)

let inputList = [4; 3; 2; 1; 4]
let indexToRemove = 3

let modifiedList = removeAtIndex indexToRemove inputList
printfn "Модифікований список: %A" modifiedList
