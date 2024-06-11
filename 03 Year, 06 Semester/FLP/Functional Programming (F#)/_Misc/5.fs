// Отсортировать список

let rec insert x lst =
    match lst with
    | [] -> [x]
    | head :: tail -> if x <= head then x :: head :: tail else head :: insert x tail

let rec sort lst =
    match lst with
    | [] -> []
    | head :: tail -> insert head (sort tail)

let inputList = [1; 5; 8; 4; 2]
let sortedList = sort inputList
printfn "%A" sortedList
